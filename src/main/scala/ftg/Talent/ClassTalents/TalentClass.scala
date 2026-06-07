package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.TalentDescriptor
import upickle.default.{ReadWriter => RW}
import ftg.Talent.ClassTalents.ArtificerTalent._
import ftg.Talent.ClassTalents.BerserkerTalents._
import ftg.Talent.ClassTalents.ClericTalents._
import ftg.Talent.ClassTalents.DruidTalents._
import ftg.Talent.ClassTalents.FighterTalents._
import ftg.Talent.ClassTalents.MonkTalents._
import ftg.Talent.ClassTalents.PaladinTalents._
import ftg.Talent.ClassTalents.PsionTalents._
import ftg.Talent.ClassTalents.RangerTalents._
import ftg.Talent.ClassTalents.RogueTalents._
import ftg.Talent.ClassTalents.SorcererTalents._
import ftg.Talent.ClassTalents.WarlockTalents._
import ftg.Talent.ClassTalents.WizardTalents._

sealed trait TalentClass derives RW {
  def coreTalent: TalentDescriptor
  def nonCoreTalents: List[TalentDescriptor]
  def name: String
}

case object BardClass extends TalentClass {
  override def coreTalent: TalentDescriptor =
    BardsongTalentDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
    BardicLoreDesc,
    FriendlyFaceDesc,
    DynamicEntranceDesc,
    ForkedTongueDesc,
    InfluenceTalentDesc,
    JackOfAllTradesDesc,
    WordplayDesc
  )
  override def name: String = "Bard"
}

case object ArtificerClass extends TalentClass {

  override def coreTalent: TalentDescriptor = IngenuityDesc

  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      AnchorshotDesc,
      AutomatonsDesc,
      DoubleBarreledBlunderbussDesc,
      GrenadesDesc,
      MechanicalMountDesc,
      SteamhammerDesc,
      SwiftwingDesc
    )

  override def name: String = "Artificer"

}

case object BerserkerClass extends TalentClass {

  override def coreTalent: TalentDescriptor = FrenzyDesc

  override def nonCoreTalents: List[TalentDescriptor] = List(
    FearsomeDesc,
    FleshWoundsDesc,
    IntoTheFrayDesc,
    JoyfulWarriorDesc,
    MightyDesc,
    OverkillDesc,
    WarsongsDesc
  )

  override def name: String = "Berserker"

}

case object ClericClass extends TalentClass {
  override def coreTalent: TalentDescriptor = ChannelDivinityDesc

  override def nonCoreTalents: List[TalentDescriptor] =
    List(BlessedDesc, DevoutDesc, HealerDesc, IronWillDesc)

  override def name: String = "Cleric"
}

case object DruidClass extends TalentClass {
  override def coreTalent: TalentDescriptor = WildShapeDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      AwakenDesc,
      HerbalismDesc,
      KindredSpiritsDesc,
      PrimordialBondsDesc,
      TrueShapeDesc,
      VerdantWhispersDesc,
      WindcallerDesc
    )
  override def name: String = "Druid"
}

case object FighterClass extends TalentClass {
  override def coreTalent: TalentDescriptor = WeaponMasteryDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      ArcaneTrainingDesc,
      BulwarkDesc,
      ControlDesc,
      GotYourBackDesc,
      MeasuredTonesDesc,
      SwiftRecoveryDesc,
      TacticianDesc
    )
  override def name: String = "Fighter"
}

case object MonkClass extends TalentClass {
  override def coreTalent: TalentDescriptor = DisciplineDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
    FlowStateDesc,
    HealingHandsDesc,
    LightningReflexesDesc,
    MindOverMatterDesc,
    PrimordialForcesDesc,
    PrimordialForcesIIDesc,
    TetherDesc,
    ThereIsNoTryDesc
  )
  override def name: String = "Monk"
}

case object PaladinClass extends TalentClass {
  override def coreTalent: TalentDescriptor = OathswornDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      AegisDesc,
      AuthorityDesc,
      ChallengeDesc,
      DauntlessDesc,
      DivineBlessingDesc,
      GuardianDesc,
      RebukeDesc
    )
  override def name: String = "Paladin"
}

case object PsionClass extends TalentClass {
  override def coreTalent: TalentDescriptor = AwakenedMindDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      DisturbedMindDesc,
      MindSeedDesc,
      MindThiefDesc,
      PsychicWarriorDesc,
      ReaderDesc,
      TumultuousMindDesc,
      WilderDesc
    )
  override def name: String = "Psion"
}

case object RangerClass extends TalentClass {
  override def coreTalent: TalentDescriptor = HuntersMarkDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
    AnimalCompanionDesc,
    AnimalCompanionIIDesc,
    KeenSensesDesc,
    RelentlessDesc,
    ScoutAheadDesc,
    SeasonedHunterDesc,
    SharpshooterDesc,
    TrophiesDesc
  )
  override def name: String = "Ranger"
}

case object RogueClass extends TalentClass {
  override def coreTalent: TalentDescriptor = ExpertiseDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      AccordingToPlanDesc,
      EldritchAffinityDesc,
      LurkerDesc,
      OpportunistDesc,
      PoisonerDesc,
      TrapSenseDesc,
      WeaselDesc
    )
  override def name: String = "Rogue"
}

case object SorcererClass extends TalentClass {
  override def coreTalent: TalentDescriptor = SorceryDesc
  override def nonCoreTalents: List[TalentDescriptor] =
    List(
      EldritchGrowthDesc,
      MaelstromDesc,
      MagicSenseDesc,
      SpelleaterDesc,
      SubtleCastingDesc,
      WispsDesc,
      WrathDesc
    )
  override def name: String = "Sorcerer"
}

case object WarlockClass extends TalentClass {
  override def coreTalent: TalentDescriptor = PactDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
    EldritchWeaponryDesc,
    HexDesc,
    KnowingGazeDesc,
    OtherworldlyFormDesc,
    RitualistDesc,
    VisionsDesc,
    WayfarerDesc
  )
  override def name: String = "Warlock"
}

case object WizardClass extends TalentClass {
  override def coreTalent: TalentDescriptor = SpellcraftDesc
  override def name: String                 = "Wizard"
  override def nonCoreTalents: List[TalentDescriptor] =
    List(AlchemistDesc, ArcaneSpecialtyDesc, ArcanistDesc, ColleaguesDesc)
}

object TalentsRefs {
  def allPathTalents: List[TalentDescriptor] =
    allClasses.flatMap(_.nonCoreTalents)
  def allClasses =
    List(
      ArtificerClass,
      BardClass,
      BerserkerClass,
      ClericClass,
      DruidClass,
      FighterClass,
      MonkClass,
      PaladinClass,
      PsionClass,
      RangerClass,
      RogueClass,
      SorcererClass,
      WarlockClass,
      WizardClass
    )
}
