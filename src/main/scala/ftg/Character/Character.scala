package ftg.Character

import ftg.util.Util.opaqueIntRW
import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}

opaque type Experience = Int

object Experience {
  extension (i: Int) def xp: Experience    = i
  extension (x: Experience) def toInt: Int = x
  given RW[Experience]                     = opaqueIntRW(identity, identity)
}

final case class Character(
    profile: CharacterProfile,
    stats: CharacterBaseStats,
    conditions: List[Condition],
    story: Story,
    spark: Spark,
    details: CharacterDetails,
    bonds: List[Bond],
    groupArc: Option[StoryArc],
    characterArc: (Option[StoryArc], Option[StoryArc]),
    experience: Experience,
    charClass: CharacterClass,
    // talents: List[Talent],
    notes: String
) derives ReadWriter {
  def level: Int = {
    val expThresholds = List(1, 2, 3, 4, 5, 6, 7)
    var foundLevel    = 0
    var remainingXp   = experience.toInt
    expThresholds.foreach { lev =>
      remainingXp -= lev
      if remainingXp < 0 then () else foundLevel = lev
    }
    return foundLevel
  }
}
