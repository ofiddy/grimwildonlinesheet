package ftg.page.talentRenderers

import tyrian.Html
import ftg.Talent.TalentADT._
import ftg.page.Msg
import ftg.page.talentRenderers.FluentTalentRenderers._
import monocle.syntax.all.focus
import ftg.Talent.ClassTalents.BerserkerTalents.FrenzyDesc
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent

object BerserkerTalentRenderer {
  def berserkerTalentRender(
      t: BerserkerTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = t match
    case t: FrenzyTalent => {
      val max = FrenzyDesc(c).frenzy
      acc withWidget MultiCheckbox("FRENZY", t.focus(_.frenzy), max)
    }

    case t: FearsomeTalent => acc withWidget PushBox(t.focus(_.marked))

}
