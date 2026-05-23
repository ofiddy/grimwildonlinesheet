package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.ArtificerTalent._
import ftg.Talent.ClassTalents.BardTalents._
import ftg.Character.Wise
import ftg.DicePool.DicePool

object TalentADT {
  sealed trait Talent extends TalentImpl derives ReadWriter

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
  case object JackOfAllTradesTalent
      extends BardTalent
      with TalentImpl(JackOfAllTradesDesc)
  final case class WordplayTalent(marked: Boolean)
      extends BardTalent
      with TalentImpl(WordplayDesc)

  sealed trait ArtificerTalent extends Talent derives ReadWriter
  final case class IngenuityTalent(marked: Boolean)
      extends ArtificerTalent
      with TalentImpl(IngenuityDesc)
  final case class AnchorshotTalent(pool: DicePool)
      extends ArtificerTalent
      with TalentImpl(AnchorshotDesc)
  final case class AutomatonsTalent(
      pool1: DicePool,
      pool2: DicePool,
      pool3: DicePool
  ) extends ArtificerTalent
      with TalentImpl(AutomatonsDesc)
  final case class DoubleBarreledBlunderbussTalent(
      blast: Boolean,
      drill: Boolean,
      inferno: Boolean,
      scatter: Boolean,
      shrapnel: Boolean,
      tangler: Boolean
  ) extends ArtificerTalent
      with TalentImpl(DoubleBarreledBlunderbussDesc)
  final case class GrenadesTalent(
      pool: DicePool
  ) extends ArtificerTalent
      with TalentImpl(GrenadesDesc)
}
