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
import ftg.Character.Background
import ftg.Character.Story
import ftg.Character.Spark
import ftg.Character.CharacterTrait.TraitSection

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

  case object StoryLoc extends Loc[Story] {
    val lens = GenLens[Character](_.story)
  }

  case object SparkLoc extends Loc[Spark] {
    val lens = GenLens[Character](_.spark)
  }

  case object TraitLoc extends Loc[TraitSection] {
    val lens = GenLens[Character](_.details.traits)
  }

  object BackgroundLocs {
    sealed trait BackgroundLoc extends Loc[Background]

    case object BackgroundLoc1 extends BackgroundLoc {
      val lens = GenLens[Character](_.details.backgrounds._1)
    }

    case object BackgroundLoc2 extends BackgroundLoc {
      val lens = GenLens[Character](_.details.backgrounds._2)
    }
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
