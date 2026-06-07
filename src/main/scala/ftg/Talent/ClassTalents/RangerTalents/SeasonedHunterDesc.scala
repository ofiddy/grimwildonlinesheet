package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.md
import ftg.Talent.Markdown
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SeasonedHunterTalent

case object SeasonedHunterDesc extends TalentDescriptor {

  override def name: String = "Seasoned Hunter"

  override def desc: Markdown =
    "You always know where a monster’s lair will be. Once per session, you can tell all to ***Look Out!*** and declare how a monster spends 1 suspense on a move to attack your party. Everyone takes +1d on the defense roll and follow-up action rolls.".md

  override def apply(c: ftg.Character.Character): Talent = SeasonedHunterTalent(
    false
  )
}
