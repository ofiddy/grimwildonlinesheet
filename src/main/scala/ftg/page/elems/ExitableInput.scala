package ftg.page.elems

import tyrian.Attr
import tyrian.Html
import tyrian.Html.*
import tyrian.Tyrian
import ftg.page.Msg

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
