package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ControlTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object ControlDesc extends TalentDescriptor {

  override def name: String = "Control"

  override def desc: Markdown =
    "You can **push yourself** to declare a zone that enemies cannot cross without dealing with you. If they attempt to move past you, you can ***interrupt*** them. On a perfect, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = ControlTalent(false)

}
