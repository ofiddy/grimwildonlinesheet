package ftg.Talent.TalentWidget.TalentComponents

import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent
import ftg.Character.Character as Character

final case class CheckboxesComponent(
    boxes: Character => Int,
    marked: Int
) extends TalentComponent

object FluentCheckboxes {
  protected case class CheckboxWithLimits(
      max: Character => Int
  ) {
    infix def withStart(i: Int): CheckboxesComponent =
      CheckboxesComponent(max, i)
  }

  object Checkbox {
    infix def withMax(f: Character => Int): CheckboxWithLimits =
      CheckboxWithLimits(f)
    infix def withMax(i: Int): CheckboxWithLimits = withMax(_ => i)
  }
}
