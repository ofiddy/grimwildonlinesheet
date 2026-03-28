package ftg.command

import ftg.Character.CharacterName

sealed trait CharCommand
sealed trait PureCommand   extends CharCommand
sealed trait EffectCommand extends CharCommand

final case class ChangeName(newName: String, oldName: CharacterName)
    extends EffectCommand
