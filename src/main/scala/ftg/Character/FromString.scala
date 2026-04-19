package ftg.Character

object FromString {
  trait FromString[T] {
    extension (s: String) def into: T
  }
}
