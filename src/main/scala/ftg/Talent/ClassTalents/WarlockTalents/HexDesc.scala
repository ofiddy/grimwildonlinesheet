package ftg.Talent.ClassTalents.WarlockTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.HexTalent
import ftg.Talent.Markdown
import ftg.Talent.md

object HexDesc extends TalentDescriptor {

  override def name: String = "Hex"

  override def desc: Markdown =
    "You can roll Presence to hex someone with: *dread*—*clumsiness*—*confusion*—*forgetfulness*—*misfortune*—*sleepiness*. Decide when it takes effect: *now*—*soon*—*much later*—*specific trigger*. This is a cosmic effect. It’s magic, but not fully under your control.".md

  override def apply(c: ftg.Character.Character): Talent = HexTalent

}
