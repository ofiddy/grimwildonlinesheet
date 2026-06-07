package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.AnchorshotTalent
import ftg.DicePool.DicePool

case object AnchorshotDesc extends TalentDescriptor {

  override def name: String = "Anchorshot"

  override def desc: Markdown =
    "You have ***potency*** to hinder the movement of anything elephant-size or larger. When hit, the target becomes ***4d Tethered***. It anchors to the ground so you can leave it.".md

  override def apply(c: ftg.Character.Character): Talent = AnchorshotTalent(
    DicePool(0)
  )

}
