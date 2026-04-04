package ftg.command

import ftg.Character.CharacterName
import monocle.syntax.all._
import ftg.Character.Character as Character
import ftg.Character.PlayerName
import ftg.Character.DistinctiveFeatures
import ftg.Character.StatPool
import ftg.Character.BodyStats.*
import ftg.Character.MentalStats.*
import monocle.Lens
import monocle.macros.GenLens
import monocle.syntax.AppliedLens

object CharacterLoc {
  sealed trait Loc[T] {
    val lens: Lens[Character, T]
    def apply(char: Character) = AppliedLens(char, lens)
  }

  case object CharacterNameLoc extends Loc[CharacterName] {
    val lens = GenLens[Character](_.profile.characterName)
  }

  case object PlayerNameLoc extends Loc[PlayerName] {
    val lens = GenLens[Character](_.profile.playerName)
  }

  case object DistinctiveFeaturesLoc extends Loc[DistinctiveFeatures] {
    val lens = GenLens[Character](_.profile.distinctiveFeatures)
  }

  object StatLocs {
    sealed trait StatLoc extends Loc[StatPool]

    case object Brawn extends StatLoc {
      val lens =
        GenLens[Character](_.stats.bodyStats).andThen(brawnLens)
    }
    case object Agility extends StatLoc {
      val lens =
        GenLens[Character](_.stats.bodyStats).andThen(agilityLens)
    }
    case object Wits extends StatLoc {
      val lens =
        GenLens[Character](_.stats.mentalStats).andThen(witsLens)
    }
    case object Presence extends StatLoc {
      val lens =
        GenLens[Character](_.stats.mentalStats).andThen(presenceLens)
    }
  }

}
