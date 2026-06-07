package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.WayfarerTalent

case object WayfarerDesc extends TalentDescriptor {

  override def name: String = "Wayfarer"

  override def desc: Markdown =
    "You can **push yourself** to teleport. The further or less familiar, the less precise. Max range is two days’ ride away. Clear eyesight and no obstruction requires no roll, otherwise make a story roll. You can bring others, taking +1t per person.".md

  override def apply(c: ftg.Character.Character): Talent = WayfarerTalent(false)

}
