package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SpelleaterTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool

object SpelleaterDesc extends TalentDescriptor {

  override def name: String = "Spelleater"

  override def desc: Markdown =
    "You have a ***3d Spelleater pool*** *(per session)*. When you suffer from magic, roll the pool. If dice remain, ignore the effect and bank essence (max 2). Spend it to increase a spellcasting die result by 1, but it triggers wild surge.".md

  override def apply(c: ftg.Character.Character): Talent =
    SpelleaterTalent(DicePool(3), 0)

}
