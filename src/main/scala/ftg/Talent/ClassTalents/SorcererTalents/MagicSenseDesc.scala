package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MagicSenseTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object MagicSenseDesc extends TalentDescriptor {

  override def name: String = "Magic Sense"

  override def desc: Markdown =
    "You always get a **hint** or **reveal** on vigilance involving magic. When you avoid or disrupt it, take spark.".md

  override def apply(c: ftg.Character.Character): Talent = MagicSenseTalent

}
