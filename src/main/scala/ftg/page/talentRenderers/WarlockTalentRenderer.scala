package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent

object WarlockTalentRenderer {
  def warlockTalentRenderer(
      t: WarlockTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: PactTalent =>
        acc withWidgets List(
          TextEntry("GIFTS", t.focus(_.gifts._1)),
          TextEntry("", t.focus(_.gifts._2)),
          Pool("PATIENCE", t.focus(_.patience))
        )
  }
}
