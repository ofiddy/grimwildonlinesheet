package ftg.Talent.TalentComponent

import ftg.Character.{Character => Character}
import ftg.DicePool.DicePool
import ftg.Talent.TalentComponents.TalentComponent

final case class DicePoolComponent(
    currentDice: DicePool,
    maxDice: Character => DicePool
) extends TalentComponent

object FluentDicePoolComponent {
  extension (i: Int) {
    infix def d(x: DicePool.type): DicePoolComponent =
      DicePoolComponent(DicePool(i), _ => DicePool(i))
  }
}
