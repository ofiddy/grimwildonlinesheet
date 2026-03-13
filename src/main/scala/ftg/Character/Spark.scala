package ftg.Character

opaque type Spark = Int

extension (s: Spark) {
  def gainStory: Spark =
    if s >= 0 && s < 2 then s + 1 else 2

  def spendStory: Option[Spark] =
    if s <= 0 then None else Some(s - 1)
}

object Spark {
  def startingStory: Spark = 2
}
