package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.BardTalents.Bardsong.BardsongImpl
import ftg.Talent.ClassTalents.BardTalents.FriendlyFace.FriendlyFaceImpl

object TalentADT {
  sealed trait Talent derives ReadWriter
  sealed trait BardTalent extends Talent derives ReadWriter
  final case class BardsongTalent(bardsongs: Int, melodies: Int)
      extends BardTalent
      with BardsongImpl
  case object FriendlyFaceTalent extends BardTalent with FriendlyFaceImpl
}
