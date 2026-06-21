package ftg.page

import ftg.DicePool.RollResult

sealed trait Modal
final case class TalentModal(search: String)     extends Modal
final case class DiceRollModal(roll: RollResult) extends Modal
