package ftg.Talent

import ftg.Character.{Character => Character}

opaque type Markdown = String

extension (s: String) {
  def md: Markdown = s
}

extension (m: Markdown) {
  def toRawString: String = m
}

trait TalentDescriptor[+T <: Talent] {
  def name: String
  def desc: Markdown
  def apply(c: Character): T
}

trait Talent {
  def talentDesc: TalentDescriptor[Talent]
}

trait StatelessTalent extends Talent with TalentDescriptor[StatelessTalent] {
  def talentDesc: TalentDescriptor[Talent] = this
}
