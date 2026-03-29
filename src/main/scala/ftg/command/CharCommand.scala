package ftg.command

import ftg.Character.FromString.FromString
import ftg.command.CharacterLoc.Loc

sealed trait CharCommand
sealed trait PureCommand   extends CharCommand
sealed trait EffectCommand extends CharCommand

final case class ValueEditCommand[T: FromString](
    newValue: String,
    oldValue: T,
    lens: Loc[T]
) extends EffectCommand {
  def into: T = newValue.into
}
