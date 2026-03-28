package ftg.page

import ftg.command.CharCommand

sealed trait Msg

final case class SheetCommand(cmd: CharCommand) extends Msg
