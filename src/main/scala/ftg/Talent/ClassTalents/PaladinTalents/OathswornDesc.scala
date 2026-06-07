package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.OathswornTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Character.LevelGrowth.`and every 2 levels`

case object OathswornDesc extends TalentDescriptor {

  override def name: String = "Oathsworn"

  override def desc: Markdown = """
You draw power from your three tenets. You don’t take thorns from bloodied or rattled, instead taking +1d on rolls with the related physical or mental stats. Each session, you have **3 smite**. When attacking in combat or argument, spend it 1-for-1 to drop dice from a task pool ***after*** rolling it. [Growth: 2 level/increase smite by 1]
**Affirmation**: Before each session, state your tenets and discuss how they came up last session. ***Give spark to one player*** who joined a scene involving your tenets. **Take 1 less smite** for each tenet in violation.
""".md

  override def apply(c: ftg.Character.Character): OathswornTalent = {
    given Int = c.level
    OathswornTalent(3 `and every 2 levels` (_ + 1), (None, None, None))
  }

}
