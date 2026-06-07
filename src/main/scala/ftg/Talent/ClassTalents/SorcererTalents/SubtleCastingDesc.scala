package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SubtleCastingTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object SubtleCastingDesc extends TalentDescriptor {

  override def name: String = "Subtle Casting"

  override def desc: Markdown =
    "You can always cast spells subtly, able to conceal that you were the one that cast it. This doesn’t defeat common sense, of course.".md

  override def apply(c: ftg.Character.Character): Talent = SubtleCastingTalent

}
