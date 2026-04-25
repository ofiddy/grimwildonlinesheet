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
import ftg.page.Msg
import monocle.Lens
import monocle.macros.GenLens
import tyrian.Html
import tyrian.Html._
import ftg.page.elems.inputPatterns.LargelyPrefilledSection
import ftg.page.elems.inputPatterns.prefilledOrCustomSelector

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

    prefilledOrCustomSelector[TraitSection, Trait]
  }

  private def desiresSelector = {
    given LargelyPrefilledSection[DesireSection, Desire] {
      extension (ds: DesireSection) {
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

    prefilledOrCustomSelector[DesireSection, Desire]
  }
}
