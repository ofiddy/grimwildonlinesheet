package ftg.Talent.ClassTalents.SorcererTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.WispsTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object WispsDesc extends TalentDescriptor {

  override def name: String = "Wisps"

  override def desc: Markdown =
    "Two small spheres of your magic path float around you. Give them 2 traits: *annoying*—*capricious*—*malevolent*—*mesmerizing*—*whimsical*. They serve as touchstones. You can sacrifice one to: *assist without risk*—*distract someone*—*suffer vex in your stead*. Make story rolls to determine effects if sacrificed. They return each session.".md

  override def apply(c: ftg.Character.Character): Talent =
    WispsTalent((None, None), 0)

}
