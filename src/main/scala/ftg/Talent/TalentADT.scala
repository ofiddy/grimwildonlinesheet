package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.BardTalents._
import ftg.Character.Wise

object TalentADT {
  sealed trait Talent extends TalentImpl derives ReadWriter

  /// BARD TALENTS
  sealed trait BardTalent extends Talent derives ReadWriter
  final case class BardsongTalent(bardsongs: Int, melodies: Int)
      extends BardTalent
      with TalentImpl(BardsongTalentDesc)
  case object FriendlyFaceTalent
      extends BardTalent
      with TalentImpl(FriendlyFaceDesc)
  final case class BardicLoreTalent(
      story: Boolean,
      wises: (Option[Wise], Option[Wise], Option[Wise])
  ) extends BardTalent
      with TalentImpl(BardicLoreDesc)
  case object DynamicEntranceTalent
      extends BardTalent
      with TalentImpl(DynamicEntranceDesc)
  final case class ForkedTongueTalent(marked: Boolean)
      extends BardTalent
      with TalentImpl(ForkedTongueDesc)
  final case class InfluenceTalent(influences: Int)
      extends BardTalent
      with TalentImpl(InfluenceTalentDesc)
}
