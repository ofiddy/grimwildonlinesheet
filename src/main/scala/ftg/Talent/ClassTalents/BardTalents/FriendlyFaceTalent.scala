package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentImpl
import ftg.Talent.TalentDescriptor

private[Talent] object FriendlyFace {

  trait FriendlyFaceImpl extends TalentImpl {
    override def talentDesc: TalentDescriptor = FriendlyFaceDesc
  }

  case object FriendlyFaceDesc extends TalentDescriptor {
    override def apply(c: ftg.Character.Character) =
      ftg.Talent.TalentADT.FriendlyFaceTalent

    override def name: String = "Friendly Face"

    override def desc: Markdown =
      "In any new town or district of a city you go to, you can always: *know someone useful*—*quickly make a friend*—*be recognized by a fan*. They’ll gladly do you a reasonable favor. **Take spark** if you promise to pay them back.".md
  }

}
