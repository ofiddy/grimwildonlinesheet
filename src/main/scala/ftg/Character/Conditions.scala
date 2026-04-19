package ftg.Character

import ftg.DicePool.DicePool
import upickle.default.ReadWriter

sealed trait ConditionType derives ReadWriter

case object ShortTermCondition                   extends ConditionType
case object LongTermCondition                    extends ConditionType
case object PermanentCondition                   extends ConditionType
final case class UrgentCondition(pool: DicePool) extends ConditionType

final case class Condition(name: Option[String], condType: ConditionType)
    derives ReadWriter
