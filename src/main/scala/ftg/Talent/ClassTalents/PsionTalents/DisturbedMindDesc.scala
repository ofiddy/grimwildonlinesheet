package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.DisturbedMindTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object DisturbedMindDesc extends TalentDescriptor {

  override def name: String = "Disturbed Mind"

  override def desc: Markdown =
    " Your presence is highly disturbing. The GM judges an NPC’s response, or you can spend story to set it: *agitated*—*fractured*—*paranoid*—*repulsed*. Once per session when you would take vex, a nearby sentient creature must instead take a vex response.".md

  override def apply(c: ftg.Character.Character): Talent = DisturbedMindTalent(
    false
  )

}
