package ftg.page.elems.inputPatterns

import ftg.command.CharacterLoc.Loc
import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import ftg.page.Msg.SheetMsg
import ftg.command.ValueEditCommand
import ftg.page.elems.ExitableInput.exitableTextInput
import tyrian.Empty
import ftg.page.elems.SheetInputs.handleChangeFor

trait LargelyPrefilledSection[T] {
  def extractCustomLabel(t: Option[T]): Option[String]
  def sectionMap: Map[String, T]
  def custom(s: String): T
}

def prefilledOrCustomSelector[T](
    char: ftg.Character.Character,
    label: String,
    loc: Loc[Option[T]]
)(using T: LargelyPrefilledSection[T]): Html[Msg] = {
  val applied = loc(char)
  div(cls := "horizontal")(
    p(label),
    select(
      onInput(s =>
        val traitSystemMap = T.sectionMap
        val newTrait: Option[T] = s match {
          case "none"                          => None
          case s if traitSystemMap.contains(s) => Some(traitSystemMap(s))
          case "custom"                        => Some(T.custom(""))
          case _                               => None
        }
        SheetMsg(ValueEditCommand(newTrait, applied.get, loc))
      )
    )(
      option(`value` := "none", selected := applied.get.isEmpty)("") +:
        T.sectionMap.toList.map((label, obj) =>
          option(
            `value`  := label.toLowerCase(),
            selected := applied.get.map(_ == obj).getOrElse(false)
          )(label.capitalize)
        ) :+ option(
          `value`  := "custom",
          selected := T.extractCustomLabel(applied.get).nonEmpty
        )("Custom")
    ),
    T.extractCustomLabel(applied.get) match {
      case Some(label) =>
        exitableTextInput(`value` := label)(s =>
          handleChangeFor(loc)(
            applied.get,
            Some(T.custom(s))
          )
        )
      case _ => Empty
    }
  )
}
