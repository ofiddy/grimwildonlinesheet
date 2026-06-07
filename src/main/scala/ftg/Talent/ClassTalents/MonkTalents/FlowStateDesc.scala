package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.FlowStateTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object FlowStateDesc extends TalentDescriptor {

  override def name: String = "Flow State"

  override def desc: Markdown =
    "You can keep taking action to keep the spotlight, even if the GM wants to spend suspense.".md

  override def apply(c: ftg.Character.Character): Talent = FlowStateTalent

}
