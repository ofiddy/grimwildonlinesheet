package ftg.Talent

import ftg.Talent.TalentWidgets.TalentWidget

opaque type Markdown = String

extension (s: String) {
  def md: Markdown = s
}

final case class Talent(
    name: String,
    description: Markdown,
    widgets: List[TalentWidget]
)
