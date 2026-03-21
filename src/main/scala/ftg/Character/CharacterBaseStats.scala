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
    def brawn: StatPool            = b.leftPool
    def agility: StatPool          = b.rightPool
    def isBloodied: Boolean        = b.markedPool.isDefined
    def bloodied: Option[DicePool] = b.markedPool
  }

  protected final case class FluentWithBrawn(b: Int) {
    infix def withAgility(a: Int): BodyStats =
      StatGroup(
        StatPool(DicePool(b), false),
        StatPool(DicePool(a), false),
        None
      )
  }

  infix def withBrawn(b: Int) = FluentWithBrawn(b)
}

opaque type MentalStats = StatGroup

object MentalStats {
  extension (m: MentalStats) {
    def wits: StatPool            = m.leftPool
    def presence: StatPool        = m.rightPool
    def isRattled: Boolean        = m.markedPool.isDefined
    def rattled: Option[DicePool] = m.markedPool
  }

  protected final case class FluentWithWits(w: Int) {
    infix def withPresence(p: Int): MentalStats =
      StatGroup(
        StatPool(DicePool(w), false),
        StatPool(DicePool(p), false),
        None
      )
  }

  infix def withWits(w: Int) = FluentWithWits(w)
}

final case class CharacterBaseStats(
    bodyStats: BodyStats,
    mentalStats: MentalStats
)
