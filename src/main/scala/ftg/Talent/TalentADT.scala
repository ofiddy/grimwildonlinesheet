package ftg.Talent

import upickle._
import ftg.Talent.ClassTalents.ArtificerTalent._
import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.ClassTalents.BerserkerTalents._
import ftg.Talent.ClassTalents.ClericTalents._
import ftg.Talent.ClassTalents.DruidTalents._
import ftg.Talent.ClassTalents.FighterTalents._
import ftg.Talent.ClassTalents.MonkTalents._
import ftg.Talent.ClassTalents.PaladinTalents._
import ftg.Character.Wise
import ftg.DicePool.DicePool
import ftg.Talent.ClassTalents.ArtificerTalent.MechanicalMountDesc.MechanicalMountFeatures
import ftg.Talent.ClassTalents.PsionTalents._
import ftg.Talent.ClassTalents.RangerTalents._
import ftg.Talent.ClassTalents.RogueTalents._
import ftg.Talent.ClassTalents.SorcererTalents._
import ftg.Talent.ClassTalents.WarlockTalents._
import ftg.Talent.ClassTalents.WizardTalents._

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
  final case class ControlTalent(marked: Boolean)
      extends FighterTalent
      with TalentImpl(ControlDesc)
  final case class GotYourBackTalent(marked: Boolean)
      extends FighterTalent
      with TalentImpl(GotYourBackDesc)
  final case class MeasuredTonesTalent(marked: Boolean)
      extends FighterTalent
      with TalentImpl(MeasuredTonesDesc)
  case object SwiftRecoveryTalent
      extends FighterTalent
      with TalentImpl(SwiftRecoveryDesc)
  final case class TacticianTalent(marked: Boolean)
      extends FighterTalent
      with TalentImpl(TacticianDesc)

  sealed trait MonkTalent extends Talent derives ReadWriter
  final case class DisciplineTalent(flow: Int, interrupt: Int)
      extends MonkTalent
      with TalentImpl(DisciplineDesc)
  case object FlowStateTalent extends MonkTalent with TalentImpl(FlowStateDesc)
  case object HealingHandsTalent
      extends MonkTalent
      with TalentImpl(HealingHandsDesc)
  case object LightningReflexesTalent
      extends MonkTalent
      with TalentImpl(LightningReflexesDesc)
  case object MindOverMatterTalent
      extends MonkTalent
      with TalentImpl(MindOverMatterDesc)
  final case class PrimordialForcesTalent(
      elem: Option[String],
      charged: Boolean
  ) extends MonkTalent
      with TalentImpl(PrimordialForcesDesc)
  final case class PrimordialForcesIITalent(
      elem: Option[String],
      charged: Boolean
  ) extends MonkTalent
      with TalentImpl(PrimordialForcesIIDesc)
  final case class TetherTalent(tether: Boolean, push: Boolean)
      extends MonkTalent
      with TalentImpl(TetherDesc)
  case object ThereIsNoTryTalent
      extends MonkTalent
      with TalentImpl(ThereIsNoTryDesc)

  sealed trait PaladinTalent extends Talent derives ReadWriter
  final case class OathswornTalent(
      smite: Int,
      tenets: (Option[String], Option[String], Option[String])
  ) extends PaladinTalent
      with TalentImpl(OathswornDesc)
  final case class AegisTalent(marked: Boolean)
      extends PaladinTalent
      with TalentImpl(AegisDesc)
  final case class AuthorityTalent(marked: Boolean)
      extends PaladinTalent
      with TalentImpl(AuthorityDesc)
  final case class ChallengeTalent(marked: Boolean)
      extends PaladinTalent
      with TalentImpl(ChallengeDesc)
  case object DauntlessTalent
      extends PaladinTalent
      with TalentImpl(DauntlessDesc)
  final case class DivineBlessingTalent(lPool: LabelledPool)
      extends PaladinTalent
      with TalentImpl(DivineBlessingDesc)
  case object GuardianTalent extends PaladinTalent with TalentImpl(GuardianDesc)
  case object RebukeTalent   extends PaladinTalent with TalentImpl(RebukeDesc)

  sealed trait PsionTalent extends Talent derives ReadWriter
  final case class AwakenedMindTalent(
      power: Int,
      bastions: (
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String]
      )
  ) extends PsionTalent
      with TalentImpl(AwakenedMindDesc)
  final case class DisturbedMindTalent(marked: Boolean)
      extends PsionTalent
      with TalentImpl(DisturbedMindDesc)
  final case class MindSeedTalent(marked: Boolean)
      extends PsionTalent
      with TalentImpl(MindSeedDesc)
  case object MindThiefTalent extends PsionTalent with TalentImpl(MindThiefDesc)
  case object PsychicWarriorTalent
      extends PsionTalent
      with TalentImpl(PsychicWarriorDesc)
  case object TumultuousMindTalent
      extends PsionTalent
      with TalentImpl(TumultuousMindDesc)
  case object WilderTalent extends PsionTalent with TalentImpl(WilderDesc)
  final case class ReaderTalent(marked: Boolean)
      extends PsionTalent
      with TalentImpl(ReaderDesc)

  sealed trait RangerTalent extends Talent derives ReadWriter
  final case class HuntersMarkTalent(weakness: Int)
      extends RangerTalent
      with TalentImpl(HuntersMarkDesc)
  final case class AnimalCompanionTalent(
      tricks: (
          Option[String],
          Option[String],
          Option[String]
      ),
      flaws: (Option[String], Option[String]),
      marked: Boolean,
      hurt: Boolean
  ) extends RangerTalent
      with TalentImpl(AnimalCompanionDesc)
  final case class AnimalCompanionIITalent(
      tricks: (
          Option[String],
          Option[String],
          Option[String]
      ),
      marked: Boolean
  ) extends RangerTalent
      with TalentImpl(AnimalCompanionIIDesc)
  case object KeenSensesTalent
      extends RangerTalent
      with TalentImpl(KeenSensesDesc)
  case object RelentlessTalent
      extends RangerTalent
      with TalentImpl(RelentlessDesc)
  final case class ScoutAheadTalent(marked: Boolean)
      extends RangerTalent
      with TalentImpl(ScoutAheadDesc)
  final case class SeasonedHunterTalent(marked: Boolean)
      extends RangerTalent
      with TalentImpl(SeasonedHunterDesc)
  final case class SharpshooterTalent(marked: Boolean)
      extends RangerTalent
      with TalentImpl(SharpshooterDesc)
  final case class TrophiesTalent(trophy: Option[String])
      extends RangerTalent
      with TalentImpl(TrophiesDesc)

  sealed trait RogueTalent extends Talent derives ReadWriter
  final case class ExpertiseTalent(
      expertise: Option[String],
      contingency: DicePool
  ) extends RogueTalent
      with TalentImpl(ExpertiseDesc)
  final case class AccordingToPlanTalent(marked: Boolean)
      extends RogueTalent
      with TalentImpl(AccordingToPlanDesc)
  final case class EldritchAffinityTalent(
      pathsAndTechs: (Option[String], Option[String], Option[String])
  ) extends RogueTalent
      with TalentImpl(EldritchAffinityDesc)
  final case class LurkerTalent(marked: Boolean)
      extends RogueTalent
      with TalentImpl(LurkerDesc)
  final case class OpportunistTalent(marked: Boolean)
      extends RogueTalent
      with TalentImpl(OpportunistDesc)
  final case class PoisonerTalent(pool: DicePool)
      extends RogueTalent
      with TalentImpl(PoisonerDesc)
  case object TrapSenseTalent extends RogueTalent with TalentImpl(TrapSenseDesc)
  final case class WeaselTalent(marked: Boolean)
      extends RogueTalent
      with TalentImpl(WeaselDesc)

  sealed trait SorcererTalent extends Talent derives ReadWriter
  final case class SorceryTalent(
      pathsAndTechs: (
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String]
      )
  ) extends SorcererTalent
      with TalentImpl(SorceryDesc)
  case object EldritchGrowthTalent
      extends SorcererTalent
      with TalentImpl(EldritchGrowthDesc)
  case object MaelstromTalent
      extends SorcererTalent
      with TalentImpl(MaelstromDesc)
  case object MagicSenseTalent
      extends SorcererTalent
      with TalentImpl(MagicSenseDesc)
  final case class SpelleaterTalent(pool: DicePool, essence: Int)
      extends SorcererTalent
      with TalentImpl(SpelleaterDesc)
  case object SubtleCastingTalent
      extends SorcererTalent
      with TalentImpl(SubtleCastingDesc)
  final case class WispsTalent(
      traits: (Option[String], Option[String]),
      sacrificed: Int
  ) extends SorcererTalent
      with TalentImpl(WispsDesc)
  case object WrathTalent extends SorcererTalent with TalentImpl(WrathDesc)

  sealed trait WarlockTalent extends Talent derives ReadWriter
  final case class PactTalent(
      gifts: (Option[String], Option[String]),
      patience: DicePool
  ) extends WarlockTalent
      with TalentImpl(PactDesc)
  final case class EldritchWeaponryTalent(marked: Boolean)
      extends WarlockTalent
      with TalentImpl(EldritchWeaponryDesc)
  case object HexTalent extends WarlockTalent with TalentImpl(HexDesc)
  final case class KnowingGazeTalent(marked: Boolean)
      extends WarlockTalent
      with TalentImpl(KnowingGazeDesc)
  case object OtherworldlyFormTalent
      extends WarlockTalent
      with TalentImpl(OtherworldlyFormDesc)
  case object RitualistTalent
      extends WarlockTalent
      with TalentImpl(RitualistDesc)
  case object VisionsTalent extends WarlockTalent with TalentImpl(VisionsDesc)
  final case class WayfarerTalent(marked: Boolean)
      extends WarlockTalent
      with TalentImpl(WayfarerDesc)

  sealed trait WizardTalent extends Talent derives ReadWriter
  final case class SpellcraftTalent(
      spells: Int,
      potentSpells: Int,
      theorems: (
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String],
          Option[String]
      )
  ) extends WizardTalent
      with TalentImpl(SpellcraftDesc)

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
