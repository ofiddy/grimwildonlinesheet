package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.AnimalCompanionIITalent

object AnimalCompanionIIDesc extends TalentDescriptor {

  override def name: String = "Animal Companion (II)"

  override def desc: Markdown =
    "*Special: you can take this talent again to grant it 3 more tricks and another marked box.*".md

  override def apply(c: ftg.Character.Character): Talent =
    AnimalCompanionIITalent((None, None, None), false)

}
