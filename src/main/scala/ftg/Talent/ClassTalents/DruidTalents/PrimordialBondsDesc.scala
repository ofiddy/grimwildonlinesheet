package ftg.Talent.ClassTalents.DruidTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.PrimordialBondsTalent

case object PrimordialBondsDesc extends TalentDescriptor {

  override def name: String = "Primordial Bonds"

  override def desc: Markdown =
    "You have bonds with the elements: ***Air***, ***Earth***, ***Fire***, and ***Water***. You have spellcasting, rolling Presence to call on an element, which serves as the touchstone. You can call on each element once per session, or twice if your bond is ***deep***. Mark two usages of a single element to cast a potent spell. Combine usages of two elements to take +1d and both touchstones on the roll.".md

  override def apply(c: ftg.Character.Character): Talent =
    PrimordialBondsTalent((0, false), (0, false), (0, false), (0, false))

}
