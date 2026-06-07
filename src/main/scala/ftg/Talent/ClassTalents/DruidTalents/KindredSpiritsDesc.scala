package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.KindredSpiritsTalent

case object KindredSpiritsDesc extends TalentDescriptor {

  override def name: String = "Kindred Spirits"

  override def desc: Markdown =
    "You can speak with animals and spirits of the wild. When you meet, ***they know of you***. Roll their bond with you or spend story to establish it. When you call, those nearby will answer.".md

  override def apply(c: ftg.Character.Character): Talent = KindredSpiritsTalent

}
