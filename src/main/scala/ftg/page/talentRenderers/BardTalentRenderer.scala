package ftg.page.talentRenderers

import tyrian.Html
import ftg.Talent.TalentADT.BardTalent
import ftg.page.Msg
import ftg.Talent.TalentADT.BardsongTalent
import ftg.Character.{Character => Character}
import ftg.Talent.ClassTalents.BardTalents.Bardsong.BardsongTalentDescriptor
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.page.talentRenderers.FluentTalentRenderers.MultiCheckbox
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder

object BardTalentRenderer {
  def bardTalentRender(t: BardTalent, c: Character, acc: Html[Msg])(using
      TalentEditBuilder
  ): Html[Msg] =
    t match
      case t @ BardsongTalent(bardsongs, melodies) =>
        val max = BardsongTalentDescriptor(c)
        (acc
          withWidget MultiCheckbox(
            "BARDSONGS",
            t.focus(_.bardsongs),
            max.bardsongs
          )) withWidget MultiCheckbox(
          "MEDLODIES",
          t.focus(_.melodies),
          max.melodies
        )
      case _ => acc

}
