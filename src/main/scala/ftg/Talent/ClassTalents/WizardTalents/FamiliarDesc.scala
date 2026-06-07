package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.FamiliarTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object FamiliarDesc extends TalentDescriptor {

  override def name: String = "Familiar"

  override def desc: Markdown =
    "You manifest a small magical creature. You can communicate telepathically and send it on simple tasks, making a story roll to see how it goes. By entering a trance, you can use its senses. You can also **push yourself** to cast a spell through it. If your familiar takes damage, it vanishes and reappears at the start of the next session.".md

  override def apply(c: ftg.Character.Character): Talent = FamiliarTalent(false)

}
