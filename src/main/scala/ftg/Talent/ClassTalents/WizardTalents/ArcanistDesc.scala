package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ArcanistTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object ArcanistDesc extends TalentDescriptor {

  override def name: String = "Arcanist"

  override def desc: Markdown =
    "Take +1d when rolling an arcana’s pool and ignore the first die that would be dropped. You also gain ***3 minor arcana*** and ***1 major arcana*** that you’ve either created or found.".md

  override def apply(c: ftg.Character.Character): Talent = ArcanistTalent

}
