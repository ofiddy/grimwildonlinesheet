package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Talent.TalentADT.AwakenTalent

case object AwakenDesc extends TalentDescriptor {

  override def name: String = "Awaken"

  override def desc: Markdown =
    "Once per scene, you may awaken a living natural feature—plants, animals, insects, fungi—within or nearby the scene. It gains awareness and purpose, forming a ***3d power pool*** you can direct, guided by its nature. One time only, you can make this a ritual-level effect.".md

  override def apply(c: ftg.Character.Character): Talent =
    AwakenTalent(DicePool(3), false)

}
