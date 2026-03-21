package ftg.Character

import ftg.Character.PremadeBonds.LeftBond
import ftg.Character.PremadeBonds.RightBond

sealed trait BondDescription {
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
}

final case class Bond(pcName: CharacterName, bondDesc: BondDescription)
