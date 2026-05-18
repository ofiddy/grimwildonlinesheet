package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.TalentDescriptor
import upickle.default.{ReadWriter => RW}

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
    InfluenceTalentDesc
  )
  override def name: String = "Bard"
}

case object TestClass extends TalentClass {
  override def coreTalent: TalentDescriptor =
    FriendlyFaceDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
  )
  override def name: String = "Test"
}

object TalentsRefs {
  def allPathTalents: List[TalentDescriptor] =
    allClasses.flatMap(_.nonCoreTalents)
  def allClasses = List(BardClass, TestClass)
}
