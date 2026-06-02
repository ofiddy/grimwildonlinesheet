package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.BlessedTalent

case object BlessedDesc extends TalentDescriptor {

  override def name: String = "Blessed"

  override def desc: Markdown =
    "Once per session, you can re-roll a roll you just made as your god attempts to intervene. The re-roll is made without any thorns on it. On a perfect, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = BlessedTalent(false)

}
