package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MindThiefTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object MindThiefDesc extends TalentDescriptor {

  override def name: String = "Mind Thief"

  override def desc: Markdown =
    "On a critical on a bastion roll, refund the power points spent and gain 3 power points. This causes mental ***collateral damage*** around you: *confusion*—*hallucinations*—*headaches*—*panic*. One time only, you can make this a ritual-level effect, affecting all within miles.".md

  override def apply(c: ftg.Character.Character): Talent = MindThiefTalent

}
