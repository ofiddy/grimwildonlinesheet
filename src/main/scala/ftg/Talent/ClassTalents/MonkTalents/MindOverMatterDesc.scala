package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.MindOverMatterTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object MindOverMatterDesc extends TalentDescriptor {

  override def name: String = "Mind Over Matter"

  override def desc: Markdown =
    "When you take a physical mark, you can instead choose to take a mental mark. When you clear any mark by rolling it, **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = MindOverMatterTalent

}
