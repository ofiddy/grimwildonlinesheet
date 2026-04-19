package ftg.util

object Util {
  extension (s: String)
    def asOption: Option[String] = Some(s).filter(_.trim.nonEmpty)
}
