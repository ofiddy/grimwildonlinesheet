package ftg.page.elems.inputPatterns

import ftg.command.CharacterLoc.Loc
import monocle.Lens
import monocle.syntax.AppliedLens
import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import tyrian.CSS
import ftg.page.Msg.SheetMsg
import ftg.command.ValueEditCommand
import ftg.page.elems.ExitableInput.exitableTextInput
import tyrian.Empty
import ftg.page.elems.SheetInputs.handleChangeFor

trait LargelyPrefilledSection[TS, T] {
  extension (a: TS) {
    def sectionMap: Map[String, T]
    def custom(s: String): T
    def loc: Loc[TS]
    def extractCustomLabel(t: Option[T]): Option[String]
  }
}

def prefilledOrCustomSelector[TS, T](
    t: TS,
    label: String,
    lens: Lens[TS, Option[T]]
)(using TS: LargelyPrefilledSection[TS, T]): Html[Msg] = {
  val applied = AppliedLens(t, lens)
  div(styles(CSS.`display`("flex")))(
    p(label),
    select(
      onInput(s =>
        val traitSystemMap = t.sectionMap
        val newTrait: Option[T] = s match {
          case "none"                          => None
          case s if traitSystemMap.contains(s) => Some(traitSystemMap(s))
          case "custom"                        => Some(t.custom(""))
          case _                               => None
        }
        val newSection = applied.replace(newTrait)
        SheetMsg(ValueEditCommand(newSection, t, t.loc))
      )
    )(
      option(`value` := "none", selected := applied.get.isEmpty)("") +:
        t.sectionMap.toList.map((label, obj) =>
          option(
            `value`  := label.toLowerCase(),
            selected := applied.get.map(_ == obj).getOrElse(false)
          )(label.capitalize)
        ) :+ option(
          `value`  := "custom",
          selected := t.extractCustomLabel(applied.get).nonEmpty
        )("Custom")
    ),
    t.extractCustomLabel(applied.get) match {
      case Some(label) =>
        exitableTextInput(`value` := label)(s =>
          handleChangeFor(t.loc)(
            t,
            applied.replace(Some(t.custom(s)))
          )
        )
      case _ => Empty
    }
  )
}
