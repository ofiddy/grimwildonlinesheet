package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.WeaselTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object WeaselDesc extends TalentDescriptor {

  override def name: String = "Weasel"

  override def desc: Markdown =
    "If caught in a lie or red-handed, you can **push yourself** to weasel your way out—for now. You get an exit, but they definitely realize what happened soon after.".md

  override def apply(c: ftg.Character.Character): Talent = WeaselTalent(false)

}
