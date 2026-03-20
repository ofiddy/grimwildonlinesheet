package ftg.Character

import ftg.Talent.Talent

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
    experience: Int,
    charClass: CharacterClass,
    talents: List[Talent],
    notes: String
)
