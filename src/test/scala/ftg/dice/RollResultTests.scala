package ftg.dice.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.DicePool.RollGenerator
import scala.collection.mutable
import ftg.DicePool.DiceFace
import _root_.ftg.DicePool.DicePool

class RiggedCroupier(val diceList: List[Int]) extends RollGenerator {

  override def roll(diceSize: DiceFace): DiceFace = diceTrail.pop()
  private val diceTrail                           = mutable.Stack.from(diceList)
}

class RollResultTests extends AnyFlatSpec with should.Matchers {
  "RollResult" should "correctly tabulate the provided dice" in {
    val rolls = List(1, 5, 6, 4, 4, 1, 5)
    given RiggedCroupier(rolls)
    val result = DicePool(7).roll
    result(1) shouldBe 2
    result(2) shouldBe 0
    result(3) shouldBe 0
    result(4) shouldBe 2
    result(5) shouldBe 2
    result(6) shouldBe 1
  }
}
