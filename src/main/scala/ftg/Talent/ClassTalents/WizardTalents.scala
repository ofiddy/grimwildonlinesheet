package ftg.Talent.ClassTalents

import ftg.Talent.Talent
import ftg.Talent.md
import ftg.Talent.TalentWidgets.TalentWidget
import ftg.Talent.TalentWidget.TalentComponents.FluentCheckboxes.Checkbox

object WizardTalents {
  val spellcraft = Talent(
    "Spellcraft",
    "You have spellcasting ability. You roll Wits to cast and the spell theorem you are casting serves as its touchstone. Each session, you can cast 4 spells and 2 potent spells. You choose which spell theorem to use when you cast. You can learn new spell theorems from studying and experimenting with scrolls. This consumes the scroll. [Growth: 2 levels/1 spell, potent spell, and spell theorem]".md,
    List(
      TalentWidget("Spells", Checkbox withMin 0 withMax 4 withStart 0),
      TalentWidget("Potent Spells", Checkbox withMin 0 withMax 2 withStart 0)
    )
  )
}
