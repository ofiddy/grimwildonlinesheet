package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.DivineBlessingTalent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.LabelledPool
import ftg.DicePool.DicePool

object DivineBlessingDesc extends TalentDescriptor {

  override def name: String = "Divine Blessing"

  override def desc: Markdown =
    "You have spellcasting as the Channel Divinity talent. Detail your god and choose 1 minor domain, a ***4d power pool*** *(per session)*. You can drop 1 and roll the pool to cast a potent spell.".md

  override def apply(c: ftg.Character.Character): Talent = DivineBlessingTalent(
    LabelledPool(None, DicePool(4))
  )

}
