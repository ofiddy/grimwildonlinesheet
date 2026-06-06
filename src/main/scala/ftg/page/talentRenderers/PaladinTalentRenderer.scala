package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.PaladinTalents._

object PaladinTalentRenderer {
  def paladinTalentRenderer(
      t: PaladinTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: OathswornTalent => {
        val max = OathswornDesc(c).smite
        acc withWidget MultiCheckbox(
          "SMITE",
          t.focus(_.smite),
          max
        ) withFooter TripleTextFooter(t.focus(_.tenets), "TENETS")
      }

      case t: AegisTalent     => acc withWidget PushBox(t.focus(_.marked))
      case t: AuthorityTalent => acc withWidget PushBox(t.focus(_.marked))
  }

}
