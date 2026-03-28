package ftg.command

import ftg.Character.Character as Character
import ftg.Character.CharacterName.*
import monocle.syntax.all._

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(newName, oldName) =>
      char.focus(_.profile.characterName).replace(newName.charName)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ChangeName(newName, oldName) =>
      char.focus(_.profile.characterName).replace(oldName)

}
