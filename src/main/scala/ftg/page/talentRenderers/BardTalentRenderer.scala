package ftg.page.talentRenderers

import tyrian.Html
import ftg.Talent.TalentADT.BardTalent
import ftg.page.Msg
import ftg.Talent.TalentADT.BardsongTalent
import ftg.Talent.TalentADT.FriendlyFaceTalent

object BardTalentRenderer {
  def bardTalentRender(t: BardTalent, acc: Html[Msg]): Html[Msg] = t match
    case BardsongTalent(bardsongs, melodies) => ???
    case FriendlyFaceTalent                  => ???

}
