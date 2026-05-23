package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.FleshWoundsTalent

case object FleshWoundsDesc extends TalentDescriptor {

  override def name: String = "Flesh Wounds"

  override def desc: Markdown =
    "When you take physical damage, only a disaster can drop you. You can get bloodied multiple times. Each extra time inflicts +1t on rolls. These extra thorns can never be ignored and they also apply thorns to any attempt to heal you.".md

  override def apply(c: ftg.Character.Character): Talent = FleshWoundsTalent(0)

}
