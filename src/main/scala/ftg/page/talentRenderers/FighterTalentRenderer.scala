package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Character.LevelGrowth.`and every 3 levels`

object FighterTalentRenderer {
  def fighterTalentRenderer(
      t: FighterTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given Int = c.level
    t match
      case t: WeaponMasteryTalent => {
        val masteryDice = 3 `and every 3 levels` (_ + 1)
        acc withWidget TextEntry(s"MASTERY: ${masteryDice}", t.focus(_.style))
      }

      case t: ArcaneTrainingTalent =>
        acc withWidgets List(
          MultiCheckbox("SPELLS", t.focus(_.spells), 3),
          SquareBox(t.focus(_.potent), "POTENT")
        ) withFooter TextEntryFooter(
          "THEOREMS",
          List(
            t.focus(_.theorems._1),
            t.focus(_.theorems._2),
            t.focus(_.theorems._3)
          )
        )

      case t: BulwarkTalent => acc withWidget Pool("BULWARK", t.focus(_.pool))
      case t: ControlTalent => acc withWidget PushBox(t.focus(_.marked))
      case t: GotYourBackTalent   => acc withWidget PushBox(t.focus(_.marked))
      case t: MeasuredTonesTalent => acc withWidget PushBox(t.focus(_.marked))
      case SwiftRecoveryTalent    => acc
      case t: TacticianTalent     => acc withWidget PushBox(t.focus(_.marked))
  }

}
