package ftg.page.talentRenderers

import ftg.Talent.TalentADT.ClericTalent
import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.Talent.TalentADT.ChannelDivinityTalent
import ftg.page.talentRenderers.FluentTalentRenderers.ChannelDivinityFooter
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent

object ClericTalentRenderer {
  def clericTalentRenderer(
      t: ClericTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = t match
    case t: ChannelDivinityTalent => acc withFooter ChannelDivinityFooter(t)

}
