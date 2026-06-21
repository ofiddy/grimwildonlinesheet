package ftg.page.cmds

import cats.effect.Async
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.document
import tyrian.Cmd
import cats.effect.IO
import ftg.Character.{Character => Character}
import org.scalajs.dom.html
import tyrian.cmds.FileReader
import tyrian.cmds.FileReader.Result
import ftg.page.Msg

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

  def loadCharacter(id: String): Cmd[IO, Msg] =
    FileReader.readText(id) {
      case tyrian.cmds.FileReader.Result.Error(message) => Msg.NoOpMsg
      case Result.File(name, path, data) => Msg.TryParseAndLoadCharacter(data)
      case Result.NoFile(message)        => Msg.NoOpMsg
    }

  def openLoadCharacter(): Cmd[IO, Msg] = Cmd.SideEffect {
    document.getElementById("character-upload").asInstanceOf[html.Input].click()
  }

  def openModal[F[_]: Async]: Cmd.SideEffect[F, Unit] = Cmd.SideEffect {
    document.getElementById("modal") match {
      case d: html.Dialog => d.showModal()
    }
  }

  def closeModal[F[_]: Async]: Cmd.SideEffect[F, Unit] = Cmd.SideEffect {
    document.getElementById("modal") match {
      case d: html.Dialog => d.close()
    }
  }
}
