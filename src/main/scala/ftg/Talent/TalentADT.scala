package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.BardTalents._

object TalentADT {
  sealed trait Talent     extends TalentImpl derives ReadWriter
  sealed trait BardTalent extends Talent derives ReadWriter
  final case class BardsongTalent(bardsongs: Int, melodies: Int)
      extends BardTalent
      with TalentImpl(BardsongTalentDesc)
  case object FriendlyFaceTalent
      extends BardTalent
      with TalentImpl(FriendlyFaceDesc)
  final case class BardicLoreTalent(story: Boolean)
      extends BardTalent
      with TalentImpl(BardicLoreDesc)
  case object DynamicEntranceTalent
      extends BardTalent
      with TalentImpl(DynamicEntranceDesc)
  final case class ForkedTongueTalent(marked: Boolean)
      extends BardTalent
      with TalentImpl(ForkedTongueDesc)
}
