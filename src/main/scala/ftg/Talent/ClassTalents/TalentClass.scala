package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.TalentDescriptor
import upickle.default.{ReadWriter => RW}
import ftg.Talent.ClassTalents.ArtificerTalent._

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
    List(AnchorshotDesc, AutomatonsDesc)

  override def name: String = "Artificer"

}

object TalentsRefs {
  def allPathTalents: List[TalentDescriptor] =
    allClasses.flatMap(_.nonCoreTalents)
  def allClasses = List(BardClass, ArtificerClass)
}
