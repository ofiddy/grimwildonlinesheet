package ftg.Character

import ftg.DicePool.DicePool

sealed trait Condition

final case class ShortTermCondition(name: String)              extends Condition
final case class LongTermCondition(name: String)               extends Condition
final case class PermanentCondition(name: String)              extends Condition
final case class UrgentCondition(name: String, pool: DicePool) extends Condition
