package ftg.page

import tyrian.Html
import tyrian.Html._
import tyrian.CSS
import ftg.page.Msg.IoMsg
import ftg.page.IoCmd.SaveCharacterMsg

object Toolbar {
  def renderToolbar(model: Model): Html[Msg] =
    div(styles(CSS.`display`("flex")))(
      button(onClick(IoMsg(SaveCharacterMsg)))("Save Character"),
      button("Load Character"),
      button("New Character")
    )
}
