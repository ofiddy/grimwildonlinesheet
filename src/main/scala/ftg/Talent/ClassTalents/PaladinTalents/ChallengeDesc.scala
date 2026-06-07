package ftg.Talent.ClassTalents.PaladinTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.TalentADT.ChallengeTalent
import ftg.Talent.Markdown
import ftg.Talent.md

case object ChallengeDesc extends TalentDescriptor {

  override def name: String = "Challenge"

  override def desc: Markdown =
    "**Push yourself** to challenge a foe, taking +1d on all rolls against them, but give them 1 suspense. If they do anything besides confront you, you can ***interrupt*** it—only on a perfect, the challenge continues. If an ally engages your foe, the challenge ends.".md

  override def apply(c: ftg.Character.Character): Talent = ChallengeTalent(
    false
  )

}
