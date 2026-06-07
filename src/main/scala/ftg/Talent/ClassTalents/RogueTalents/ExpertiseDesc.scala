package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ExpertiseTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Character.LevelGrowth.`and every 2 levels`

case object ExpertiseDesc extends TalentDescriptor {

  override def name: String = "Expertise"

  override def desc: Markdown = """
Choose a skillset below as your expertise:
◆ ***Skullduggery***: Take +1d at stealth, picking locks, lying, and sleight of hand.
◆ ***Assassination***: Take +1d at stealth, tracking people, opening strikes, and disguises.
Each session, you have a ***3d Contingency*** pool, always planning ahead. You can roll it as bonus dice after any roll within your expertise. [Growth: 2 levels/+1d Contingency]
""".md

  override def apply(c: ftg.Character.Character): Talent =
    given Int = c.level
    ExpertiseTalent(None, DicePool(3 `and every 2 levels` (_ + 1)))

}
