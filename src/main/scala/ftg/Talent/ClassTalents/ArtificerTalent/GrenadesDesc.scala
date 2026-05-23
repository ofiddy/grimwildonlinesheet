package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.GrenadesTalent
import ftg.DicePool.DicePool

case object GrenadesDesc extends TalentDescriptor {

  override def name: String = "Grenades"

  override def desc: Markdown =
    "Each session, you have a ***4d Grenades*** resource pool. You have access to the following bombs: *choking*—*flashbang*—*glue*—*smoke*—*stink*. Bombs can affect multiple targets or an area even without potency. You can drop 1 and roll the pool to have a ***potent grenade***.".md

  override def apply(c: ftg.Character.Character): Talent = GrenadesTalent(
    DicePool(4)
  )

}
