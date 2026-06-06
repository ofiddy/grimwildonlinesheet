package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.RangerTalents.HuntersMarkDesc

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
  }
}
