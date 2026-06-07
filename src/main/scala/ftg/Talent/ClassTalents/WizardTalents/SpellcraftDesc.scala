package ftg.Talent.ClassTalents.WizardTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Character.LevelGrowth.`and every 2 levels`
import ftg.Talent.TalentADT.SpellcraftTalent

object SpellcraftDesc extends TalentDescriptor {

  override def name: String = "Spellcraft"

  override def desc: Markdown =
    "You have spellcasting ability. You roll Wits to cast and the spell theorem you are casting serves as its touchstone. Each session, you can cast 4 spells and 2 potent spells. You choose which spell theorem to use when you cast. You can learn new spell theorems from studying and experimenting with scrolls. This consumes the scroll. [Growth: 2 levels/1 spell, potent spell, and spell theorem]".md

  override def apply(c: ftg.Character.Character): SpellcraftTalent = {
    given Int = c.level
    SpellcraftTalent(
      4 `and every 2 levels` (_ + 1),
      2 `and every 2 levels` (_ + 1),
      (None, None, None, None, None, None, None)
    )
  }

}
