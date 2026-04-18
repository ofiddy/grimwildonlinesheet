package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.AddListElemCommand
import ftg.Character.ShortTermCondition
import monocle.syntax.all.focus
import ftg.Character.Condition
import ftg.DicePool.UnimplementedRollGenerator
import ftg.DicePool.RollGenerator
import ftg.page.Model
import ftg.command.CharacterListFactories.NewCondition
import ftg.command.CharacterLoc.ConditionsLoc

class AddConditionCommandTests extends AnyFlatSpec with should.Matchers {
  given RollGenerator = UnimplementedRollGenerator

  "AddConditionCommand" should "add to a conditions list on modify" in {
    val premadeChar = detherilStarren
    val cmd         = AddListElemCommand(NewCondition, ConditionsLoc)
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.conditions.length shouldBe premadeChar.conditions.length + 1
  }

  it should "remove from the conditions list on undo" in {
    val premadeChar = detherilStarren
      .focus(_.conditions)
      .replace(
        List(
          Condition(None, ShortTermCondition),
          Condition(None, ShortTermCondition)
        )
      )
    val cmd       = AddListElemCommand(NewCondition, ConditionsLoc)
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.conditions.length shouldBe premadeChar.conditions.length - 1
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val cmd         = AddListElemCommand(NewCondition, ConditionsLoc)
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))
    ).character shouldBe premadeChar
  }
}
