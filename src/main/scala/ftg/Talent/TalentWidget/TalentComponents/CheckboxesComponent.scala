package ftg.Talent.TalentWidget.TalentComponents

import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent
import ftg.Character.Character as Character

final case class CheckboxesComponent(
    min: Character => Int,
    max: Character => Int,
    marked: Int
) extends TalentComponent

object FluentCheckboxes {
  protected case class CheckboxWithMin(min: Character => Int) {
    infix def withMax(f: Character => Int): CheckboxWithLimits =
      CheckboxWithLimits(min, f)
    infix def withMax(i: Int): CheckboxWithLimits = withMax(_ => i)
  }

  protected case class CheckboxWithLimits(
      min: Character => Int,
      max: Character => Int
  ) {
    infix def withStart(i: Int): CheckboxesComponent =
      CheckboxesComponent(min, max, i)
  }

  object Checkbox {
    infix def withMin(f: Character => Int): CheckboxWithMin = CheckboxWithMin(f)
    infix def withMin(i: Int): CheckboxWithMin = CheckboxWithMin(_ => i)
  }
}
