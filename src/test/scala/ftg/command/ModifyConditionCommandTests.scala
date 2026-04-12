package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.DefaultCharacter.detherilStarren
import ftg.Character.Condition
import ftg.Character.ShortTermCondition
import ftg.Character.UrgentCondition
import ftg.DicePool.DicePool
import ftg.Character.PermanentCondition
import ftg.command.ModifyCharacter.modify
import ftg.page.Model
import ftg.DicePool.UnimplementedRollGenerator
import ftg.command.ModifyCharacter.undo
import ftg.DicePool.RollGenerator
import ftg.command.ModifyConditionCommand

class ModifyConditionCommandTests extends AnyFlatSpec with should.Matchers {
  val conds = (
    Condition(Some("Condition1"), ShortTermCondition),
    Condition(Some("Condition2"), UrgentCondition(DicePool(4))),
    Condition(Some("Condition3"), UrgentCondition(DicePool(6)))
  )

  given RollGenerator = UnimplementedRollGenerator

  "ModifyConditionCommand" should "overwrite only at provided location on modify" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val testCond = Condition(Some("Test"), PermanentCondition)
    val cmd =
      ModifyConditionCommand(testCond, conds._2, 1)
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.conditions.length shouldBe 3
    afterModify.conditions shouldBe List(conds._1, testCond, conds._3)
  }

  it should "overwrite only at provided location on undo" in {
    val testCond = Condition(Some("Test"), PermanentCondition)
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, testCond, conds._3))
    val cmd =
      ModifyConditionCommand(testCond, conds._2, 1)
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.conditions shouldBe List(conds._1, conds._2, conds._3)
  }

  it should "be reflective on modify and undo" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val testCond = Condition(Some("Test"), PermanentCondition)
    val cmd =
      ModifyConditionCommand(testCond, conds._2, 1)
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))
    ).character shouldBe premadeChar
  }
}
