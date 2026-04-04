package ftg.command

import ftg.Character.FromString.FromString
import ftg.command.CharacterLoc.Loc
import ftg.command.CharacterLoc.StatLocs.*

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

final case class RollStatCommand(f: StatLoc) extends PureCommand
final case class EditStatPoolSizeCommand(
    newValue: Int,
    oldValue: Int,
    f: StatLoc
) extends EffectCommand

object RollStatCommand {
  def RollBrawn: RollStatCommand    = RollStatCommand(Brawn)
  def RollAgility: RollStatCommand  = RollStatCommand(Agility)
  def RollWits: RollStatCommand     = RollStatCommand(Wits)
  def RollPresence: RollStatCommand = RollStatCommand(Presence)
}
