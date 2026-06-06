package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Talent.TalentADT.BulwarkTalent

object BulwarkDesc extends TalentDescriptor {

  override def name: String = "Bulwark"

  override def desc: Markdown =
    "Each session, you have a ***3d Bulwark pool*** from armor or other defenses. When you get bloodied or dropped from physical damage, roll the pool. If dice remain, ignore the damage.".md

  override def apply(c: ftg.Character.Character): Talent = BulwarkTalent(
    DicePool(3)
  )

}
