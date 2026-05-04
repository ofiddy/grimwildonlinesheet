package ftg.Talent

import ftg.Talent.TalentComponents.TalentComponent

opaque type Markdown = String

extension (s: String) {
  def md: Markdown = s
}

extension (m: Markdown) {
  def toRawString: String = m
}

final case class Talent(
    name: String,
    description: Markdown,
    widgets: List[TalentComponent]
)
