package ftg.Character

import ftg.Character.CharacterDesire.DesireSection
import ftg.Character.CharacterTrait.TraitSection

import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}
import ftg.util.Util.opaqueTextRW
import ftg.Character.Wise.TripWise
import monocle.macros.GenLens

opaque type Wise = String

final case class Background(
    description: Option[String],
    wises: TripWise
) derives ReadWriter

case class CharacterDetails(
    backgrounds: (Background, Background),
    traits: TraitSection,
    desires: DesireSection
) derives ReadWriter

object Wise {
  extension (s: String) def wise: Wise     = s
  extension (w: Wise) def toString: String = w
  given RW[Wise]                           = opaqueTextRW(identity, identity)

  type TripWise = (Option[Wise], Option[Wise], Option[Wise])
  def firstWise  = GenLens[TripWise](_._1)
  def secondWise = GenLens[TripWise](_._2)
  def thirdWise  = GenLens[TripWise](_._3)
}

object Background {
  def apply(bg: String, ws: (Wise, Wise, Wise)): Background =
    Background(Some(bg), (Some(ws._1), Some(ws._2), Some(ws._3)))
}
