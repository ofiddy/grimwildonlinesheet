package ftg.Talent.ClassTalents.PsionTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ReaderTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object ReaderDesc extends TalentDescriptor {

  override def name: String = "Reader"

  override def desc: Markdown =
    "You gain 1 story per session and can spend story when you first meet someone to know their foremost surface thought—they are like an open book to you. If you follow-up on it, it counts as a **setup**.".md

  override def apply(c: ftg.Character.Character): Talent = ReaderTalent(true)

}
