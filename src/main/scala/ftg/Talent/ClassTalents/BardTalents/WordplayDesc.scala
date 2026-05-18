package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.WordplayTalent

case object WordplayDesc extends TalentDescriptor {

  override def name: String = "Wordplay"

  override def desc: Markdown =
    "On a perfect defense roll when the situation allows for verbal quips, you make them: *embarrass themself*—*let a secret slip*—*focus on or lose track of you*. Once per session, you can ***goad*** the GM into spending suspense on conversation.".md

  override def apply(c: ftg.Character.Character): Talent = WordplayTalent(false)

}
