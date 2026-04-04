package ftg.command

import ftg.Character.Character as Character
import monocle.syntax.all.focus
import ftg.Character.StatPool
import ftg.Character.StatPool.diceRemainingLens

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case v @ ValueEditCommand(_, _, find) =>
      find(char).replace(v.into)
    case EditStatPoolSizeCommand(newDice, _, find) =>
      find(char).andThen(diceRemainingLens).replace(newDice)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ValueEditCommand(_, old, find) =>
      find(char).replace(old)
    case ftg.command.EditStatPoolSizeCommand(_, old, find) =>
      find(char).andThen(diceRemainingLens).replace(old)
}
