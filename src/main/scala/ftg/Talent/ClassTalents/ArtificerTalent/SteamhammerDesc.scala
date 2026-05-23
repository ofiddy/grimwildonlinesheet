package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.SteamhammerTalent
import ftg.DicePool.DicePool

case object SteamhammerDesc extends TalentDescriptor {

  override def name: String = "Steamhammer"

  override def desc: Markdown =
    "Each session, you have a ***6d power pool***. You can roll the pool to pull off ***potent feats of pulverizing force***. You can also not expend its steam to use it as a normal warhammer.".md

  override def apply(c: ftg.Character.Character): Talent = SteamhammerTalent(
    DicePool(6)
  )

}
