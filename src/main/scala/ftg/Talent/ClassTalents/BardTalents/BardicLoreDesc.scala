package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.md
import ftg.Talent.Markdown
import ftg.Talent.TalentADT.BardicLoreTalent

case object BardicLoreDesc extends TalentDescriptor {
  override def apply(c: ftg.Character.Character) =
    BardicLoreTalent(true)

  override def name: String = "Bardic Lore"

  override def desc: Markdown =
    "You gain any 3 wises and 1 extra story per session. You take +1d on any story rolls pertaining to what you know or story details you add.".md
}
