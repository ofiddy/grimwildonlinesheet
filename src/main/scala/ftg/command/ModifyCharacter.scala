package ftg.command

import ftg.Character.Condition
import ftg.Character.StatPool
import ftg.Character.StatPool.diceRemainingLens
import ftg.Character.StatPool.markedLens
import ftg.Character.UrgentCondition
import ftg.Character.{Character => Character}
import ftg.DicePool.RollGenerator
import ftg.page.Model
import monocle.syntax.all.focus

object ModifyCharacter {
  def modify(cmd: EffectCommand, model: Model)(using RollGenerator): Model =
    cmd match
      case v @ ValueEditCommand(_, _, find) =>
        model withChar (char => find(char).replace(v.into))
      case EditStatPoolSizeCommand(newDice, _, find) =>
        model withChar (char =>
          find(char).andThen(diceRemainingLens).replace(newDice)
        )
      case TogglePoolMarkedCommand(find) =>
        model withChar (char => find(char).andThen(markedLens).modify(!_))
      case ToggleCommand(find) =>
        model withChar (char => find(char).modify(!_))
      case AddListElemCommand(factory, find) =>
        model withChar (char =>
          find(char)
            .modify(_ :+ factory.build)
        )
      case ModifyListElemCommand(newCond, _, i, find) =>
        model withChar (char => find(char).modify(_.updated(i, newCond)))
      case DeleteListElemCommand(_, index, find) =>
        model withChar (char => find(char).modify(_.patch(index, Nil, 1)))
      case RollAndDropConditionPoolCommand(i, _) =>
        rollAndDropCondition(i, model)
      case ToggleTalentCommand(td, _) =>
        model withChar (char =>
          val without         = char.talents.filter(_.talentDesc != td)
          val shouldHaveAdded = char.talents.length == without.length
          val newTalents =
            if shouldHaveAdded then (td(char) :: char.talents).sortBy(_.name)
            else without
          char.focus(_.talents).replace(newTalents)
        )
      case ChangeClassCommand(newClass, _) =>
        model withChar (char =>
          char.copy(
            coreTalent = newClass.coreTalent(char),
            charClass = newClass
          )
        )

  def undo(cmd: EffectCommand, model: Model): Model = cmd match
    case ValueEditCommand(_, old, find) =>
      model withChar (char => find(char).replace(old))
    case EditStatPoolSizeCommand(_, old, find) =>
      model withChar (char =>
        find(char).andThen(diceRemainingLens).replace(old)
      )
    case TogglePoolMarkedCommand(find) =>
      model withChar (char => find(char).andThen(markedLens).modify(!_))
    case ToggleCommand(find) =>
      model withChar (char => find(char).modify(!_))
    case AddListElemCommand(_, find) =>
      model withChar (char => find(char).modify(_.dropRight(1)))
    case ModifyListElemCommand(_, oldCond, i, find) =>
      model withChar (char => find(char).modify(_.updated(i, oldCond)))
    case DeleteListElemCommand(oldCond, index, find) =>
      model withChar (char =>
        find(char)
          .modify(l =>
            val (front, back) = l.splitAt(index)
            front ++ (oldCond :: back)
          )
      )
    case RollAndDropConditionPoolCommand(i, prevPool) =>
      model.character.conditions.lift(i) match
        case Some(Condition(name, UrgentCondition(dice))) =>
          model withChar (_.focus(_.conditions)
            .modify(_.updated(i, Condition(name, UrgentCondition(prevPool)))))
        case _ => model
    case ToggleTalentCommand(_, ts) => model withChar (_.copy(talents = ts))
    case ChangeClassCommand(_, (oldClass, oldTal)) =>
      model withChar (char =>
        char.copy(
          coreTalent = oldTal,
          charClass = oldClass
        )
      )

  def rollAndDropCondition(index: Int, model: Model)(using
      RollGenerator
  ): Model =
    model match
      case m: Model =>
        m.character.conditions.lift(index) match
          case Some(cond @ Condition(name, UrgentCondition(dice))) =>
            val (rolledDice, leftoverPool) = dice.rollAndDrop
            val newCond = Condition(name, UrgentCondition(leftoverPool))
            model withChar (c =>
              c.focus(_.conditions).modify(_.updated(index, newCond))
            ) log s"Rolled ${rolledDice}"

          case _ =>
            model log s"Failed to roll and drop on condition index ${index}. No changes made, undo will also make no changes."

}
