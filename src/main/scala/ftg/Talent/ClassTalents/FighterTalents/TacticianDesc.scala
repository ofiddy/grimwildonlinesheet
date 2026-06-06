package ftg.Talent.ClassTalents.FighterTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.TacticianTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object TacticianDesc extends TalentDescriptor {

  override def name: String = "Tactician"

  override def desc: Markdown =
    "During an intense action sequence, you can **push yourself** to tag 3 scene elements right away and 1 later in the sequence. When an ally interacts with a tagged element, you assist without risk on the roll. If no roll is needed, they **take spark**.".md

  override def apply(c: ftg.Character.Character): Talent = TacticianTalent(
    false
  )

}
