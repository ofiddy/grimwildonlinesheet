package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.InfluenceTalent

case object InfluenceTalentDesc extends TalentDescriptor {

  override def name: String = "Influence"

  override def desc: Markdown =
    "Twice per session, you can ***invoke an ally’s bond with you*** to increase their die roll result by 1. You don’t have to be in the same scene—they might recall a memory.".md

  override def apply(c: ftg.Character.Character): Talent = InfluenceTalent(0)

}
