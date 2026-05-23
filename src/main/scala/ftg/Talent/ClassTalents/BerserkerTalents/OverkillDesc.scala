package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.OverkillTalent

object OverkillDesc extends TalentDescriptor {

  override def name: String = "Overkill"

  override def desc: Markdown =
    "On a critical when bringing violence, threats, or destruction to bear, you can: *cause an extra secondary effect*—*roll a task pool twice*.".md

  override def apply(c: ftg.Character.Character): Talent = OverkillTalent

}
