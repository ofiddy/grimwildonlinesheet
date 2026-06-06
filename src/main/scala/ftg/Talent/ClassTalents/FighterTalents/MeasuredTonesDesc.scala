package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MeasuredTonesTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object MeasuredTonesDesc extends TalentDescriptor {

  override def name: String = "Measured Tones"

  override def desc: Markdown =
    "When you speak in measured tones, people always stop and listen—declare ***low risk*** if desired. Unless completely surprised, you can **push yourself** to ***interrupt*** an impact move initiating aggression. If you change their mind, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = MeasuredTonesTalent(
    false
  )

}
