package ftg.page.cmds

import cats.effect.Async
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.document
import tyrian.Cmd
import cats.effect.IO
import ftg.Character.{Character => Character}
import org.scalajs.dom.html

object GwCmds {
  def unfocusCurrentblur[F[_]: Async]: Cmd.SideEffect[F, Unit] =
    Cmd.SideEffect {
      document.activeElement match
        case i: HTMLInputElement => i.blur()
    }

  def downloadCharacter(char: Character): Cmd.SideEffect[IO, Unit] =
    Cmd.SideEffect {
      import org.scalajs.dom
      import scala.scalajs.js.URIUtils.encodeURIComponent

      val json = upickle.write(char)

      val elem =
        dom.document.createElement("a").asInstanceOf[html.Anchor]
      elem.setAttribute(
        "href",
        "data:text/plain;charset=utf-8," + encodeURIComponent(json)
      )
      elem.setAttribute("download", "char.gw.json")
      elem.style.display = "none"

      dom.document.body.appendChild(elem): Unit
      elem.click()
      document.body.removeChild(elem): Unit
    }
}
