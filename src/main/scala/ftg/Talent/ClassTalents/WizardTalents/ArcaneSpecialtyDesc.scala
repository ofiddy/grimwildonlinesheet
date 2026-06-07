package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ArcaneSpecialtyTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object ArcaneSpecialtyDesc extends TalentDescriptor {

  override def name: String = "Arcane Specialty"

  override def desc: Markdown =
    "Choose your specialty school. All of your spell theorems now have it plus another school. When casting, choose which school to use. On a critical with your specialty school, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent =
    ArcaneSpecialtyTalent(None)

}
