package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.HealingHandsTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object HealingHandsDesc extends TalentDescriptor {

  override def name: String = "Healing Hands"

  override def desc: Markdown =
    "You can heal an ally that is bloodied. This automatically works, but you must make a Presence defense roll against being bloodied—you take their pain onto yourself and must fend it off.".md

  override def apply(c: ftg.Character.Character): Talent = HealingHandsTalent

}
