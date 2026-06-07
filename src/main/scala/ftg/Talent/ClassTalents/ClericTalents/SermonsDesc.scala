package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SermonsTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object SermonsDesc extends TalentDescriptor {

  override def name: String = "Sermons"

  override def desc: Markdown =
    "When given time, you can pull off ***potent feats of persuasion in the name of your beliefs***. You can also **push yourself** to do it on the spot. One time only, you can make this a ritual-level effect.".md

  override def apply(c: ftg.Character.Character): Talent =
    SermonsTalent(false, false)

}
