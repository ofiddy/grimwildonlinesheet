package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Character.LevelGrowth.`and every 2 levels`

object SorcererTalentRenderer {
  def sorcererTalentRenderer(
      t: SorcererTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given Int = c.level
    t match
      case t: SorceryTalent =>
        acc withWidgets List(
          TextEntry("PATHS & TECHNIQUES", t.focus(_.pathsAndTechs._1)),
          TextEntry("", t.focus(_.pathsAndTechs._2)),
          TextEntry("", t.focus(_.pathsAndTechs._3)),
          TextEntry("", t.focus(_.pathsAndTechs._4)),
          TextEntry("", t.focus(_.pathsAndTechs._5)),
          TextEntry("", t.focus(_.pathsAndTechs._6)),
          TextEntry("", t.focus(_.pathsAndTechs._7))
        ).take(4 `and every 2 levels` (_ + 1))
  }
}
