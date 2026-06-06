package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.AccordingToPlanTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object AccordingToPlanDesc extends TalentDescriptor {

  override def name: String = "According to Plan"

  override def desc: Markdown =
    "You gain 1 story per session and can spend story to flashback to utilizing subterfuge in a way that’s immediately relevant, like swiping some keys, paying off a guard, or preparing an escape route. Make a montage roll, taking +1d for Expertise. The GM always takes suspense in place of an impact move.".md

  override def apply(c: ftg.Character.Character): Talent =
    AccordingToPlanTalent(true)

}
