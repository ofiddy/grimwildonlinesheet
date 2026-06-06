package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.OtherworldlyFormTalent

object OtherworldlyFormDesc extends TalentDescriptor {

  override def name: String = "Otherworldly Form"

  override def desc: Markdown =
    "You can turn into a floating, dispersed form, flavored after your patron’s trappings. Choose three things you can do in this form: *appear barely visible*—*fly high in the sky*—*interact with objects*—*move swiftly*—*slip through small gaps*—*speak*.".md

  override def apply(c: ftg.Character.Character): Talent =
    OtherworldlyFormTalent

}
