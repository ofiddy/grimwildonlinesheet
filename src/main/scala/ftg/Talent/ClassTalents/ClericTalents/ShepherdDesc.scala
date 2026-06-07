package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ShepherdTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object ShepherdDesc extends TalentDescriptor {

  override def name: String = "Shepherd"

  override def desc: Markdown =
    "When you assist an ally on a defense roll that would leave them rattled or with vex, you can ***invoke your bond*** to take +1d on the roll. On a perfect, you both **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = ShepherdTalent

}
