package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.EldritchGrowthTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object EldritchGrowthDesc extends TalentDescriptor {

  override def name: String = "Eldritch Growth"

  override def desc: Markdown =
    "The maelstrom of magic has twisted your body. You gain a permanent physical feature. In addition to its obvious benefits, it now serves as a touchstone.".md

  override def apply(c: ftg.Character.Character): Talent = EldritchGrowthTalent

}
