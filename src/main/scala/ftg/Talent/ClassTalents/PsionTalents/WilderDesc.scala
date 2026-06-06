package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.WilderTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object WilderDesc extends TalentDescriptor {

  override def name: String = "Wilder"

  override def desc: Markdown =
    "Your powers are inherently chaotic, either by nature or nurture, and you struggle to control them. Take +1d, but also +1t, on all psionic bastion rolls. You can never ignore thorns from any source, including by using your core talent. On a critical, regain the power points you just spent.".md

  override def apply(c: ftg.Character.Character): Talent = WilderTalent

}
