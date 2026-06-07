package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.TrophiesTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object TrophiesDesc extends TalentDescriptor {

  override def name: String = "Trophies"

  override def desc: Markdown =
    "When you play a key part in slaying a powerful monster, you can take a trophy from it. This acts as a wand. A minor wand requires no roll. A major wand requires a Wits roll. The monster’s name, ability, and body part are the touchstone. Only you can use it.".md

  override def apply(c: ftg.Character.Character): Talent = TrophiesTalent(None)

}
