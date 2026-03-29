package ftg.command

import ftg.Character.Character as Character

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case v @ ValueEditCommand(_, _, find) =>
      find(char).replace(v.into)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ValueEditCommand(_, old, find) =>
      find(char).replace(old)
}
