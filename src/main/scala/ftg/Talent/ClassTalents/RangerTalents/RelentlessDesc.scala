package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.RelentlessTalent
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md

case object RelentlessDesc extends TalentDescriptor {

  override def name: String = "Relentless"

  override def desc: Markdown =
    "You can pull off ***potent feats of traversal*** and can always move at full speed, unhindered by what’s in your way.".md

  override def apply(c: ftg.Character.Character): Talent =
    RelentlessTalent

}
