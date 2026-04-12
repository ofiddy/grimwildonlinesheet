package ftg.page.elems

import ftg.Character.CharacterTrait.TraitSection
import tyrian.Html
import ftg.page.Msg
import tyrian.Html._
import ftg.Character.CharacterTrait.Trait
import ftg.Character.CharacterTrait.traitSystem
import tyrian.CSS
import monocle.Lens
import monocle.macros.GenLens
import monocle.syntax.AppliedLens
import ftg.page.Msg.SheetMsg
import ftg.command.ValueEditCommand
import ftg.command.CharacterLoc.TraitLoc
import ftg.Character.CharacterTrait.CustomTrait
import tyrian.Empty
import ftg.page.elems.ExitableInput.exitableTextInput
import PartialFunction._
import ftg.page.elems.SheetInputs.handleChangeFor

object TraitsDesiresInput {
  def renderTraits(ts: TraitSection): Html[Msg] = div(
    h3("Traits"),
    traitSelector(ts, "✔️ ", GenLens[TraitSection](_.twoYouAre._1)),
    traitSelector(ts, "✔️ ", GenLens[TraitSection](_.twoYouAre._2)),
    traitSelector(ts, "❌ ", GenLens[TraitSection](_.oneYouArent))
  )

  private def traitSelector(
      t: TraitSection,
      label: String,
      lens: Lens[TraitSection, Option[Trait]]
  ): Html[Msg] =
    val applied = AppliedLens(t, lens)
    div(styles(CSS.`display`("flex")))(
      p(label),
      select(
        onInput(s =>
          val traitSystemMap =
            traitSystem.map(t => (t.label.toLowerCase(), t)).toMap
          val newTrait: Option[Trait] = s match {
            case "none"                          => None
            case s if traitSystemMap.contains(s) => Some(traitSystemMap(s))
            case "custom"                        => Some(CustomTrait(""))
            case _                               => None
          }
          val newSection = applied.replace(newTrait)
          SheetMsg(ValueEditCommand(newSection, t, TraitLoc))
        )
      )(
        option(`value` := "none", selected := applied.get.isEmpty)("") +:
          traitSystem.map(t2 =>
            option(
              `value`  := t2.label.toLowerCase(),
              selected := applied.get.map(_ == t2).getOrElse(false)
            )(t2.label)
          ) :+ option(
            `value` := "custom",
            selected := (cond(applied.get) { case Some(CustomTrait(_)) =>
              true
            })
          )("Custom")
      ),
      applied.get match {
        case Some(CustomTrait(label)) =>
          exitableTextInput(`value` := label)(s =>
            handleChangeFor(TraitLoc)(
              t,
              applied.replace(Some(CustomTrait(s)))
            )
          )
        case _ => Empty
      }
    )
}
