package ftg.Character

opaque type CharacterName      = String
opaque type PlayerName         = String
opaque type DistinctiveFeature = String

final case class CharacterProfile(
    characterName: CharacterName,
    playerName: PlayerName,
    distinctiveFeatures: List[DistinctiveFeature]
)

object CharacterName {
  extension (s: String) def charName: CharacterName = s
}

object PlayerName {
  extension (s: String) def playerName: PlayerName = s
}

object DistinctiveFeature {
  extension (s: String) def distinctiveFeature: DistinctiveFeature = s
}
