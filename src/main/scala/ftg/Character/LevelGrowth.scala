package ftg.Character

object LevelGrowth {
  trait Growable[A] {
    extension (i: A) {
      infix def `and every 3 levels`(f: A => A)(using level: Int): A = {
        val times = level % 3
        (1 to times).foldLeft(i)((x, _) => f(x))
      }
    }
  }

  given Growable[Int]
}
