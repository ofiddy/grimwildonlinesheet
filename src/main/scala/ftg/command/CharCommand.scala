package ftg.command

import ftg.Character.CharacterName
import ftg.Character.PlayerName

sealed trait CharCommand
sealed trait PureCommand   extends CharCommand
sealed trait EffectCommand extends CharCommand

final case class ChangeName(newName: String, oldName: CharacterName)
    extends EffectCommand
final case class ChangePlayerName(newName: String, oldName: PlayerName)
    extends EffectCommand
