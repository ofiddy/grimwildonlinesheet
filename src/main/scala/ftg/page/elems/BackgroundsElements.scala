package ftg.page.elems

import ftg.Character.*
import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import ftg.command.CharacterLoc.BackgroundLocs.BackgroundLoc
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.elems.SheetInputs.handleChangeFor
import ftg.util.Util.asOption
import monocle.syntax.all.focus
import ftg.Character.Wise.*
import monocle.Lens
import monocle.syntax.AppliedPLens

object BackgroundsElements {
  def renderBackgroundRows(
      char: Character,
      loc: BackgroundLoc
  ): Html[Msg] = {
    val bg = loc(char).get
    def bgRow(
        lens: AppliedPLens[Background, Background, Option[Wise], Option[Wise]]
    ): Html[Msg] =
      td(
        exitableTextInput(
          `value` := lens.get.map(_.toString).getOrElse("")
        )(s =>
          handleChangeFor(loc)(
            bg,
            lens.replace(s.asOption.map(_.wise))
          )
        )
      )

    tr(
      td(
        exitableTextInput(`value` := bg.description.getOrElse(""))(s =>
          handleChangeFor(loc)(bg, bg.copy(description = s.asOption))
        )
      ),
      bgRow(bg.focus(_.wises._1)),
      bgRow(bg.focus(_.wises._2)),
      bgRow(bg.focus(_.wises._3))
    )
  }
}
