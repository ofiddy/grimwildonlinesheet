package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.TumultuousMindTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object TumultuousMindDesc extends TalentDescriptor {

  override def name: String = "Tumultuous Mind"

  override def desc: Markdown =
    "You can spend 2 power points to gain potency on any Wits or Presence defense roll. Regardless of the roll, if the attacker is sentient, you can ***interrupt*** their next impact move.".md

  override def apply(c: ftg.Character.Character): Talent = TumultuousMindTalent

}
