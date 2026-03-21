package ftg.Character

import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.CharacterDesire.DesireSection

opaque type Wise = String

final case class Background(description: String, wises: (Wise, Wise, Wise))

case class CharacterDetails(
    backgrounds: (Option[Background], Option[Background]),
    traits: TraitSection,
    desires: DesireSection
)

object Wise {
  extension (s: String) def wise: Wise = s
}
