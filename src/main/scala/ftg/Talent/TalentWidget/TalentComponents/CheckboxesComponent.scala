package ftg.Talent.TalentWidget.TalentComponents

import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent
import ftg.Character.Character as Character

final case class CheckboxesComponent(
    min: Character => Int,
    max: Character => Int,
    marked: Int
) extends TalentComponent
