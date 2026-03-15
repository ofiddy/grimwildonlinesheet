package ftg.Character

import ftg.Character.CharacterTrait.Trait

opaque type Wise = String

final case class Background(description: String, wises: (Wise, Wise, Wise))
opaque type Backgrounds = (Background, Background)

case class CharacterDetails(
    backgrounds: Backgrounds,
    traits: Trait
)
