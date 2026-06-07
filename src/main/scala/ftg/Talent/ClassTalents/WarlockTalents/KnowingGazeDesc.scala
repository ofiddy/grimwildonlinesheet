package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.KnowingGazeTalent

case object KnowingGazeDesc extends TalentDescriptor {

  override def name: String = "Knowing Gaze"

  override def desc: Markdown =
    "When given time, you can learn: *their greatest desire*—*what they’re hiding*—*what they want to protect*—*who they serve*. This gaze is highly disconcerting and only works on someone once. You can **push yourself** to do this on the spot.".md

  override def apply(c: ftg.Character.Character): Talent = KnowingGazeTalent(
    false
  )

}
