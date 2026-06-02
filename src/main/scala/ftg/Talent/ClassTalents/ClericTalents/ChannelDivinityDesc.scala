package ftg.Talent.ClassTalents.ClericTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.ChannelDivinityTalent
import ftg.Talent.TalentADT.LabelledPool
import ftg.DicePool.DicePool

case object ChannelDivinityDesc extends TalentDescriptor {

  override def name: String = "Channel Divinity"

  override def desc: Markdown =
    "Your god grants you spellcasting ability. You roll a domain pool to cast and that domain pool and your god’s epithet serve as touchstones. Your major domain is a 6d pool and your minor domains are 4d pools. They replenish each session. You can drop 1d and roll the domain to cast a potent spell. [Growth: 2 levels/increase one domain pool by 1d (max 8d).]".md

  override def apply(c: ftg.Character.Character): Talent =
    ChannelDivinityTalent(
      (
        LabelledPool(None, DicePool(6)),
        LabelledPool(None, DicePool(4)),
        LabelledPool(None, DicePool(4))
      ),
      (0, 0, 0)
    )

}
