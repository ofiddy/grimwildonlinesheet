package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.DoubleBarreledBlunderbussTalent

case object DoubleBarreledBlunderbussDesc extends TalentDescriptor {

  override def name: String = "Double-Barreled Blunderbuss"

  override def desc: Markdown =
    "You can fire each type of specialized ammo once per session. When you shoot, choose the ammo: *blast core*—*drill shot*—*inferno shot*— *scatter shot*—*shrapnel burst*—*tangler shot*. Each shot has a secondary or collateral effect related to the type. You can fire two ammo types at the same time for a potent shot.".md

  override def apply(c: ftg.Character.Character): Talent =
    DoubleBarreledBlunderbussTalent(false, false, false, false, false, false)

}
