package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.WrathTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object WrathDesc extends TalentDescriptor {

  override def name: String = "Wrath"

  override def desc: Markdown =
    "When you or a nearby ally gets bloodied, rattled, or dropped, you take +1d and ***potency*** on your next spell if you immediately lash out.".md

  override def apply(c: ftg.Character.Character): Talent = WrathTalent

}
