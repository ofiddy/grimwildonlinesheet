package ftg.Character

import ftg.Character.FromString.FromString
import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}
import ftg.util.Util.opaqueTextRW

opaque type CharacterName       = String
opaque type PlayerName          = String
opaque type DistinctiveFeatures = String

final case class CharacterProfile(
    characterName: CharacterName,
    playerName: PlayerName,
    distinctiveFeatures: DistinctiveFeatures
) derives ReadWriter

object CharacterName {
  extension (c: CharacterName) def label: String        = c
  extension (s: String) def intoCharName: CharacterName = s.into

  given into: FromString[CharacterName] with {
    extension (s: String) override def into: CharacterName = s
  }

  given RW[CharacterName] = opaqueTextRW(identity, identity)
}

object PlayerName {
  extension (c: PlayerName) def label: String          = c
  extension (s: String) def intoPlayerName: PlayerName = s.into

  given into: FromString[PlayerName] with {
    extension (s: String) override def into: PlayerName = s
  }

  given RW[PlayerName] = opaqueTextRW(identity, identity)
}

object DistinctiveFeatures {
  extension (c: DistinctiveFeatures) def label: String = c
  extension (s: String)
    def intoDistinctiveFeatures: DistinctiveFeatures = s.into

  given into: FromString[DistinctiveFeatures] with {
    extension (s: String) override def into: DistinctiveFeatures = s
  }

  given RW[DistinctiveFeatures] = opaqueTextRW(identity, identity)
}
