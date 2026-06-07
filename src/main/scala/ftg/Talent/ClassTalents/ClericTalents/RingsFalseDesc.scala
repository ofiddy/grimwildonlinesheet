package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.RingsFalseTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object RingsFalseDesc extends TalentDescriptor {

  override def name: String = "Rings False"

  override def desc: Markdown =
    "You always know when someone is lying, though not necessarily the truth. You take +1d on a follow-up if you reveal you know they’re lying.".md

  override def apply(c: ftg.Character.Character): Talent = RingsFalseTalent

}
