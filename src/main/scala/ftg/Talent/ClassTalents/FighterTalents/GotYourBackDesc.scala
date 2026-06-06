package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.GotYourBackTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object GotYourBackDesc extends TalentDescriptor {

  override def name: String = "Got Your Back"

  override def desc: Markdown =
    "You can **push yourself** to assist an ally after they roll a grim. The ally can also roll 1d. On a perfect, you both **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = GotYourBackTalent(
    false
  )

}
