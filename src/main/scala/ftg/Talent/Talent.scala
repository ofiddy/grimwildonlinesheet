package ftg.Talent

opaque type Markdown = String

extension (s: String) {
  def md: Markdown = s
}

extension (m: Markdown) {
  def toRawString: String = m
}

trait Talent {
  def freshFromChar(c: ftg.Character.Character): Talent
  def name: String
  def desc: Markdown
}
