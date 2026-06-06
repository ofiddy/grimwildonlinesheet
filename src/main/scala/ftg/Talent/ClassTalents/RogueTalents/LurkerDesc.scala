package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.LurkerTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object LurkerDesc extends TalentDescriptor {

  override def name: String = "Lurker"

  override def desc: Markdown =
    "On a perfect sneaking past or secretly observing sentient creatures, they: *let a secret slip*—*give you a golden opportunity*. If caught sneaking, you can **push yourself** to stay completely still and avoid detection, but must leave the area immediately after.".md

  override def apply(c: ftg.Character.Character): Talent = LurkerTalent(false)

}
