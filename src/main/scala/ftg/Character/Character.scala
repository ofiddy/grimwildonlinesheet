package ftg.Character

import ftg.util.Util.opaqueIntRW
import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}
import scala.annotation.tailrec

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
  def level: Int = Character.level(experience.toInt)
}

object Character {
  def level(xp: Int) = {
    @tailrec
    def levelHelper(
        foundLevel: Int,
        remainingXp: Int,
        thresholds: List[Int]
    ): Int = thresholds match
      case head :: next =>
        val xp2 = remainingXp - head
        if xp2 < 0 then foundLevel else levelHelper(head, xp2, next)
      case Nil => foundLevel

    levelHelper(0, xp, List(1, 2, 3, 4, 5, 6, 7))

  }
}
