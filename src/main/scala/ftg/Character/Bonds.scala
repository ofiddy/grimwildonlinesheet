package ftg.Character

import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}
import ftg.Character.PremadeBonds.LeftBond
import ftg.Character.PremadeBonds.RightBond
import ftg.util.Util.opaqueTextRW

sealed trait BondDescription derives ReadWriter {
  def label: String
}
final case class CustomBond(label: String) extends BondDescription
final case class PremadeBond(labelLeft: LeftBond, labelRight: RightBond)
    extends BondDescription {
  def label: String = s"${labelLeft} ${labelRight}"
}

object PremadeBonds {
  opaque type LeftBond  = String
  opaque type RightBond = String

  def premadeLeftBonds: List[LeftBond] = List(
    "Deep",
    "Complex",
    "Growing",
    "Lowkey",
    "Playful",
    "Tense"
  )

  def premadeRightBonds: List[RightBond] = List(
    "Affection",
    "Camaraderie",
    "Curiosity",
    "Doubts",
    "Respect",
    "Rivalry"
  )

  object LeftBond {
    extension (s: String) {
      def intoLeftBond: LeftBond = s
    }

    extension (l: LeftBond) {
      def toString: String = l
    }

    given RW[LeftBond] = opaqueTextRW(identity, identity)
  }

  object RightBond {
    extension (s: String) {
      def intoRightBond: RightBond = s
    }

    extension (l: RightBond) {
      def toString: String = l
    }

    given RW[RightBond] = opaqueTextRW(identity, identity)
  }
}

final case class Bond(pcName: CharacterName, bondDesc: BondDescription)
    derives ReadWriter
