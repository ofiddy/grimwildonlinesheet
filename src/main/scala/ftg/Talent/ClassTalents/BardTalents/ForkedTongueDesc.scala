package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.ForkedTongueTalent

case object ForkedTongueDesc extends TalentDescriptor {

  override def name: String = "Forked Tongue"

  override def desc: Markdown =
    "When given time, you can tell ***potent lies***. You can **push yourself** to do it on the spot.".md

  override def apply(c: ftg.Character.Character): Talent = ForkedTongueTalent(
    false
  )

}
