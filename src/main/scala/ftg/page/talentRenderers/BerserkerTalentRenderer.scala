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
    case t: FleshWoundsTalent =>
      acc withWidget MultiCheckbox("WOUNDS", t.focus(_.wounds), t.wounds + 1)
    case IntoTheFrayTalent   => acc
    case JoyfulWarriorTalent => acc
    case t: MightyTalent     => acc withWidget PushBox(t.focus(_.marked))
    case OverkillTalent      => acc
    case t: WarsongsTalent =>
      acc withWidgets List(
        MultiCheckbox("WARSONGS", t.focus(_.warsongs), 2)
      ) withFooter WarsongsFooter(t.focus(_.compositions))

}
