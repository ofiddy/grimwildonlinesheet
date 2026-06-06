package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent

object DruidTalentRenderer {
  def druidTalentRenderer(
      t: DruidTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: WildShapeTalent =>
        acc withWidget Pool("WILD SHAPE", t.focus(_.pool))
      case t: AwakenTalent =>
        acc withWidgets List(
          Pool("AWAKENED", t.focus(_.pool)),
          SquareBox(t.focus(_.ritual), "RITUAL")
        )
      case t: HerbalismTalent =>
        acc withWidgets List(
          TextEntry("MAJOR", t.focus(_.majorHerb)),
          TextEntry("MINOR", t.focus(_.minorHerb)),
          SquareBox(t.focus(_.usedMythic), "MYTHIC")
        )
  }

}
