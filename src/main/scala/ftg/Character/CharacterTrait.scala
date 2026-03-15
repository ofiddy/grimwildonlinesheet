package ftg.Character

import ftg.Character.SelectableDetails.produceDetailSystem

private type CharacterTrait

object CharacterTrait {
  val traitSystem = produceDetailSystem[CharacterTrait](
    List(
      "Brave",
      "Caring",
      "Confident",
      "Curious",
      "Gentle",
      "Honest",
      "Honourable",
      "Persistent",
      "Protective",
      "Quiet",
      "Rash",
      "Stubborn"
    )
  )

  opaque type PremadeTrait = traitSystem.Premade
  opaque type CustomTrait  = traitSystem.Custom
  opaque type Trait        = traitSystem.Detail

  final case class TraitSection(
      twoYouAre: List[Trait],
      oneYouArent: Option[Trait]
  )
}

private type CharacterDesire

object CharacterDesire {
  val desireSystem = produceDetailSystem[CharacterDesire](
    List(
      "Belonging",
      "Glory",
      "Harmony",
      "Honour",
      "Justice",
      "Knowledge",
      "Love",
      "Power",
      "Renown",
      "Thrills",
      "Wealth",
      "Wisdom"
    )
  )

  opaque type PremadeDesire = desireSystem.Premade
  opaque type CustomDesire  = desireSystem.Custom
  opaque type Desire        = desireSystem.Detail

  final case class DesireSection(
      twoYouWant: List[Desire],
      oneYouDont: Option[Desire]
  )
}
