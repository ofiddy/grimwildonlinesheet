package ftg.command

import ftg.Character.BodyStats._
import ftg.Character.CharacterName
import ftg.Character.DistinctiveFeatures
import ftg.Character.MentalStats._
import ftg.Character.PlayerName
import ftg.Character.StatPool
import ftg.Character.{Character => Character}
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

  object HarmLocs {
    sealed trait HarmLoc extends Loc[Boolean]
    case object Bloodied extends HarmLoc {
      val lens = GenLens[Character](_.stats.bodyStats).andThen(bloodiedLens)
    }
    case object Rattled extends HarmLoc {
      val lens = GenLens[Character](_.stats.mentalStats).andThen(rattledLens)
    }
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
