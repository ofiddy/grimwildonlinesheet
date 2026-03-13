package ftg.DicePool

final case class DicePool(diceRemaining: Int)

trait PoolRollable[A] {
  extension (a: A) {
    def roll(): RollResult
    def drop(x: Int): Unit

    def rollAndDrop(): RollResult = {
      val diceRoll = a.roll()
      drop(diceRoll.messy)
      diceRoll
    }
  }
}
