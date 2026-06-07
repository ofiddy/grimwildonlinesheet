package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.AuthorityTalent

case object AuthorityDesc extends TalentDescriptor {

  override def name: String = "Authority"

  override def desc: Markdown =
    "Your presence fills the air with authority. The GM judges an NPC’s response, or you can spend story to set it: *admiration*—*obedience*—*respect*—*defiance*. You can **push yourself** to pull off a ***potent feat of righteous command***.".md

  override def apply(c: ftg.Character.Character): Talent = AuthorityTalent(
    false
  )

}
