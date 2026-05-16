package ftg.command

import ftg.DicePool.DicePool
import ftg.command.CharacterListFactories.ElemFactory
import ftg.command.CharacterLoc.Loc
import ftg.command.CharacterLoc.StatLocs._
import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.ClassTalents.TalentClass

sealed trait CharCommand
sealed trait PureCommand   extends CharCommand
sealed trait EffectCommand extends CharCommand

final case class ValueEditCommand[T](
    newValue: T,
    oldValue: T,
    loc: Loc[T]
) extends EffectCommand {
  def into: T = newValue
}

final case class RollStatCommand(f: StatLoc) extends PureCommand
final case class EditStatPoolSizeCommand(
    newValue: Int,
    oldValue: Int,
    loc: StatLoc
) extends EffectCommand

final case class TogglePoolMarkedCommand(
    loc: StatLoc
) extends EffectCommand

final case class ToggleCommand(
    loc: Loc[Boolean]
) extends EffectCommand

final case class AddListElemCommand[T](
    factory: ElemFactory[T],
    loc: Loc[List[T]]
) extends EffectCommand
final case class ModifyListElemCommand[T](
    newElem: T,
    oldElem: T,
    index: Int,
    loc: Loc[List[T]]
) extends EffectCommand
final case class RollAndDropConditionPoolCommand(i: Int, prevPool: DicePool)
    extends EffectCommand
final case class DeleteListElemCommand[T](
    oldCond: T,
    index: Int,
    loc: Loc[List[T]]
) extends EffectCommand

final case class ChangeClassCommand(
    newClass: TalentClass,
    oldState: (TalentClass, Talent)
) extends EffectCommand

object RollStatCommand {
  def RollBrawn: RollStatCommand    = RollStatCommand(Brawn)
  def RollAgility: RollStatCommand  = RollStatCommand(Agility)
  def RollWits: RollStatCommand     = RollStatCommand(Wits)
  def RollPresence: RollStatCommand = RollStatCommand(Presence)
}

final case class ToggleTalentCommand(
    desc: TalentDescriptor,
    allTalents: List[Talent]
) extends EffectCommand
