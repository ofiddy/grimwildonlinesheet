package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.ArtificerTalent._
import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.ClassTalents.BerserkerTalents._
import ftg.Talent.ClassTalents.ClericTalents._
import ftg.Talent.ClassTalents.DruidTalents._
import ftg.Talent.ClassTalents.FighterTalents._
import ftg.Character.Wise
import ftg.DicePool.DicePool
import ftg.Talent.ClassTalents.ArtificerTalent.MechanicalMountDesc.MechanicalMountFeatures

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
  final case class MechanicalMountTalent(
      features: MechanicalMountFeatures,
      drawback: Option[String]
  ) extends ArtificerTalent
      with TalentImpl(MechanicalMountDesc)
  final case class SteamhammerTalent(
      pool: DicePool
  ) extends ArtificerTalent
      with TalentImpl(SteamhammerDesc)
  final case class SwiftwingTalent(pool: DicePool)
      extends ArtificerTalent
      with TalentImpl(SwiftwingDesc)

  sealed trait BerserkerTalent extends Talent derives ReadWriter
  final case class FrenzyTalent(frenzy: Int)
      extends BerserkerTalent
      with TalentImpl(FrenzyDesc)
  final case class FearsomeTalent(marked: Boolean)
      extends BerserkerTalent
      with TalentImpl(FearsomeDesc)
  final case class FleshWoundsTalent(wounds: Int)
      extends BerserkerTalent
      with TalentImpl(FleshWoundsDesc)
  case object IntoTheFrayTalent
      extends BerserkerTalent
      with TalentImpl(IntoTheFrayDesc)
  case object JoyfulWarriorTalent
      extends BerserkerTalent
      with TalentImpl(JoyfulWarriorDesc)
  final case class MightyTalent(marked: Boolean)
      extends BerserkerTalent
      with TalentImpl(MightyDesc)
  case object OverkillTalent
      extends BerserkerTalent
      with TalentImpl(OverkillDesc)
  final case class WarsongsTalent(
      warsongs: Int,
      compositions: (Option[String], Option[String], Option[String])
  ) extends BerserkerTalent
      with TalentImpl(WarsongsDesc)

  sealed trait ClericTalent extends Talent derives ReadWriter
  final case class ChannelDivinityTalent(
      pools: (LabelledPool, LabelledPool, LabelledPool),
      upgrades: (Int, Int, Int)
  ) extends ClericTalent
      with TalentImpl(ChannelDivinityDesc)
  final case class BlessedTalent(
      marked: Boolean
  ) extends ClericTalent
      with TalentImpl(BlessedDesc)
  case object DevoutTalent extends ClericTalent with TalentImpl(DevoutDesc)
  case object HealerTalent extends ClericTalent with TalentImpl(HealerDesc)
  final case class IronWillTalent(pool: DicePool)
      extends ClericTalent
      with TalentImpl(IronWillDesc)

  sealed trait DruidTalent extends Talent derives ReadWriter
  final case class WildShapeTalent(pool: DicePool)
      extends DruidTalent
      with TalentImpl(WildShapeDesc)
  final case class AwakenTalent(pool: DicePool, ritual: Boolean)
      extends DruidTalent
      with TalentImpl(AwakenDesc)
  final case class HerbalismTalent(
      majorHerb: Option[String],
      minorHerb: Option[String],
      usedMythic: Boolean
  ) extends DruidTalent
      with TalentImpl(HerbalismDesc)
  case object KindredSpiritsTalent
      extends DruidTalent
      with TalentImpl(KindredSpiritsDesc)
  final case class PrimordialBondsTalent(
      air: (Int, Boolean),
      earth: (Int, Boolean),
      fire: (Int, Boolean),
      water: (Int, Boolean)
  ) extends DruidTalent
      with TalentImpl(PrimordialBondsDesc)
  final case class TrueShapeTalent(form: Option[String])
      extends DruidTalent
      with TalentImpl(TrueShapeDesc)
  case object VerdantWhispersTalent
      extends DruidTalent
      with TalentImpl(VerdantWhispersDesc)
  final case class WindcallerTalent(marked: Boolean)
      extends DruidTalent
      with TalentImpl(WindcallerDesc)

  sealed trait FighterTalent extends Talent derives ReadWriter
  final case class WeaponMasteryTalent(style: Option[String])
      extends FighterTalent
      with TalentImpl(WeaponMasteryDesc)
  final case class ArcaneTrainingTalent(
      spells: Int,
      potent: Boolean,
      theorems: (Option[String], Option[String], Option[String])
  ) extends FighterTalent
      with TalentImpl(ArcaneTrainingDesc)
  final case class BulwarkTalent(pool: DicePool)
      extends FighterTalent
      with TalentImpl(BulwarkDesc)

  // HELPERS
  final case class MarkableSelectable(
      marked: Boolean,
      feature: Option[String]
  ) derives ReadWriter

  final case class LabelledPool(
      label: Option[String],
      pool: DicePool
  ) derives ReadWriter

}
