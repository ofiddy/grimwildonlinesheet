package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.DynamicEntranceTalent

case object DynamicEntranceDesc extends TalentDescriptor {

  override def name: String = "Dynamic Entrance"

  override def desc: Markdown =
    "You can always appear in a scene exactly where and when you want, limited only by your physical capabilities. Make a 3d story roll to see if you’re engaged as you enter.".md

  override def apply(c: ftg.Character.Character): Talent = DynamicEntranceTalent

}
