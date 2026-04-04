package ftg.page.elems

import ftg.page.Msg
import tyrian.Attr
import tyrian.Html
import tyrian.Html._
import tyrian.Tyrian

object ExitableTextArea {
  def exitableTextArea(
      attributes: Attr[Msg]*
  )(f: String => Msg)(start: String): Html[Msg] =
    textarea(
      List(
        `type` := "text",
        onEvent(
          "blur",
          e => f(e.target.asInstanceOf[Tyrian.HTMLInputElement].value)
        )
      )
        ++ attributes*
    )(start)
}
