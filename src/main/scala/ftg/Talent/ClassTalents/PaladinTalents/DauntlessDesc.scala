package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.DauntlessTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object DauntlessDesc extends TalentDescriptor {

  override def name: String = "Dauntless"

  override def desc: Markdown =
    "**Take spark** when your roll is cut. Take +1d on story rolls if dropped. On a perfect, ignore it.".md

  override def apply(c: ftg.Character.Character): Talent = DauntlessTalent

}
