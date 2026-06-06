package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.WindcallerTalent

object WindcallerDesc extends TalentDescriptor {

  override def name: String = "Windcaller"

  override def desc: Markdown =
    "Your voice carries on the winds over great distances. You can **push yourself** to summon or dismiss, but not control: *dense fog*—*diving temperatures*—*heavy rain*—*snowfall*—*strong winds*—*thunder*. You don’t need anchors on weather rituals.".md

  override def apply(c: ftg.Character.Character): Talent = WindcallerTalent(
    false
  )

}
