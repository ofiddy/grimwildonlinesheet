package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentDescriptor
import ftg.Character.LevelGrowth.`and every 3 levels`
import ftg.Talent.TalentADT.BardsongTalent

case object BardsongTalentDesc extends TalentDescriptor {
  override def apply(c: ftg.Character.Character): BardsongTalent = {
    given level: Int = c.level
    val max          = 3 `and every 3 levels` (_ + 1)
    BardsongTalent(max, max)
  }

  override def name: String = "Bardsong"

  override def desc: Markdown =
    "You can sing **3 bardsongs** (*session*). Choose a **Style + Tune of Impact** and roll Presence. Sing one to ***pull off a potent feat of emotional influence*** or ***interrupt*** any impact move. You can also sing **3 melodies** (*session*) without a roll or composition to: *assist without risk*—*calm or intensify a vex response*—*clear a mark*. [Growth: 3 levels/+1 bardsong, +1 melody].".md
}
