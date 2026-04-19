package ftg.DicePool

final case class DicePool(diceRemaining: Int)

trait PoolRollable[A] {
  extension (a: A) {
    def roll(using RollGenerator): RollResult
    def drop(n: Int): A

    def rollAndDrop(using RollGenerator): (RollResult, A) = {
      val diceRoll = a.roll
      (diceRoll, drop(diceRoll.grims))
    }
  }
}

object DicePool {
  given PoolRollable[DicePool] with {
    extension (d: DicePool) {
      def roll(using r: RollGenerator): RollResult =
        RollResult(
          Range(0, d.diceRemaining)
            .map(_ => r.roll(6))
            .groupBy(identity)
            .view
            .mapValues(_.length)
            .toMap
        )

      def drop(n: Int): DicePool = DicePool(Math.max(d.diceRemaining - n, 0))
    }
  }
}
