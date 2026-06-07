package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.PreparedSpellTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object PreparedSpellDesc extends TalentDescriptor {

  override def name: String = "Prepared Spell"

  override def desc: Markdown =
    "You gain 1 story per session and can spend story to flashback to casting a spell with specific triggers. If a montage to get access to a place is needed, the GM always takes suspense in place of an impact move. Roll for the spell’s effectiveness now.".md

  override def apply(c: ftg.Character.Character): Talent = PreparedSpellTalent(
    true
  )

}
