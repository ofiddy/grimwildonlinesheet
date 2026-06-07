package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.AegisTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object AegisDesc extends TalentDescriptor {

  override def name: String = "Aegis"

  override def desc: Markdown =
    "You take +1d when defending with a shield and can use it to make a defense roll in a nearby ally’s place. You can **push yourself** to defend multiple allies from a single attack, like blocking dragonfire. On a perfect, everyone you protected **takes spark**.".md

  override def apply(c: ftg.Character.Character): Talent = AegisTalent(false)

}
