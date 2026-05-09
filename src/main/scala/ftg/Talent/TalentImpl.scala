package ftg.Talent

import ftg.Character.{Character => Character}
import ftg.Talent.TalentADT.Talent

opaque type Markdown = String

extension (s: String) {
  def md: Markdown = s
}

extension (m: Markdown) {
  def toRawString: String = m
}

trait TalentDescriptor {
  def name: String
  def desc: Markdown
  def apply(c: Character): Talent
}

trait TalentImpl {
  def talentDesc: TalentDescriptor
}
