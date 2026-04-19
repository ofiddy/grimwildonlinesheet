package ftg.page.elems

import ftg.page.Msg
import tyrian.Attr
import tyrian.Html
import tyrian.Html._
import tyrian.Tyrian

object ExitableInput {
  def exitableTextInput(
      attributes: Attr[Msg]*
  )(f: String => Msg): Html[Msg] =
    input(
      List(
        `type` := "text",
        onKeyDown(e =>
          if e.code == "Enter" then Msg.BlurMsg // Thereby triggering onBlur too
          else Msg.NoOpMsg
        ).noPreventDefault,
        onEvent(
          "blur",
          e => f(e.target.asInstanceOf[Tyrian.HTMLInputElement].value)
        )
      )
        ++ attributes*
    )
}
