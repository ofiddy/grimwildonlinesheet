package ftg.Character

import ftg.DicePool.DicePool

sealed trait ConditionType

case object ShortTermCondition                   extends ConditionType
case object LongTermCondition                    extends ConditionType
case object PermanentCondition                   extends ConditionType
final case class UrgentCondition(pool: DicePool) extends ConditionType

final case class Condition(name: Option[String], condType: ConditionType)
