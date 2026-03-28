package ftg.page.cmds

import tyrian.Cmd
import cats.effect.Async

import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.document

object GwCmds {
  def unfocusCurrentblur[F[_]: Async]: Cmd.SideEffect[F, Unit] =
    Cmd.SideEffect {
      document.activeElement match
        case i: HTMLInputElement => i.blur()
    }
}
