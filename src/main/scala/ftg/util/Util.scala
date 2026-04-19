package ftg.util

import upickle.default.{ReadWriter => RW}

object Util {
  extension (s: String)
    def asOption: Option[String] = Some(s).filter(_.trim.nonEmpty)

  def opaqueTextRW[S](f1: S => String, f2: String => S): RW[S] =
    opaqueRW[S, String](f1, f2)

  def opaqueRW[O, T](f1: O => T, f2: T => O)(using RW[T]): RW[O] =
    upickle.readwriter[T].bimap[O](f1, f2)

  def opaqueIntRW[I](f1: I => Int, f2: Int => I): RW[I] =
    opaqueRW[I, Int](f1, f2)
}
