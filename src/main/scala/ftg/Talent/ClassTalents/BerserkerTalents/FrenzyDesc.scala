package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.FrenzyTalent
import ftg.Character.LevelGrowth.`and every 3 levels`

case object FrenzyDesc extends TalentDescriptor {

  override def name: String = "Frenzy"

  override def desc: Markdown =
    """Once per session, or when you get bloodied or take vex, you can enter a frenzy for a scene. During the frenzy, you can only take aggressive actions. You also:
     - Take +1d for each mark you have and ignore all thorns from harm and marks.
     - **Collateral damage**: *send something flying*—*smash mooks*—*throw insults*—*wreck something*.
     - Always get a final action when dropped. On a critical, ignore getting dropped.
    You can only exit a frenzy when: *no challenger stands before you*—*get dropped*—*push yourself*. [Growth: 3 levels/1 free activation of frenzy per session]""".md

  override def apply(c: ftg.Character.Character): FrenzyTalent = {
    given level: Int = c.level
    val max          = 1 `and every 3 levels` (_ + 1)
    FrenzyTalent(max)
  }

}
