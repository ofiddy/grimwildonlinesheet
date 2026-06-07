package ftg.Talent.ClassTalents.RangerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SharpshooterTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object SharpshooterDesc extends TalentDescriptor {

  override def name: String = "Sharpshooter"

  override def desc: Markdown =
    "When given time, you can pull off ***potent feats of ranged precision***. You can also **push yourself** to do it on the spot.".md

  override def apply(c: ftg.Character.Character): Talent = SharpshooterTalent(
    false
  )

}
