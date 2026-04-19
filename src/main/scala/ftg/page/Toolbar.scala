package ftg.page

import tyrian.Html
import tyrian.Html._
import tyrian.CSS
import ftg.page.Msg.IoMsg
import ftg.page.IoCmd.SaveCharacterMsg
import ftg.page.IoCmd.LoadCharacterMsg
import ftg.page.IoCmd.NewBlankCharacterMsg

object Toolbar {
  def renderToolbar(model: Model): Html[Msg] =
    div(styles(CSS.`display`("flex")))(
      button(onClick(IoMsg(SaveCharacterMsg)))("Save Character"),
      input(
        `type`   := "file",
        `id`     := "character-upload",
        `label`  := "Load Character",
        `accept` := ".gw.json",
        onChange(_ => IoMsg(LoadCharacterMsg("character-upload")))
      ),
      button(onClick(IoMsg(NewBlankCharacterMsg)))("New Character")
    )
}
