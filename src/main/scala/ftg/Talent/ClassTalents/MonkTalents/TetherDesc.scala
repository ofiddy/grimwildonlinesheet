package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.TetherTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object TetherDesc extends TalentDescriptor {

  override def name: String = "Tether"

  override def desc: Markdown =
    "Once per session, touch someone to link spirits. You can sense their feelings and know where they are. You can **push yourself** to: *assist them*—*speak into their mind*—*take mental damage for them*—*teleport to them*. If they get dropped, so do you. You must touch again or rest for the link to end. When it does, change your bond.".md

  override def apply(c: ftg.Character.Character): Talent =
    TetherTalent(false, false)

}
