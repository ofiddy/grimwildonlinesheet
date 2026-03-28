package ftg.page.elems

import tyrian.Attr
import tyrian.Html
import tyrian.Html.*
import tyrian.Tyrian

object ExitableInput {
  def exitableTextInput[M](
      attributes: Attr[M]*
  )(f: String => M)(using noOpMsg: M): Html[M] =
    input(
      List(
        `type` := "text",
        onKeyDown(e =>
          if e.code == "Enter" then
            f(e.target.asInstanceOf[Tyrian.HTMLInputElement].value)
          else noOpMsg
        ).noPreventDefault,
        onEvent(
          "blur",
          e => f(e.target.asInstanceOf[Tyrian.HTMLInputElement].value)
        )
      )
        ++ attributes*
    )
}
