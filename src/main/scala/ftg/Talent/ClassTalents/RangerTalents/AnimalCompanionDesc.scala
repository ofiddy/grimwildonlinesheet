package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.AnimalCompanionTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object AnimalCompanionDesc extends TalentDescriptor {

  override def name: String = "Animal Companion"

  override def desc: Markdown =
    "You’re accompanied by a fiercely loyal animal. Each PC adds a reciprocal bond with it. Choose ***3 tricks*** and ***2 flaws***. You roll 3d for its tricks and 1d otherwise. It has only two damage boxes, marked and hurt (for harm). It can always exit a scene when hurt.".md

  override def apply(c: ftg.Character.Character): Talent =
    AnimalCompanionTalent(
      (None, None, None),
      (None, None),
      false,
      false
    )

}
