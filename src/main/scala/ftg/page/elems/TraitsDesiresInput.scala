package ftg.page.elems

import ftg.Character.CharacterDesire.CustomDesire
import ftg.Character.CharacterDesire.Desire
import ftg.Character.CharacterDesire.DesireSection
import ftg.Character.CharacterDesire.desireSystem
import ftg.Character.CharacterTrait.CustomTrait
import ftg.Character.CharacterTrait.Trait
import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.CharacterTrait.traitSystem
import ftg.command.CharacterLoc.DesireLoc
import ftg.command.CharacterLoc.Loc
import ftg.command.CharacterLoc.TraitLoc
import ftg.command.ValueEditCommand
import ftg.page.Msg
import ftg.page.Msg.SheetMsg
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.elems.SheetInputs.handleChangeFor
import monocle.Lens
import monocle.macros.GenLens
import monocle.syntax.AppliedLens
import tyrian.CSS
import tyrian.Empty
import tyrian.Html
import tyrian.Html._

object TraitsDesiresInput {
  def renderTraits(ts: TraitSection): Html[Msg] = div(
    h3("Traits"),
    traitSelector(ts, "✔️ ", GenLens[TraitSection](_.twoYouAre._1)),
    traitSelector(ts, "✔️ ", GenLens[TraitSection](_.twoYouAre._2)),
    traitSelector(ts, "❌ ", GenLens[TraitSection](_.oneYouArent))
  )

  def renderDesires(ds: DesireSection): Html[Msg] = div(
    h3("Desires"),
    desiresSelector(ds, "✔️ ", GenLens[DesireSection](_.twoYouWant._1)),
    desiresSelector(ds, "✔️ ", GenLens[DesireSection](_.twoYouWant._2)),
    desiresSelector(ds, "❌ ", GenLens[DesireSection](_.oneYouDont))
  )

  private def traitSelector = {
    given LargelyPrefilledSection[TraitSection, Trait] {
      extension (ts: TraitSection) {
        override def twoPositive: (Option[Trait], Option[Trait]) = ts.twoYouAre
        override def oneNegative: Option[Trait] = ts.oneYouArent
        override def sectionMap: Map[String, Trait] =
          traitSystem.map(t => (t.label.toLowerCase(), t)).toMap
        override def custom(s: String): Trait = CustomTrait(s)
        override def loc: Loc[TraitSection]   = TraitLoc
        override def extractCustomLabel(t: Option[Trait]): Option[String] =
          t match {
            case Some(CustomTrait(l)) => Some(l)
            case _                    => None
          }
      }
    }

    traitsDesiresSelector[TraitSection, Trait]
  }

  private def desiresSelector = {
    given LargelyPrefilledSection[DesireSection, Desire] {
      extension (ds: DesireSection) {
        override def twoPositive: (Option[Desire], Option[Desire]) =
          ds.twoYouWant
        override def oneNegative: Option[Desire] = ds.oneYouDont
        override def sectionMap: Map[String, Desire] =
          desireSystem.map(t => (t.label.toLowerCase(), t)).toMap
        override def custom(s: String): Desire = CustomDesire(s)
        override def loc: Loc[DesireSection]   = DesireLoc
        override def extractCustomLabel(t: Option[Desire]): Option[String] =
          t match {
            case Some(CustomDesire(l)) => Some(l)
            case _                     => None
          }
      }
    }

    traitsDesiresSelector[DesireSection, Desire]
  }

  private def traitsDesiresSelector[TS, T](
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

  private trait LargelyPrefilledSection[TS, T] {
    extension (a: TS) {
      def twoPositive: (Option[T], Option[T])
      def oneNegative: Option[T]
      def sectionMap: Map[String, T]
      def custom(s: String): T
      def loc: Loc[TS]
      def extractCustomLabel(t: Option[T]): Option[String]
    }
  }
}
