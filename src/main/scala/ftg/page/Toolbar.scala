package ftg.page

import tyrian.Html
import tyrian.Html._
import ftg.page.Msg.IoMsg
import ftg.page.IoCmd.SaveCharacterMsg
import ftg.page.IoCmd.LoadCharacterMsg
import ftg.page.IoCmd.NewBlankCharacterMsg
import ftg.page.IoCmd.OpenLoadCharacterDialogMsg

object Toolbar {
  def renderToolbar(model: Model): Html[Msg] =
    div(id := "toolbar")(
      button(onClick(IoMsg(SaveCharacterMsg)), cls := "toolbar-button")(
        "Save Character"
      ),
      button(
        onClick(IoMsg(OpenLoadCharacterDialogMsg)),
        cls := "toolbar-button"
      )(
        "Load Character"
      ),
      input(
        `type`   := "file",
        `id`     := "character-upload",
        `label`  := "Load Character",
        `accept` := ".gw.json",
        cls      := "toolbar-button",
        onChange(_ => IoMsg(LoadCharacterMsg("character-upload")))
      ),
      button(onClick(IoMsg(NewBlankCharacterMsg)), cls := "toolbar-button")(
        "New Character"
      )
    )
}
