package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Character.LevelGrowth.`and every 2 levels`
import ftg.Talent.TalentADT.WildShapeTalent

case object WildShapeDesc extends TalentDescriptor {

  override def name: String = "Wild Shape"

  override def desc: Markdown =
    "Shift into any familiar beast form, rolling a 4d Wild Shape pool. At 0d, you fail to shift. It replenishes after each scene. You take on the form’s physical qualities and feral instincts, moving stat points to represent this (*min 1, max 3*). You lose access to your own physical talents. Drop 1d for each ***wild talent*** (max 2), like *aquatic, smaller than a cat, bigger than a bear, venomous,* and *flight*. This can also be a specific talent from any path or a talent you make. [Growth: 2 levels/Wild Shape +1d]".md

  override def apply(c: ftg.Character.Character): Talent = {
    given Int = c.level
    WildShapeTalent(
      DicePool(4 `and every 2 levels` (_ + 1))
    )
  }

}
