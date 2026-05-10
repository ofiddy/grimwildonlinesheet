package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.BardTalents.Bardsong.BardsongTalentDescriptor
import ftg.Talent.ClassTalents.BardTalents.FriendlyFace.FriendlyFaceDesc

object TalentADT {
  sealed trait Talent     extends TalentImpl derives ReadWriter
  sealed trait BardTalent extends Talent derives ReadWriter
  final case class BardsongTalent(bardsongs: Int, melodies: Int)
      extends BardTalent
      with TalentImpl(BardsongTalentDescriptor)
  case object FriendlyFaceTalent
      extends BardTalent
      with TalentImpl(FriendlyFaceDesc)
}
