package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.DefaultCharacter.detherilStarren
import ftg.Character.Condition
import ftg.Character.ShortTermCondition
import ftg.Character.UrgentCondition
import ftg.DicePool.DicePool
import ftg.command.ModifyCharacter.modify
import ftg.page.Model
import ftg.command.ModifyCharacter.undo
import ftg.command.RollAndDropConditionPoolCommand
import ftg.dice.test.RiggedCroupier
import ftg.DicePool.RollResult

class RollAndDropConditionPoolCommandTests
    extends AnyFlatSpec
    with should.Matchers {

  val cond2StartingPool = DicePool(4)

  val conds = (
    Condition(Some("Condition1"), ShortTermCondition),
    Condition(Some("Condition2"), UrgentCondition(cond2StartingPool)),
    Condition(Some("Condition3"), UrgentCondition(DicePool(6)))
  )

  "RollAndDropConditionPoolCommand" should "reduce the dice pool if grims are rolled" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val cmd =
      RollAndDropConditionPoolCommand(1, cond2StartingPool)
    val rollSequence = RiggedCroupier(List(1, 2, 3, 6))
    val afterModify =
      modify(cmd, Model(premadeChar, Nil))(using rollSequence).character
    afterModify.conditions shouldBe List(
      conds._1,
      Condition(Some("Condition2"), UrgentCondition(DicePool(1))),
      conds._3
    )
  }

  it should "maintain the dice pool if no grims are rolled" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val cmd =
      RollAndDropConditionPoolCommand(1, cond2StartingPool)
    val rollSequence = RiggedCroupier(List(4, 5, 6, 6))
    val afterModify =
      modify(cmd, Model(premadeChar, Nil))(using rollSequence).character
    afterModify.conditions shouldBe premadeChar.conditions
  }

  it should "log the roll on modify" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val cmd =
      RollAndDropConditionPoolCommand(1, cond2StartingPool)
    val rollSequence = RiggedCroupier(List(1, 2, 3, 6))
    val log =
      modify(cmd, Model(premadeChar, Nil))(using rollSequence).log
    val expectedResult = RollResult
      .apply(
        Map
          .apply(
            1 -> 1,
            2 -> 1,
            3 -> 1,
            6 -> 1
          )
      )
      .toString()
    log.length shouldBe 1
    log.head.toString should include(expectedResult)
  }

  it should "be reflective on modify and undo" in {
    val premadeChar =
      detherilStarren.copy(conditions = List(conds._1, conds._2, conds._3))
    val cmd =
      RollAndDropConditionPoolCommand(1, cond2StartingPool)
    val rollSequence = RiggedCroupier(List(1, 1, 6, 6))
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))(using rollSequence)
    ).character shouldBe premadeChar
  }
}
