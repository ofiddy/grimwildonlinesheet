package ftg.page.talentRenderers

import tyrian.Html
import ftg.Talent.TalentADT.BardTalent
import ftg.page.Msg
import ftg.Talent.TalentADT.BardsongTalent
import ftg.Character.{Character => Character}
import ftg.Talent.ClassTalents.BardTalents._
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.page.talentRenderers.FluentTalentRenderers._
import monocle.syntax.all.focus
import ftg.Talent.TalentADT.FriendlyFaceTalent
import ftg.Talent.TalentADT.BardicLoreTalent

object BardTalentRenderer {
  def bardTalentRender(t: BardTalent, c: Character, acc: Html[Msg])(using
      TalentEditBuilder
  ): Html[Msg] =
    t match
      case t: BardsongTalent =>
        val max = BardsongTalentDesc(c)
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
      case FriendlyFaceTalent => acc
      case t: BardicLoreTalent =>
        acc withWidget StoryBox(t.focus(_.story))

}
