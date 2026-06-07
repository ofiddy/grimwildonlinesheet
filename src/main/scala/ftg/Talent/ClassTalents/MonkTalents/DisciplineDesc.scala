package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.DisciplineTalent
import ftg.Character.LevelGrowth.`and every 3 levels`

case object DisciplineDesc extends TalentDescriptor {

  override def name: String = "Discipline"

  override def desc: Markdown = """
Never face a disadvantage due to a weapon matchup. Once per session, you can ***interrupt*** with a: *philosophical point*—*quick reaction*—*stunning strike*. Each scene, you have **4 flow**. Spend it to:
◆ Ignore difficulty thorns from: *being outnumbered*—*a single powerful opponent*.
◆ Attempt a ***fluid maneuver***. Before rolling, declare you want to also: *disarm them*—*redirect momentum*—*reposition you, them, or both*. On a perfect or messy, it happens.
◆ Pull off a ***potent feat of mystical grace***, like running across water or falling harmlessly from a great height. *For 1 more flow*, extend this to those you’re touching.
[Growth: 3 levels/+1 flow per scene and +1 interrupts per session]
""".md

  override def apply(c: ftg.Character.Character): DisciplineTalent =
    given Int = c.level
    DisciplineTalent(
      4 `and every 3 levels` (_ + 1),
      1 `and every 3 levels` (_ + 1)
    )

}
