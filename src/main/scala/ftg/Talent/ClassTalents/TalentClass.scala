package ftg.Talent.ClassTalents

import ftg.Talent.ClassTalents.BardTalents.Bardsong.BardsongTalentDescriptor
import ftg.Talent.TalentDescriptor
import ftg.Talent.ClassTalents.BardTalents.FriendlyFace.FriendlyFaceDesc

sealed trait TalentClass {
  def coreTalent: TalentDescriptor
  def nonCoreTalents: List[TalentDescriptor]
}

case object BardClass extends TalentClass {
  override def coreTalent: TalentDescriptor =
    BardsongTalentDescriptor
  override def nonCoreTalents: List[TalentDescriptor] = List(
    FriendlyFaceDesc
  )
}
