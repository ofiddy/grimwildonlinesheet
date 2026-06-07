package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.HerbalismTalent

case object HerbalismDesc extends TalentDescriptor {

  override def name: String = "Herbalism"

  override def desc: Markdown =
    "Before each session, use the Herbalism Crucible to make two herb names. You have ***1 minor potion of one and 1 major potion of the other***. The name serves as its touchstone. They lose effect after this session. One time only, you can also have 1 mythic potion.".md

  override def apply(c: ftg.Character.Character): Talent =
    HerbalismTalent(None, None, false)

}
