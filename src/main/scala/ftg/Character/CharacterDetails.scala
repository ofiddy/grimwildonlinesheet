package ftg.Character

import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.CharacterDesire.DesireSection

opaque type Wise = String

final case class Background(description: String, wises: (Wise, Wise, Wise))
opaque type Backgrounds = (Background, Background)

case class CharacterDetails(
    backgrounds: Backgrounds,
    traits: TraitSection,
    desires: DesireSection
)
