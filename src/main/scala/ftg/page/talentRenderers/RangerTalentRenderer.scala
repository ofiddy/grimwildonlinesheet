package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.RangerTalents.HuntersMarkDesc
import monocle.AppliedLens

object RangerTalentRenderer {
  def rangerTalentRenderer(
      t: RangerTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: HuntersMarkTalent => {
        val max = HuntersMarkDesc(c).weakness
        acc withWidget MultiCheckbox("WEAKNESS", t.focus(_.weakness), max)
      }
      case t: AnimalCompanionTalent => {
        acc withWidgets List(
          companionTrickSelector(t.focus(_.tricks._1)),
          companionTrickSelector(t.focus(_.tricks._2)),
          companionTrickSelector(t.focus(_.tricks._3)),
          companionFlawSelector(t.focus(_.flaws._1)),
          companionFlawSelector(t.focus(_.flaws._2)),
          SquareBox(t.focus(_.marked), "MARKED"),
          SquareBox(t.focus(_.hurt), "HURT")
        )
      }
      case t: AnimalCompanionIITalent =>
        acc withWidgets List(
          companionTrickSelector(t.focus(_.tricks._1)),
          companionTrickSelector(t.focus(_.tricks._2)),
          companionTrickSelector(t.focus(_.tricks._3)),
          SquareBox(t.focus(_.marked), "MARKED")
        )
      case KeenSensesTalent    => acc
      case RelentlessTalent    => acc
      case t: ScoutAheadTalent => acc withWidget StoryBox(t.focus(_.marked))
      case t: SeasonedHunterTalent =>
        acc withWidget SquareBox(t.focus(_.marked), "LOOK OUT!")
      case t: SharpshooterTalent => acc withWidget PushBox(t.focus(_.marked))
      case t: TrophiesTalent =>
        acc withWidget TextEntry("TROPHY", t.focus(_.trophy))

  }

  def companionTrickSelector[T <: Talent](
      ref: AppliedLens[T, Option[String]]
  ): Selectable[T] = Selectable(
    ref,
    List(
      "DISTRACT",
      "FIGHT",
      "GUARD",
      "PERFORM",
      "RESCUE",
      "RETRIEVE",
      "SCOUT",
      "SEARCH",
      "TRACK",
      "WARN"
    ),
    "TRICK"
  )

  def companionFlawSelector(
      ref: AppliedLens[AnimalCompanionTalent, Option[String]]
  ): Selectable[AnimalCompanionTalent] = Selectable(
    ref,
    List(
      "AGGRESIVE",
      "CLUMSY",
      "GRUMPY",
      "INSATIABLE",
      "JUMPY",
      "NOISY",
      "OVERPROTECTIVE",
      "SCARY",
      "UNRULY"
    ),
    "FLAW"
  )
}
