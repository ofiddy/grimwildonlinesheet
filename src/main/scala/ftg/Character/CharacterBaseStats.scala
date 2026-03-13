package ftg.Character

import ftg.DicePool.DicePool

final case class CharacterBaseStats()

final case class StatPool(dice: DicePool, isMarked: Boolean)

final case class StatGroup(
    leftPool: StatPool,
    rightPool: StatPool,
    isMarked: Boolean
)

opaque type BodyStats = StatGroup

object BodyStats {
  extension (b: BodyStats) {
    def brawn: StatPool     = b.leftPool
    def agility: StatPool   = b.rightPool
    def isBloodied: Boolean = b.isMarked
  }
}

opaque type MentalStats = StatGroup

object MentalStats {
  extension (m: MentalStats) {
    def wits: StatPool     = m.leftPool
    def presence: StatPool = m.rightPool
    def isRattled: Boolean = m.isMarked
  }
}
