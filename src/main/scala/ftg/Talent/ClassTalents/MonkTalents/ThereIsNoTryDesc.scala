package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ThereIsNoTryTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object ThereIsNoTryDesc extends TalentDescriptor {

  override def name: String = "There Is No Try"

  override def desc: Markdown =
    "When putting your life or something you hold equally dear on the line, ***5s count as 6s, but 4s count as 1s***. This generally occurs with high risk or after being bloodied in battle.".md

  override def apply(c: ftg.Character.Character): Talent = ThereIsNoTryTalent

}
