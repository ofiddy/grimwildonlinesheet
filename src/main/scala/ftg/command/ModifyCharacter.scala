package ftg.command

import ftg.Character.Character as Character
import monocle.syntax.all.focus
import ftg.Character.StatPool
import ftg.Character.StatPool.diceRemainingLens
import ftg.Character.StatPool.markedLens

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case v @ ValueEditCommand(_, _, find) =>
      find(char).replace(v.into)
    case EditStatPoolSizeCommand(newDice, _, find) =>
      find(char).andThen(diceRemainingLens).replace(newDice)
    case TogglePoolMarkedCommand(find) =>
      find(char).andThen(markedLens).modify(!_)

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ValueEditCommand(_, old, find) =>
      find(char).replace(old)
    case EditStatPoolSizeCommand(_, old, find) =>
      find(char).andThen(diceRemainingLens).replace(old)
    case TogglePoolMarkedCommand(find) =>
      find(char).andThen(markedLens).modify(!_)
}
