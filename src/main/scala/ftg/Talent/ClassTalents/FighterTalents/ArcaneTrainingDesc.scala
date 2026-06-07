package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.ArcaneTrainingTalent

case object ArcaneTrainingDesc extends TalentDescriptor {

  override def name: String = "Arcane Training"

  override def desc: Markdown =
    "You have spellcasting as the Spellcraft talent. You roll Wits and can cast 3 spells and 1 potent spell per session. You know 3 spell theorems, created with the Spell Crucible, and can learn new spells from scrolls.".md

  override def apply(c: ftg.Character.Character): Talent =
    ArcaneTrainingTalent(0, false, (None, None, None))

}
