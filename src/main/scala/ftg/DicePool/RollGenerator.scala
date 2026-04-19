package ftg.DicePool

import scala.util.Random

trait RollGenerator {
  def roll(diceSize: DiceFace): DiceFace
}

object RandomRollGenerator extends RollGenerator {
  def roll(diceSize: DiceFace): DiceFace = Random.between(1, 7)
}

object UnimplementedRollGenerator extends RollGenerator {
  def roll(diceSize: DiceFace): DiceFace = ???
}
