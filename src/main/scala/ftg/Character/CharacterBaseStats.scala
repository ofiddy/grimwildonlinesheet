package ftg.Character

import ftg.DicePool.DicePool
import monocle.macros.GenLens
import monocle.syntax.all._

import upickle.default.ReadWriter
import upickle.default.{ReadWriter => RW}
import ftg.util.Util.opaqueRW

final case class StatPool(dice: DicePool, isMarked: Boolean) derives ReadWriter

object StatPool {
  def diceRemainingLens = GenLens[StatPool](_.dice.diceRemaining)
  def markedLens        = GenLens[StatPool](_.isMarked)
}

final case class StatGroup(
    leftPool: StatPool,
    rightPool: StatPool,
    marked: Boolean
) derives ReadWriter

opaque type BodyStats = StatGroup

object BodyStats {
  extension (b: BodyStats) {
    def brawn: StatPool     = b.leftPool
    def agility: StatPool   = b.rightPool
    def isBloodied: Boolean = b.marked
  }

  def brawnLens    = GenLens[BodyStats](_.leftPool)
  def agilityLens  = GenLens[BodyStats](_.rightPool)
  def bloodiedLens = GenLens[BodyStats](_.marked)

  protected final case class FluentWithBrawn(b: Int) {
    infix def withAgility(a: Int): BodyStats =
      StatGroup(
        StatPool(DicePool(b), false),
        StatPool(DicePool(a), false),
        false
      )
  }

  infix def withBrawn(b: Int) = FluentWithBrawn(b)

  given RW[BodyStats] = opaqueRW[BodyStats, StatGroup](identity, identity)
}

opaque type MentalStats = StatGroup

object MentalStats {
  extension (m: MentalStats) {
    def wits: StatPool     = m.leftPool
    def presence: StatPool = m.rightPool
    def isRattled: Boolean = m.marked
  }

  def witsLens     = GenLens[MentalStats](_.leftPool)
  def presenceLens = GenLens[MentalStats](_.rightPool)
  def rattledLens  = GenLens[MentalStats](_.marked)

  protected final case class FluentWithWits(w: Int) {
    infix def withPresence(p: Int): MentalStats =
      StatGroup(
        StatPool(DicePool(w), false),
        StatPool(DicePool(p), false),
        false
      )
  }

  infix def withWits(w: Int) = FluentWithWits(w)

  given RW[MentalStats] = opaqueRW[MentalStats, StatGroup](identity, identity)
}

final case class CharacterBaseStats(
    bodyStats: BodyStats,
    mentalStats: MentalStats
) derives ReadWriter
