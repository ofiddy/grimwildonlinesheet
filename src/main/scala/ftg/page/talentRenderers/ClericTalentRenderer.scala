package ftg.page.talentRenderers

import ftg.Talent.TalentADT.ClericTalent
import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.Talent.TalentADT.ChannelDivinityTalent
import ftg.page.talentRenderers.FluentTalentRenderers.ChannelDivinityFooter
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Character.LevelGrowth.`and every 2 levels`
import ftg.Talent.TalentADT._
import ftg.page.talentRenderers.FluentTalentRenderers.SquareBox
import monocle.syntax.all.focus

object ClericTalentRenderer {
  def clericTalentRenderer(
      t: ClericTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given charLevel: Int = c.level
    t match
      case t: ChannelDivinityTalent =>
        val max = 0 `and every 2 levels` (_ + 1)
        acc withFooter ChannelDivinityFooter(t, max)
      case t: BlessedTalent =>
        acc withWidget SquareBox(t.focus(_.marked), "Used")
      case DevoutTalent => acc
      case HealerTalent => acc
  }

}
