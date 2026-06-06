package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.TalentADT.VerdantWhispersTalent
import ftg.Talent.md

object VerdantWhispersDesc extends TalentDescriptor {

  override def name: String = "Verdant Whispers"

  override def desc: Markdown =
    "You can commune with plants. Roll Wits to decipher their messages, as they are truly hard to understand. You always get a **hint** or **reveal** on vigilance in places teeming with plant life.".md

  override def apply(c: ftg.Character.Character): Talent = VerdantWhispersTalent

}
