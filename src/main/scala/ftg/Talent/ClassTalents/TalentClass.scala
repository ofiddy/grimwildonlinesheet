package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.TalentDescriptor
import upickle.default.{ReadWriter => RW}
import ftg.Talent.ClassTalents.ArtificerTalent._
import ftg.Talent.ClassTalents.BerserkerTalents._

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
    OverkillDesc
  )

  override def name: String = "Berserker"

}

object TalentsRefs {
  def allPathTalents: List[TalentDescriptor] =
    allClasses.flatMap(_.nonCoreTalents)
  def allClasses = List(ArtificerClass, BardClass, BerserkerClass)
}
