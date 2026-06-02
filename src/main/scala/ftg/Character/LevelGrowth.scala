package ftg.Character

object LevelGrowth {
  def everyNLevels[A](n: Int, i: A, f: A => A)(using level: Int): A = {
    val times = level / n
    (1 to times).foldLeft(i)((x, _) => f(x))
  }

  extension (i: Int) {
    infix def `and every 3 levels`(f: Int => Int)(using level: Int): Int = {
      everyNLevels(3, i, f)
    }

    infix def `and every 2 levels`(f: Int => Int)(using level: Int): Int =
      everyNLevels(2, i, f)
  }
}
