package ftg.Talent.ClassTalents

import ftg.Talent.Talent
import ftg.Talent.ClassTalents.BardTalents.Bardsong.BardsongTalentDescriptor
import ftg.Talent.TalentDescriptor
import ftg.Talent.ClassTalents.BardTalents.FriendlyFaceTalent

sealed trait TalentClass {
  def coreTalent: TalentDescriptor[Talent]
  def nonCoreTalents: List[TalentDescriptor[Talent]]
}

case object BardClass extends TalentClass {
  override def coreTalent: TalentDescriptor[Talent] = BardsongTalentDescriptor
  override def nonCoreTalents: List[TalentDescriptor[Talent]] = List(
    FriendlyFaceTalent
  )
}
