package ftg.DicePool

type DiceFace = Int

final case class RollResult(diceResults: Map[DiceFace, Int]) {
  def apply(face: DiceFace): Int = diceResults.getOrElse(face, 0)
  def grims: Int   = diceResults(1) + diceResults(2) + diceResults(3)
  def messy: Int   = diceResults(4) + diceResults(5)
  def perfect: Int = diceResults(6)
}
