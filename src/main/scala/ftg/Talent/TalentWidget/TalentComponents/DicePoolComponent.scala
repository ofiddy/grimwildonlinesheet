package ftg.Talent.TalentWidget.TalentComponents

import ftg.Character.Character as Character
import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent
import ftg.DicePool.DicePool

final case class DicePoolComponent(
    currentDice: DicePool,
    maxDice: Character => DicePool
) extends TalentComponent
