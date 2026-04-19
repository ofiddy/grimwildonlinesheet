package ftg.Character

private type CharacterTrait

object CharacterTrait {
  val traitSystem =
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
    ).map(PremadeTrait(_))

  sealed trait Trait { def label: String }
  final case class PremadeTrait(label: String) extends Trait
  final case class CustomTrait(label: String)  extends Trait

  final case class TraitSection(
      twoYouAre: (Option[Trait], Option[Trait]),
      oneYouArent: Option[Trait]
  )
}

private type CharacterDesire

object CharacterDesire {
  val desireSystem =
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
    ).map(PremadeDesire(_))

  sealed trait Desire { def label: String }
  final case class PremadeDesire(label: String) extends Desire
  final case class CustomDesire(label: String)  extends Desire

  final case class DesireSection(
      twoYouWant: (Option[Desire], Option[Desire]),
      oneYouDont: Option[Desire]
  )
}
