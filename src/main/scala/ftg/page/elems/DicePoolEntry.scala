package ftg.page.elems

import ftg.page.Msg
import tyrian.Attr
import tyrian.Html
import tyrian.Html._
import tyrian.Tyrian

object DicePoolEntry {
  def dicePoolEntry(
      attributes: Attr[Msg]*
  )(f: Int => Msg): Html[Msg] =
    input(
      List(
        `type` := "text",
        onKeyDown(e =>
          e match
            case e if e.code == "Enter" => Msg.BlurMsg
            case e
                if (e.keyCode >= '0' && e.keyCode <= '9') || e.code == "Backspace" =>
              Msg.NoOpMsg
            case e =>
              e.preventDefault()
              Msg.NoOpMsg
        ).noPreventDefault,
        onEvent(
          "blur",
          e =>
            e.target
              .asInstanceOf[Tyrian.HTMLInputElement]
              .value
              .toIntOption match
              case Some(value) => f(value)
              case None        => Msg.NoOpMsg
        )
      )
        ++ attributes*
    )
}
