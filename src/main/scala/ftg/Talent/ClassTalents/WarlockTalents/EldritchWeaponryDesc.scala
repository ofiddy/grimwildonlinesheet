package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.EldritchWeaponryTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object EldritchWeaponryDesc extends TalentDescriptor {

  override def name: String = "Eldritch Weaponry"

  override def desc: Markdown =
    "On a perfect when using cantrip utility as a weapon, you can **push yourself** to also inflict a spell-level effect with your patron’s trappings as the touchstone. It can’t deal more damage.".md

  override def apply(c: ftg.Character.Character): Talent =
    EldritchWeaponryTalent(false)

}
