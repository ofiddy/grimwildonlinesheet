package ftg.page

object IoCmd {
  sealed trait IoCmd

  case object SaveCharacterMsg extends IoCmd
}
