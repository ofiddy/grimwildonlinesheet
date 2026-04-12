package ftg.command

import ftg.Character.StatPool
import ftg.Character.StatPool.diceRemainingLens
import ftg.Character.StatPool.markedLens
import ftg.Character.{Character => Character}
import monocle.syntax.all.focus
import ftg.Character.Condition
import ftg.Character.ShortTermCondition
import ftg.Character.UrgentCondition
import ftg.DicePool.RollGenerator
import ftg.page.Model

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
      case AddConditionCommand =>
        model withChar (char =>
          char
            .focus(_.conditions)
            .modify(_ :+ Condition(Some("New Condition"), ShortTermCondition))
        )
      case ModifyConditionCommand(newCond, _, i) =>
        model withChar (char =>
          char.focus(_.conditions).modify(_.updated(i, newCond))
        )
      case RollAndDropConditionPoolCommand(i, _) =>
        rollAndDropCondition(i, model)

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
    case AddConditionCommand =>
      model withChar (char => char.focus(_.conditions).modify(_.dropRight(1)))
    case ModifyConditionCommand(_, oldCond, i) =>
      model withChar (char =>
        char.focus(_.conditions).modify(_.updated(i, oldCond))
      )
    case RollAndDropConditionPoolCommand(i, prevPool) =>
      model.character.conditions.lift(i) match
        case Some(Condition(name, UrgentCondition(dice))) =>
          model withChar (_.focus(_.conditions)
            .modify(_.updated(i, Condition(name, UrgentCondition(prevPool)))))
        case _ => model

  def rollAndDropCondition(index: Int, model: Model)(using
      RollGenerator
  ): Model =
    model match
      case Model(char, log) =>
        char.conditions.lift(index) match
          case Some(cond @ Condition(name, UrgentCondition(dice))) =>
            val (rolledDice, leftoverPool) = dice.rollAndDrop
            val newCond = Condition(name, UrgentCondition(leftoverPool))
            model withChar (c =>
              c.focus(_.conditions).modify(_.updated(index, newCond))
            ) log s"Rolled ${rolledDice}"

          case _ =>
            model log s"Failed to roll and drop on condition index ${index}. No changes made, undo will also make no changes."

}
