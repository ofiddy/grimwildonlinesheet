package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.TrueShapeTalent

object TrueShapeDesc extends TalentDescriptor {

  override def name: String = "True Shape"

  override def desc: Markdown =
    "Choose 1 beast form. If it has no wild talents, you can shift into it without rolling wild shape, even at 0d. If it has wild talents, you drop 1d less than normal when you shift.".md

  override def apply(c: ftg.Character.Character): Talent = TrueShapeTalent(None)

}
