package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.JoyfulWarriorTalent

case object JoyfulWarriorDesc extends TalentDescriptor {

  override def name: String = "Joyful Warrior"

  override def desc: Markdown =
    "On a critical or when bloodied in battle, you **take spark** and can: *clear one mark from each ally*—*bring a dropped (not dead) ally back into the scene*.".md

  override def apply(c: ftg.Character.Character): Talent = JoyfulWarriorTalent

}
