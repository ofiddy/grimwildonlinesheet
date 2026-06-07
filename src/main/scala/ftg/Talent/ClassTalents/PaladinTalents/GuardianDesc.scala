package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.GuardianTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object GuardianDesc extends TalentDescriptor {

  override def name: String = "Guardian"

  override def desc: Markdown =
    "Take +1d when you assist on a defense roll. If your dice come up as a 6, you both take spark.".md

  override def apply(c: ftg.Character.Character): Talent = GuardianTalent

}
