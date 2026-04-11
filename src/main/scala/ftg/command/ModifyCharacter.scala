package ftg.command

import ftg.Character.StatPool
import ftg.Character.StatPool.diceRemainingLens
import ftg.Character.StatPool.markedLens
import ftg.Character.{Character => Character}
import monocle.syntax.all.focus
import ftg.Character.Condition
import ftg.Character.ShortTermCondition

object ModifyCharacter {
  def modify(cmd: EffectCommand, char: Character): Character = cmd match
    case v @ ValueEditCommand(_, _, find) =>
      find(char).replace(v.into)
    case EditStatPoolSizeCommand(newDice, _, find) =>
      find(char).andThen(diceRemainingLens).replace(newDice)
    case TogglePoolMarkedCommand(find) =>
      find(char).andThen(markedLens).modify(!_)
    case ToggleCommand(find) =>
      find(char).modify(!_)
    case AddCondition =>
      char
        .focus(_.conditions)
        .modify(_ :+ Condition(Some("WAhgoooo"), ShortTermCondition))

  def undo(cmd: EffectCommand, char: Character): Character = cmd match
    case ValueEditCommand(_, old, find) =>
      find(char).replace(old)
    case EditStatPoolSizeCommand(_, old, find) =>
      find(char).andThen(diceRemainingLens).replace(old)
    case TogglePoolMarkedCommand(find) =>
      find(char).andThen(markedLens).modify(!_)
    case ToggleCommand(find) =>
      find(char).modify(!_)
    case AddCondition => char.focus(_.conditions).modify(_.dropRight(1))
}
