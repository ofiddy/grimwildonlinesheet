package ftg.Character

import ftg.Talent.Talent

opaque type Experience = Int

object Experience {
  extension (i: Int) def xp: Experience = i
}

final case class Character(
    profile: CharacterProfile,
    stats: CharacterBaseStats,
    conditions: List[Condition],
    story: Story,
    spark: Spark,
    details: CharacterDetails,
    bonds: List[Bond],
    groupArc: StoryArc,
    characterArc: StoryArc,
    experience: Experience,
    charClass: CharacterClass,
    talents: List[Talent],
    notes: String
)
