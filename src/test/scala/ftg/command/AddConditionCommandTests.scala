package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.AddConditionCommand
import ftg.Character.ShortTermCondition
import monocle.syntax.all.focus
import ftg.Character.Condition
import ftg.DicePool.UnimplementedRollGenerator
import ftg.DicePool.RollGenerator
import ftg.page.Model

class AddConditionCommandTests extends AnyFlatSpec with should.Matchers {
  given RollGenerator = UnimplementedRollGenerator

  "AddConditionCommand" should "add to a conditions list on modify" in {
    val premadeChar = detherilStarren
    val cmd         = AddConditionCommand
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
    val cmd       = AddConditionCommand
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.conditions.length shouldBe premadeChar.conditions.length - 1
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val cmd         = AddConditionCommand
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))
    ).character shouldBe premadeChar
  }
}
