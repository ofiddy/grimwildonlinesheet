package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.HealerTalent

case object HealerDesc extends TalentDescriptor {

  override def name: String = "Healer"

  override def desc: Markdown =
    "You take +1d when you heal someone, with magic or treatment. On a critical, both of you **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = HealerTalent

}
