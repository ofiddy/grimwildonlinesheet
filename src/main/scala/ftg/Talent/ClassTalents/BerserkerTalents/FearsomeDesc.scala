package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.FearsomeTalent

case object FearsomeDesc extends TalentDescriptor {

  override def name: String = "Fearsome"

  override def desc: Markdown =
    "Your presence instills fear in others. The GM judges an NPC’s response, or you can spend story to set it: *hostile*—*nervous*—*respectful*—*scared*. You can **push yourself** to pull off a ***potent feat of intimidation***.".md

  override def apply(c: ftg.Character.Character): Talent = FearsomeTalent(false)

}
