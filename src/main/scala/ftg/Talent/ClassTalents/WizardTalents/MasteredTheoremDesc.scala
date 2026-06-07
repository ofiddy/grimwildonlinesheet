package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MasteredTheoremTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object MasteredTheoremDesc extends TalentDescriptor {

  override def name: String = "Mastered Theorem"

  override def desc: Markdown =
    "Choose one of your spell theorems. You take +1d when casting it and gain 1 extra ***potent spell*** casting of it per session. Your name becomes linked to it and it begins to spread in popularity throughout wizardry—track it with campaign pools.".md

  override def apply(c: ftg.Character.Character): Talent =
    MasteredTheoremTalent(false)

}
