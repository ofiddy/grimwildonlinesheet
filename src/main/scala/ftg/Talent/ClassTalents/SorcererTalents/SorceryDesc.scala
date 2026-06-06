package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.SorceryTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object SorceryDesc extends TalentDescriptor {

  override def name: String = "Sorcery"

  override def desc: Markdown =
    "You have spellcasting ability. You roll Presence to cast and your magic paths and techniques serve as touchstones. You can cast spells at-will, though they always carry risk and require a roll. You can **push yourself** to cast a potent spell, but gain no free activation of it. When casting, rolling two or more 1s triggers a secondary ***wild surge***—raw magic spirals out of your control. Make a 2d story roll to see what happens. Use the GM crucible or ask your group for ideas. [Growth: 2 levels/ gain a new technique or magic path]".md

  override def apply(c: ftg.Character.Character): Talent = SorceryTalent(
    (None, None, None, None, None, None, None)
  )

}
