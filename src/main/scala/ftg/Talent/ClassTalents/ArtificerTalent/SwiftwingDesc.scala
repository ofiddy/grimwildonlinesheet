package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.SwiftwingTalent
import ftg.DicePool.DicePool

case object SwiftwingDesc extends TalentDescriptor {

  override def name: String = "Swiftwing"

  override def desc: Markdown =
    "You can glide (*not fly*). You gain a ***3d power pool*** while aloft, or ***4d*** if you have extreme height or speed. You must roll the pool as bonus dice with any physical roll made while aloft, including defenses. At 0d, you coast back to the ground.".md

  override def apply(c: ftg.Character.Character): Talent = SwiftwingTalent(
    DicePool(0)
  )

}
