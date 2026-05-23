package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.DicePool.DicePool
import ftg.Talent.TalentADT.AutomatonsTalent

case object AutomatonsDesc extends TalentDescriptor {

  override def name: String = "Automatons"

  override def desc: Markdown =
    "You have three small helpers you created. Each has a different descriptor, an adjective that determines what tasks they can do. Each session, each has a ***2d power pool*** you roll to per- form tasks. When assisting, they roll 1d of their pool. They can follow simple commands, without which they will simply search for you.".md

  override def apply(c: ftg.Character.Character): Talent =
    AutomatonsTalent(DicePool(2), DicePool(2), DicePool(2))

}
