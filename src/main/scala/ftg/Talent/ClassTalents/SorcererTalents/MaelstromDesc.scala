package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MaelstromTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object MaelstromDesc extends TalentDescriptor {

  override def name: String = "Maelstrom"

  override def desc: Markdown =
    "You open yourself fully to the maelstrom of raw magic. When you cast a ***potent spell***, 5s count as 6s, but 4s count as 1s. These 4s also count as 1s towards wild surge.".md

  override def apply(c: ftg.Character.Character): Talent = MaelstromTalent

}
