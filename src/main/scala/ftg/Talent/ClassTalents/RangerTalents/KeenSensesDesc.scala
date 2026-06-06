package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.KeenSensesTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object KeenSensesDesc extends TalentDescriptor {

  override def name: String = "Keen Senses"

  override def desc: Markdown =
    "Your senses are twice as sharp as normal. You can pull off ***potent feats of tracking*** and can always get a **hint** or **reveal** on vigilance against living creatures.".md

  override def apply(c: ftg.Character.Character): Talent = KeenSensesTalent

}
