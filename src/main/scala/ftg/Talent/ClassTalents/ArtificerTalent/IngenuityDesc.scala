package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.IngenuityTalent

case object IngenuityDesc extends TalentDescriptor {

  override def name: String = "Ingenuity"

  override def desc: Markdown =
    """Begin play with 1 minor and 1 major arcana. When given time, you can pull off ***potent feats of mechanical ingenuity***. You can push yourself to do it on the spot. Others can use your gadgets, but take +1t and must always roll to use them. [Growth: 2 levels/1 minor arcana and 1 major arcana]
***Engineering***: Take +1d at creating, repairing, or destroying mechanisms and arcana.""".md

  override def apply(c: ftg.Character.Character): Talent = IngenuityTalent(
    false
  )

}
