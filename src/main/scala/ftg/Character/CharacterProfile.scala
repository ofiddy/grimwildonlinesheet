package ftg.Character

opaque type CharacterName       = String
opaque type PlayerName          = String
opaque type DistinctiveFeatures = String

final case class CharacterProfile(
    characterName: CharacterName,
    playerName: PlayerName,
    distinctiveFeatures: DistinctiveFeatures
)

object CharacterName {
  extension (s: String) def charName: CharacterName = s
  extension (c: CharacterName) def label: String    = c
}

object PlayerName {
  extension (s: String) def playerName: PlayerName = s
  extension (c: PlayerName) def label: String      = c
}

object DistinctiveFeatures {
  extension (s: String) def distinctiveFeatures: DistinctiveFeatures = s
  extension (c: DistinctiveFeatures) def label: String               = c
}
