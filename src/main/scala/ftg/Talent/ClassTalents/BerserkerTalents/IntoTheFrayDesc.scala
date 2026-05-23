package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.IntoTheFrayTalent

case object IntoTheFrayDesc extends TalentDescriptor {

  override def name: String = "Into the Fray"

  override def desc: Markdown =
    "When you’re the first into an intense situation, ***5s count as 6s, but 4s count as 1s*** on your first roll. You also **setup** the first person following you regardless of your roll outcome.".md

  override def apply(c: ftg.Character.Character): Talent = IntoTheFrayTalent

}
