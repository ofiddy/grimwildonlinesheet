package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.TrapSenseTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object TrapSenseDesc extends TalentDescriptor {

  override def name: String = "Trap Sense"

  override def desc: Markdown =
    "You always get a **hint** or **reveal** on vigilance with traps. If avoided or disabled, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = TrapSenseTalent

}
