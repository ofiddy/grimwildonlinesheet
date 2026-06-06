package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ScoutAheadTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object ScoutAheadDesc extends TalentDescriptor {

  override def name: String = "Scout Ahead"

  override def desc: Markdown =
    "You gain 1 story per session and can spend story to flashback to scouting ahead and: *sabotage something*—*set a trap*—*survey the area (ask 2 questions)*—*take out a danger*—*set up an* ***interrupt***. Make a montage roll, taking +1d for prowess. The GM always takes suspense in place of an impact move.".md

  override def apply(c: ftg.Character.Character): Talent = ScoutAheadTalent(
    true
  )

}
