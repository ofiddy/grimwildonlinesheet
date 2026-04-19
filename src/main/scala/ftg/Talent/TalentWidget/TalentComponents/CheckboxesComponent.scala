package ftg.Talent.TalentWidget.TalentComponents

import ftg.Character.{Character => Character}
import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent

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
