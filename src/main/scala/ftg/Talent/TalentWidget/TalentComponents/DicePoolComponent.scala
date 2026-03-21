package ftg.Talent.TalentWidget.TalentComponents

import ftg.Character.Character as Character
import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent
import ftg.DicePool.DicePool

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
