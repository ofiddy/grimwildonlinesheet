package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.HuntersMarkTalent
import ftg.Character.LevelGrowth.`and every 2 levels`

case object HuntersMarkDesc extends TalentDescriptor {

  override def name: String = "Hunter’s Mark"

  override def desc: Markdown = """
Twice per session, you can declare a weakness in a non-humanoid creature. Describe and tag it with a ***2d Weakness*** pool. When anyone targets the weakness, roll the pool as bonus dice on their roll. **Take spark** when a Weakness die rolls a perfect (not the overall result). [Growth: 2 levels/+1 use per session]
***Prowess***: Take +1d at stealth, traversal, setting traps, and tracking.
""".md

  override def apply(c: ftg.Character.Character): HuntersMarkTalent = {
    given Int = c.level
    HuntersMarkTalent(2 `and every 2 levels` (_ + 1))
  }

}
