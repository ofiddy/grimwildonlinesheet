package ftg.Talent.ClassTalents.RogueTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.EldritchAffinityTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object EldritchAffinityDesc extends TalentDescriptor {

  override def name: String = "Eldritch Affinity"

  override def desc: Markdown =
    "You have spellcasting, as the Sorcery talent. You roll Presence to cast and choose 3 from magic paths and techniques. You can’t cast potent spells, nor do you risk a wild surge.".md

  override def apply(c: ftg.Character.Character): Talent =
    EldritchAffinityTalent((None, None, None))

}
