package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents._
import ftg.Talent.TalentDescriptor

sealed trait TalentClass {
  def coreTalent: TalentDescriptor
  def nonCoreTalents: List[TalentDescriptor]
}

case object BardClass extends TalentClass {
  override def coreTalent: TalentDescriptor =
    BardsongTalentDesc
  override def nonCoreTalents: List[TalentDescriptor] = List(
    BardicLoreDesc,
    FriendlyFaceDesc
  )
}

object TalentsRefs {
  val allBaseTalents: List[TalentDescriptor] = BardClass.nonCoreTalents
}
