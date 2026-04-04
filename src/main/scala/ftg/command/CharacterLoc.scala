package ftg.command

import monocle.syntax.AppliedPLens
import ftg.Character.CharacterName
import monocle.syntax.all._
import ftg.Character.Character as Character
import ftg.Character.PlayerName
import ftg.Character.DistinctiveFeatures
import ftg.Character.StatPool
import ftg.Character.BodyStats.*
import ftg.Character.MentalStats.*

object CharacterLoc {
  sealed trait Loc[T] {
    val lens: Character => AppliedPLens[Character, Character, T, T]
    def apply(char: Character) = lens(char)
  }

  case object CharacterNameLoc extends Loc[CharacterName] {
    val lens = _.focus(_.profile.characterName)
  }

  case object PlayerNameLoc extends Loc[PlayerName] {
    val lens = _.focus(_.profile.playerName)
  }

  case object DistinctiveFeaturesLoc extends Loc[DistinctiveFeatures] {
    val lens = _.focus(_.profile.distinctiveFeatures)
  }

  object StatLocs {
    sealed trait StatLoc extends Loc[StatPool]

    case object Brawn extends StatLoc {
      val lens =
        _.focus(_.stats.bodyStats).andThen(brawnLens)
    }
    case object Agility extends StatLoc {
      val lens =
        _.focus(_.stats.bodyStats).andThen(agilityLens)
    }
    case object Wits extends StatLoc {
      val lens =
        _.focus(_.stats.mentalStats).andThen(witsLens)
    }
    case object Presence extends StatLoc {
      val lens =
        _.focus(_.stats.mentalStats).andThen(presenceLens)
    }
  }

}
