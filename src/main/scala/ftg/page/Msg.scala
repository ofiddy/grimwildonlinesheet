package ftg.page

import ftg.command.CharCommand
import ftg.page.IoCmd.IoCmd
import ftg.Talent.ClassTalents.TalentClass

sealed trait Msg

object Msg {
  final case class SheetMsg(cmd: CharCommand)             extends Msg
  object BlurMsg                                          extends Msg
  object NoOpMsg                                          extends Msg
  final case class IoMsg(cmd: IoCmd)                      extends Msg
  final case class TryParseAndLoadCharacter(json: String) extends Msg
  case object OpenTalentModal                             extends Msg
  case object CloseModal                                  extends Msg
  final case class EditTalentModal(search: String)        extends Msg
  final case class EditTalentModalFilter(talentClass: Option[TalentClass])
      extends Msg
}
