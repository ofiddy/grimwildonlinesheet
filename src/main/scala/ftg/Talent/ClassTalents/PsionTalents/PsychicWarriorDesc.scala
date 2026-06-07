package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.PsychicWarriorTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object PsychicWarriorDesc extends TalentDescriptor {

  override def name: String = "Psychic Warrior"

  override def desc: Markdown =
    "On a perfect with a feat of martial prowess, spend no power points and take +1d on an immediate follow-up with a bastion. You can’t follow-up with a bastion you just used.".md

  override def apply(c: ftg.Character.Character): Talent = PsychicWarriorTalent

}
