package ftg.Character

import upickle.default.{ReadWriter => RW}
import ftg.util.Util.opaqueIntRW

opaque type Spark = Int

object Spark {
  def startingSpark: Spark = 2
  def maxSpark: Spark      = 2

  extension (s: Spark) {
    def gainSpark: Spark =
      if s >= 0 && s < 2 then s + 1 else 2

    def spendSpark: Spark =
      if s <= 0 then s else s - 1

    def toInt: Int = s
  }

  given RW[Spark] = opaqueIntRW[Spark](identity, identity)
}
