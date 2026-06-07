package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.ColleaguesTalent

case object ColleaguesDesc extends TalentDescriptor {

  override def name: String = "Colleagues"

  override def desc: Markdown =
    "In every city, some towns, and the occasional dungeon, you can find a powerful, eccentric wizardly colleague who owes you a favor—or maybe you owe them one. If you go out of your way to meet and catch up, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = ColleaguesTalent

}
