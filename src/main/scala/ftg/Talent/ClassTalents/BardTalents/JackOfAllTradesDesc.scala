package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.JackOfAllTradesTalent

case object JackOfAllTradesDesc extends TalentDescriptor {

  override def name: String = "Jack of all Trades"

  override def desc: Markdown =
    "Increase one of your stats that’s a 1 to a 2. You also take +1d on montage rolls.".md

  override def apply(c: ftg.Character.Character): Talent = JackOfAllTradesTalent

}
