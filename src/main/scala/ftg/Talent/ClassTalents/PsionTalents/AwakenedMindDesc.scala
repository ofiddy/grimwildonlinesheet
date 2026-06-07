package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.AwakenedMindTalent
import ftg.Character.LevelGrowth.`and every 2 levels`

case object AwakenedMindDesc extends TalentDescriptor {

  override def name: String = "Awakened Mind"

  override def desc: Markdown = """
You have spellcasting ability. Choose 2 bastions, which act as touchstones for your psionic magic. You roll two stats combined to cast, forming the roll by adding the bastion's key stats (below) and subtracting 2.
Each session, you have **8 power points**. Spend 1 power point to cast a spell. You can augment it by spending 1 or more points to: *cast a potent spell*—*take +1d (max once) on the roll*—*ignore thorns from damage*—*add another bastion as a touchstone*. [Growth: 2 levels/1 bastion, 2 power points per session]
  """.md

  override def apply(c: ftg.Character.Character): AwakenedMindTalent = {
    given Int = c.level
    AwakenedMindTalent(
      8 `and every 2 levels` (_ + 2),
      (None, None, None, None, None)
    )
  }

}
