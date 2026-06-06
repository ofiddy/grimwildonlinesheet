package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.OpportunistTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object OpportunistDesc extends TalentDescriptor {

  override def name: String = "Opportunist"

  override def desc: Markdown =
    "When someone nearby rolls defense, you take +1d on an immediate follow-up. On a critical by a nearby ally, you can **push yourself** to add a critical bonus.".md

  override def apply(c: ftg.Character.Character): Talent = OpportunistTalent(
    false
  )

}
