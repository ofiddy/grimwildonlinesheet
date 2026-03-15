package ftg.Character

final case class Character(
    profile: CharacterProfile,
    stats: CharacterBaseStats,
    conditions: List[Condition],
    story: Story,
    spark: Spark,
    details: CharacterDetails,
    bonds: List[Bond]
)
