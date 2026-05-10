package ftg.page

import tyrian.Html
import tyrian.Html._
import ftg.Talent.TalentADT.Talent
import ftg.page.talentRenderers.BardTalentRenderer.bardTalentRender
import ftg.Talent.TalentADT.BardTalent
import ftg.Character.{Character => Character}

package object talentRenderers {
  def renderTalent(t: Talent, c: Character): Html[Msg] = {
    val base = span(cls := "talent-desc")(
      span(cls := "talent-name")(t.name),
      span(cls := "talent-body")(span(": "), span(t.desc.toString()))
    )
    val afterProcess = t match {
      case t: BardTalent => bardTalentRender(t, c, base)
    }
    div(cls := "sheet-talent")(afterProcess)
  }
}
