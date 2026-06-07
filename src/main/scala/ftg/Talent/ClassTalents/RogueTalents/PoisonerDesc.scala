package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.PoisonerTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool

case object PoisonerDesc extends TalentDescriptor {

  override def name: String = "Posioner"

  override def desc: Markdown =
    "Each session, you have a ***4d Poisons*** resource pool. You have access to the following poisons: *hallucinations*—*knockout*—*memory loss*—*nausea*—*paralytic*—*truth serum*. They each take a bit of time to work and are most effective if ingested.".md

  override def apply(c: ftg.Character.Character): Talent = PoisonerTalent(
    DicePool(4)
  )

}
