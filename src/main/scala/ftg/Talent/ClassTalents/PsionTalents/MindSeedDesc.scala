package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MindSeedTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object MindSeedDesc extends TalentDescriptor {

  override def name: String = "Mind Seed"

  override def desc: Markdown =
    "When you plant thoughts or alter memories in someone’s head, you can always choose to have them take effect exactly when you want them to. You can decide this at any time later. Once per session, you can use this for an ***interrupt***.".md

  override def apply(c: ftg.Character.Character): Talent = MindSeedTalent(false)

}
