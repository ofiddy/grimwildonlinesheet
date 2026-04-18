package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.Character.*
import ftg.DicePool.DicePool
import ftg.DicePool.RollGenerator
import ftg.DicePool.UnimplementedRollGenerator
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.DeleteConditionCommand
import ftg.command.ModifyCharacter.modify
import ftg.page.Model
import ftg.command.ModifyCharacter.undo

class DeleteConditionCommandTest extends AnyFlatSpec with should.Matchers {
  val conds = (
    Condition(Some("Condition1"), ShortTermCondition),
    Condition(Some("Condition2"), UrgentCondition(DicePool(4))),
    Condition(Some("Condition3"), UrgentCondition(DicePool(6)))
  )

  val premadeChar =
    detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))

  given RollGenerator = UnimplementedRollGenerator

  "DeleteConditionCommand" should "successfully delete an object at the start of the list" in {
    val cmd         = DeleteConditionCommand(conds._1, 0)
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.conditions.length shouldBe 2
    afterModify.conditions shouldBe List(conds._2, conds._3)
  }

  it should "be reflective on deleting an object at the start of the list" in {
    val cmd         = DeleteConditionCommand(conds._1, 0)
    val afterModify = undo(cmd, modify(cmd, Model(premadeChar, Nil))).character
    afterModify shouldBe premadeChar
  }

  it should "successfully delete an object at the end of the list" in {
    val cmd         = DeleteConditionCommand(conds._3, 2)
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.conditions.length shouldBe 2
    afterModify.conditions shouldBe List(conds._1, conds._2)
  }

  it should "be reflective on deleting an object at the end of the list" in {
    val cmd         = DeleteConditionCommand(conds._3, 2)
    val afterModify = undo(cmd, modify(cmd, Model(premadeChar, Nil))).character
    afterModify shouldBe premadeChar
  }

  it should "successfully delete an object in the middle of of the list" in {
    val cmd         = DeleteConditionCommand(conds._2, 1)
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.conditions.length shouldBe 2
    afterModify.conditions shouldBe List(conds._1, conds._3)
  }

  it should "be reflective on deleting an object in the middle of the list" in {
    val cmd         = DeleteConditionCommand(conds._2, 1)
    val afterModify = undo(cmd, modify(cmd, Model(premadeChar, Nil))).character
    afterModify shouldBe premadeChar
  }
}
