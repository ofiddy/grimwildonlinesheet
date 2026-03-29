package ftg.command

import ftg.Character.Character as Character
import ftg.Character.CharacterName.*
import ftg.Character.PlayerName.*
import monocle.syntax.all._
import ftg.Character.DistinctiveFeatures.distinctiveFeatures

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(newName, _) =>
      char.focus(_.profile.characterName).replace(newName.charName)

    case ChangePlayerName(newName, _) =>
      char.focus(_.profile.playerName).replace(newName.playerName)

    case ChangeDistinctiveFeatures(newFeat, _) =>
      char
        .focus(_.profile.distinctiveFeatures)
        .replace(newFeat.distinctiveFeatures)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(_, oldName) =>
      char.focus(_.profile.characterName).replace(oldName)

    case ChangePlayerName(_, oldName) =>
      char.focus(_.profile.playerName).replace(oldName)

    case ChangeDistinctiveFeatures(_, old) =>
      char.focus(_.profile.distinctiveFeatures).replace(old)
}
