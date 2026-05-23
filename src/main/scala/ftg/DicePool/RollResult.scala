package ftg.DicePool

type DiceFace = Int

final case class RollResult(diceResults: Map[DiceFace, Int]) {
  def apply(face: DiceFace): Int = diceResults.getOrElse(face, 0)
  def grims: Int                 = this(1) + this(2) + this(3)
  def messy: Int                 = this(4) + this(5)
  def perfect: Int               = this(6)
  def hits: Int                  = messy + perfect

  override def toString: String =
    s"RollResult(${diceResults.toList.sortBy((face, _) => face).map((face, n) => (face.toString() + " ") * n).mkString})"
}
