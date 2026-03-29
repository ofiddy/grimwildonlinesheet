package ftg.command

import ftg.Character.Character as Character
import ftg.Character.CharacterName.*
import ftg.Character.PlayerName.*
import monocle.syntax.all._

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(newName, oldName) =>
      char.focus(_.profile.characterName).replace(newName.charName)

    case ChangePlayerName(newName, oldName) =>
      char.focus(_.profile.playerName).replace(newName.playerName)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(newName, oldName) =>
      char.focus(_.profile.characterName).replace(oldName)

    case ChangePlayerName(newName, oldName) =>
      char.focus(_.profile.playerName).replace(oldName)

}
