package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.VisionsTalent

object VisionsDesc extends TalentDescriptor {

  override def name: String = "Visions"

  override def desc: Markdown =
    "You can roll Presence to ask your patron for forbidden knowledge. Ask the GM a specific question. The answer may be cryptic or twisted to suit your patron’s desires, but it will never be an outright lie. On a messy or grim, roll the Patience pool.".md

  override def apply(c: ftg.Character.Character): Talent = VisionsTalent

}
