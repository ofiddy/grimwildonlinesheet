package ftg.command

import ftg.Character.FromString.FromString
import ftg.command.CharacterLoc.Loc
import ftg.Character.CharacterBaseStats
import ftg.Character.StatPool
import ftg.command.RollStatCommand.StatLoc

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

object RollStatCommand {
  trait StatLoc { def apply(c: CharacterBaseStats): StatPool }
  case object Brawn extends StatLoc {
    def apply(c: CharacterBaseStats): StatPool = c.bodyStats.brawn
  }
  case object Agility extends StatLoc {
    def apply(c: CharacterBaseStats): StatPool = c.bodyStats.agility
  }
  case object Wits extends StatLoc {
    def apply(c: CharacterBaseStats): StatPool = c.mentalStats.wits
  }
  case object Presence extends StatLoc {
    def apply(c: CharacterBaseStats): StatPool = c.mentalStats.presence
  }

  def RollBrawn: RollStatCommand    = RollStatCommand(Brawn)
  def RollAgility: RollStatCommand  = RollStatCommand(Agility)
  def RollWits: RollStatCommand     = RollStatCommand(Wits)
  def RollPresence: RollStatCommand = RollStatCommand(Presence)
}
