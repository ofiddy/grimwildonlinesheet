package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent

object RogueTalentRenderer {
  def rogueTalentRenderer(
      t: RogueTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: ExpertiseTalent =>
        acc withWidgets List(
          Selectable(
            t.focus(_.expertise),
            List("SKULLDUGGERY", "ASSASSINATION"),
            "EXPERTISE"
          ),
          Pool("CONTINGENCY", t.focus(_.contingency))
        )

      case t: AccordingToPlanTalent =>
        acc withWidget StoryBox(t.focus(_.marked))
      case t: EldritchAffinityTalent =>
        acc withFooter TripleTextFooter(
          t.focus(_.pathsAndTechs),
          "Paths & Techniques"
        )
      case t: LurkerTalent => acc withWidget PushBox(t.focus(_.marked))

  }
}
