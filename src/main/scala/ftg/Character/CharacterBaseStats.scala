package ftg.Character

import ftg.DicePool.DicePool

final case class StatPool(dice: DicePool, isMarked: Boolean)

final case class StatGroup(
    leftPool: StatPool,
    rightPool: StatPool,
    markedPool: Option[DicePool]
)

opaque type BodyStats = StatGroup

object BodyStats {
  extension (b: BodyStats) {
    def brawn: StatPool              = b.leftPool
    def agility: StatPool            = b.rightPool
    def isBloodied: Option[DicePool] = b.markedPool
  }
}

opaque type MentalStats = StatGroup

object MentalStats {
  extension (m: MentalStats) {
    def wits: StatPool              = m.leftPool
    def presence: StatPool          = m.rightPool
    def isRattled: Option[DicePool] = m.markedPool
  }
}

final case class CharacterBaseStats(
    bodyStats: BodyStats,
    mentalStats: MentalStats
)
