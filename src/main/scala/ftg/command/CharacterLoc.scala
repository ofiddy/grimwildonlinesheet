package ftg.command

import monocle.syntax.AppliedPLens
import ftg.Character.CharacterName
import monocle.syntax.all._
import ftg.Character.Character as Character
import ftg.Character.PlayerName
import ftg.Character.DistinctiveFeatures

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
}
