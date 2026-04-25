package ftg.page.elems

import ftg.Character.CharacterDesire.CustomDesire
import ftg.Character.CharacterDesire.Desire
import ftg.Character.CharacterDesire.desireSystem
import ftg.Character.CharacterTrait.CustomTrait
import ftg.Character.CharacterTrait.Trait
import ftg.Character.CharacterTrait.traitSystem
import ftg.command.CharacterLoc.Loc
import ftg.page.Msg
import tyrian.Html
import tyrian.Html._
import ftg.page.elems.inputPatterns.LargelyPrefilledSection
import ftg.page.elems.inputPatterns.prefilledOrCustomSelector
import ftg.Character.{Character => Character}
import ftg.command.CharacterLoc.TraitLocs._
import ftg.command.CharacterLoc.DesireLocs.DesireYouWant1
import ftg.command.CharacterLoc.DesireLocs.DesireYouWant2
import ftg.command.CharacterLoc.DesireLocs.DesireYouDoNotWant

object TraitsDesiresInput {
  def renderTraits(char: Character): Html[Msg] = div(
    h3("Traits"),
    traitSelector(char, "✔️ ", TraitYouAre1),
    traitSelector(char, "✔️ ", TraitYouAre2),
    traitSelector(char, "❌ ", TraitYouAreNot)
  )

  def renderDesires(char: Character): Html[Msg] = div(
    h3("Desires"),
    desiresSelector(char, "✔️ ", DesireYouWant1),
    desiresSelector(char, "✔️ ", DesireYouWant2),
    desiresSelector(char, "❌ ", DesireYouDoNotWant)
  )

  private def traitSelector = {
    given LargelyPrefilledSection[Trait] {
      override def sectionMap: Map[String, Trait] =
        traitSystem.map(t => (t.label.toLowerCase(), t)).toMap
      override def custom(s: String): Trait = CustomTrait(s)
      override def extractCustomLabel(t: Option[Trait]): Option[String] =
        t match {
          case Some(CustomTrait(l)) => Some(l)
          case _                    => None
        }
    }

    prefilledOrCustomSelector[Trait]
  }

  private def desiresSelector = {
    given LargelyPrefilledSection[Desire] {
      override def sectionMap: Map[String, Desire] =
        desireSystem.map(t => (t.label.toLowerCase(), t)).toMap
      override def custom(s: String): Desire = CustomDesire(s)
      override def extractCustomLabel(t: Option[Desire]): Option[String] =
        t match {
          case Some(CustomDesire(l)) => Some(l)
          case _                     => None
        }
    }

    prefilledOrCustomSelector[Desire]
  }
}
