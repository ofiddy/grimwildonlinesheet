package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.LightningReflexesTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object LightningReflexesDesc extends TalentDescriptor {

  override def name: String = "Lightning Reflexes"

  override def desc: Markdown =
    "You can always act first, unless completely surprised. You also ignore difficulty thorns on Agility defense rolls.".md

  override def apply(c: ftg.Character.Character): Talent =
    LightningReflexesTalent

}
