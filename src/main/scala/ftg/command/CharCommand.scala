package ftg.command

import ftg.Character.Character as Character

sealed trait CharCommand
sealed trait PureCommand extends CharCommand
sealed trait EffectCommand extends CharCommand {
  def modify(char: Character): Character
  def undo(char: Character): Character
}
