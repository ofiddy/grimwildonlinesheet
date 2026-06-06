package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.WeaponMasteryTalent

object WeaponMasteryDesc extends TalentDescriptor {

  override def name: String = "Weapon Mastery"

  override def desc: Markdown =
    "Choose a fighting style that you have mastered: *brawling*—*dual-wielding*—*one-handed weapons*—*ranged weapons*—*thrown weapons*—*two-handed weapons*. You have a ***mastery die***, a special d6. When you fight in your style, take +1d (*the mastery die*) on the roll. If the mastery die is a 6, it counts as a critical. If it’s already a critical, **take spark**. [Growth: 3 levels/+1d mastery die]".md

  override def apply(c: ftg.Character.Character): Talent = WeaponMasteryTalent(
    None
  )

}
