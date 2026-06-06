package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.PactTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Character.LevelGrowth.`and every 3 levels`

object PactDesc extends TalentDescriptor {

  override def name: String = "Pact"

  override def desc: Markdown =
    "Your patron gives you gifts in exchange for obligations. Before each session, **roll 2d** and write these results down as **Gifts**. During the session, you can replace any rolled d6 (*by anyone*) with a gift result, then erase it. You can also cast cantrips with your patron’s trappings as touchstones, useful as *set dressing* and *magic utility*. Gain a bonus talent and each talent is infused with your patron’s trappings. Your obligation is an ***8d Patience pool***. [Growth: 3 levels/gain a bonus talent, -2d max ***Patience***.]".md

  override def apply(c: ftg.Character.Character): Talent =
    given Int = c.level
    PactTalent((None, None), DicePool(8 `and every 3 levels` (_ - 2)))

}
