package ftg.Character

opaque type CharacterName      = String
opaque type PlayerName         = String
opaque type DistinctiveFeature = String

final case class CharacterProfile(
    characterName: CharacterName,
    playerName: PlayerName,
    distinctiveFeatures: List[DistinctiveFeature]
)
