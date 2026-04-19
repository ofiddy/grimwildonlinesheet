package ftg.page

import ftg.command.CharCommand
import ftg.page.IoCmd.IoCmd

sealed trait Msg

object Msg {
  final case class SheetMsg(cmd: CharCommand)             extends Msg
  object BlurMsg                                          extends Msg
  object NoOpMsg                                          extends Msg
  final case class IoMsg(cmd: IoCmd)                      extends Msg
  final case class TryParseAndLoadCharacter(json: String) extends Msg
}
