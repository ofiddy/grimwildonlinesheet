package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.DevoutTalent

case object DevoutDesc extends TalentDescriptor {

  override def name: String = "Devout"

  override def desc: Markdown =
    " Intelligent creatures recognize you as a person of deep honesty and only your most hated enemies would treat you with a lack of respect or doubt your word. This aura is upheld by your unwavering commitment—you must **push yourself** to act in bad faith (*no free activation*).".md

  override def apply(c: ftg.Character.Character): Talent = DevoutTalent

}
