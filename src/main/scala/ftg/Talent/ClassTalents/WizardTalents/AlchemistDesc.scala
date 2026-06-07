package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.AlchemistTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool

object AlchemistDesc extends TalentDescriptor {

  override def name: String = "Alchemist"

  override def desc: Markdown =
    "Each session, you have a ***4d Potions*** resource pool. You can have a minor potion and roll the pool, or drop 1 and roll for a major potion. You know recipes for your spell theorems, plus two more. Learn new recipes by sacrificing potions.".md

  override def apply(c: ftg.Character.Character): Talent =
    AlchemistTalent(DicePool(4), (None, None))

}
