package ftg.page

object IoCmd {
  sealed trait IoCmd

  case object SaveCharacterMsg                  extends IoCmd
  final case class LoadCharacterMsg(id: String) extends IoCmd
  case object NewBlankCharacterMsg              extends IoCmd
  case object OpenLoadCharacterDialogMsg        extends IoCmd
}
