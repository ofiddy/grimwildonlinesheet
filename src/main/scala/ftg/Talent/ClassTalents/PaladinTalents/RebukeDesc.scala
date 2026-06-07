package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.RebukeTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object RebukeDesc extends TalentDescriptor {

  override def name: String = "Rebuke"

  override def desc: Markdown =
    "When the GM spends suspense to prompt an impact move, you take +1d on any follow-up action against the source. On a critical, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = RebukeTalent

}
