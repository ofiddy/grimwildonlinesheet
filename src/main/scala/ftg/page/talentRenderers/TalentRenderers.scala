package ftg.page

import tyrian.Html
import tyrian.Html._
import ftg.Talent.TalentADT.Talent
import ftg.page.talentRenderers.BardTalentRenderer.bardTalentRender
import ftg.Talent.TalentADT.BardTalent
import ftg.Character.{Character => Character}
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentImpl
import ftg.JsLib.Converter

package object talentRenderers {
  def renderTalent(t: Talent & TalentImpl, c: Character)(using
      TalentEditBuilder
  ): Html[Msg] = {
    val base = renderTalentDesc(t.talentDesc)
    val afterProcess = t match {
      case t: BardTalent => bardTalentRender(t, c, base)
    }
    div(cls := "sheet-talent")(afterProcess)
  }

  def renderTalentDesc(t: TalentDescriptor): Html[Msg] =
    span(cls := "talent-desc")(
      span(cls := "talent-name")(t.name),
      span(cls := "talent-body")(
        span(": "),
        raw("span")(
          new Converter().makeHtml(t.desc.toString()).drop(3).dropRight(4)
        )
      )
    )
}
