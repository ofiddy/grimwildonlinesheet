package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Talent.TalentADT.IronWillTalent

object IronWillDesc extends TalentDescriptor {

  override def name: String = "Iron Will"

  override def desc: Markdown =
    "Each session, you have a ***3d Iron Will*** pool. When you get rattled or take vex, roll the pool. If dice remain, ignore the damage. If possible, you can also lash out at the cause or a bystander and: *instill dread in their heart*—*sow doubt in their mind*.".md

  override def apply(c: ftg.Character.Character): Talent = IronWillTalent(
    DicePool(3)
  )

}
