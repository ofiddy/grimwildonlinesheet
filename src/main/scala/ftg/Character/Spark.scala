package ftg.Character

opaque type Spark = Int

object Spark {
  def startingSpark: Spark = 2

  extension (s: Spark) {
    def gainSpark: Spark =
      if s >= 0 && s < 2 then s + 1 else 2

    def spendSpark: Option[Spark] =
      if s <= 0 then None else Some(s - 1)

    def toInt: Int = s
  }
}
