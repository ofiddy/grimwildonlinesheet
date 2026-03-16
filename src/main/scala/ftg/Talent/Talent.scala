package ftg.Talent

import ftg.Talent.TalentWidgets.TalentWidget

opaque type Markdown = String

final case class Talent(
    name: String,
    description: Markdown,
    widgets: List[TalentWidget]
)
