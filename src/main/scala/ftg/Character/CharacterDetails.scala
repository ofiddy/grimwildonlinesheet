package ftg.Character

import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.CharacterDesire.DesireSection

opaque type Wise = String

final case class Background(
    description: Option[String],
    wises: (Option[Wise], Option[Wise], Option[Wise])
)

case class CharacterDetails(
    backgrounds: (Background, Background),
    traits: TraitSection,
    desires: DesireSection
)

object Wise {
  extension (s: String) def wise: Wise     = s
  extension (w: Wise) def toString: String = w
}

object Background {
  def apply(bg: String, ws: (Wise, Wise, Wise)): Background =
    Background(Some(bg), (Some(ws._1), Some(ws._2), Some(ws._3)))
}
