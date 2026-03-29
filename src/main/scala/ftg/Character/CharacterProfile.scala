package ftg.Character

import ftg.Character.FromString.FromString

opaque type CharacterName       = String
opaque type PlayerName          = String
opaque type DistinctiveFeatures = String

final case class CharacterProfile(
    characterName: CharacterName,
    playerName: PlayerName,
    distinctiveFeatures: DistinctiveFeatures
)

object CharacterName {
  extension (c: CharacterName) def label: String        = c
  extension (s: String) def intoCharName: CharacterName = s.into

  given into: FromString[CharacterName] with {
    extension (s: String) override def into: CharacterName = s
  }
}

object PlayerName {
  extension (c: PlayerName) def label: String          = c
  extension (s: String) def intoPlayerName: PlayerName = s.into

  given into: FromString[PlayerName] with {
    extension (s: String) override def into: PlayerName = s
  }
}

object DistinctiveFeatures {
  extension (c: DistinctiveFeatures) def label: String = c
  extension (s: String)
    def intoDistinctiveFeatures: DistinctiveFeatures = s.into

  given into: FromString[DistinctiveFeatures] with {
    extension (s: String) override def into: DistinctiveFeatures = s
  }
}
