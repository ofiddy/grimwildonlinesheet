package ftg.page.cmds

import cats.effect.Async
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.document
import tyrian.Cmd

object GwCmds {
  def unfocusCurrentblur[F[_]: Async]: Cmd.SideEffect[F, Unit] =
    Cmd.SideEffect {
      document.activeElement match
        case i: HTMLInputElement => i.blur()
    }
}
