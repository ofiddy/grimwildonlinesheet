package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.RitualistTalent

case object RitualistDesc extends TalentDescriptor {

  override def name: String = "Ritualist"

  override def desc: Markdown =
    "Take +1d on a ritual’s rites and replace any one of its anchors with yourself.".md

  override def apply(c: ftg.Character.Character): Talent = RitualistTalent

}
