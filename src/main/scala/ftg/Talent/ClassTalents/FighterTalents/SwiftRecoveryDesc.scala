package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SwiftRecoveryTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object SwiftRecoveryDesc extends TalentDescriptor {

  override def name: String = "Swift Recovery"

  override def desc: Markdown =
    "On a perfect roll with an unmarked stat, clear a mark on another stat.".md

  override def apply(c: ftg.Character.Character): Talent = SwiftRecoveryTalent

}
