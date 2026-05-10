package ftg.page

import tyrian.Html
import tyrian.Html._
import ftg.Talent.TalentADT.Talent

package object talentRenderers {
  def renderTalent(t: Talent): Html[Msg] = {
    val base = span(cls := "talent-desc")(
      span(cls := "talent-name")(t.name),
      span(cls := "talent-body")(span(": "), span(t.desc.toString()))
    )
    div(cls := "sheet-talent")(base)
  }
}
