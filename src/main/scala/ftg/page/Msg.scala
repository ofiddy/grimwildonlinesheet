package ftg.page

import ftg.command.CharCommand

sealed trait Msg

object Msg {
  final case class SheetMsg(cmd: CharCommand) extends Msg
  object NoOpMsg                              extends Msg
}
