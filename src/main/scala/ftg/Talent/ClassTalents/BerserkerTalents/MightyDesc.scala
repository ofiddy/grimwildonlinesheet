package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.MightyTalent

case object MightyDesc extends TalentDescriptor {

  override def name: String = "Mighty"

  override def desc: Markdown =
    "When given time, you can pull off ***potent feats of raw strength***. You can **push yourself** to do it on the spot.".md

  override def apply(c: ftg.Character.Character): Talent = MightyTalent(false)

}
