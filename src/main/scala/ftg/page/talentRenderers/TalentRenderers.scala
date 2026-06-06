package ftg.page

import tyrian.Html
import tyrian.Html._
import ftg.page.talentRenderers.BardTalentRenderer.bardTalentRender
import ftg.Talent.TalentADT._
import ftg.Character.{Character => Character}
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentImpl
import ftg.JsLib.Converter
import ftg.page.talentRenderers.ArtificerTalentRender.artificerTalentRender
import ftg.Talent.Markdown
import ftg.page.talentRenderers.TalentRenderHelpers.ConvertMd
import ftg.page.talentRenderers.BerserkerTalentRenderer.berserkerTalentRender
import ftg.page.talentRenderers.ClericTalentRenderer.clericTalentRenderer
import ftg.page.talentRenderers.DruidTalentRenderer.druidTalentRenderer
import ftg.page.talentRenderers.FighterTalentRenderer.fighterTalentRenderer
import ftg.page.talentRenderers.MonkTalentRenderer.monkTalentRenderer

package object talentRenderers {
  object TalentRenderHelpers {
    lazy val showdownConverter = {
      val converter = new Converter()
      converter.setOption("simpleLineBreaks", "true")
      converter
    }

    def ConvertMd(md: Markdown) = showdownConverter.makeHtml(md.toString())
  }

  def renderTalent(t: Talent & TalentImpl, c: Character)(using
      TalentEditBuilder
  ): Html[Msg] = {
    val base = renderTalentDesc(t.talentDesc)
    val afterProcess = t match {
      case t: ArtificerTalent => artificerTalentRender(t, c, base)
      case t: BardTalent      => bardTalentRender(t, c, base)
      case t: BerserkerTalent => berserkerTalentRender(t, c, base)
      case t: ClericTalent    => clericTalentRenderer(t, c, base)
      case t: DruidTalent     => druidTalentRenderer(t, c, base)
      case t: FighterTalent   => fighterTalentRenderer(t, c, base)
      case t: MonkTalent      => monkTalentRenderer(t, c, base)
    }
    div(cls := "sheet-talent")(afterProcess)
  }

  def renderTalentDesc(t: TalentDescriptor): Html[Msg] =
    span(cls := "talent-desc")(
      span(cls := "talent-name")(t.name),
      span(cls := "talent-body")(
        span(": "),
        raw("span")(
          ConvertMd(t.desc).drop(3).dropRight(4)
        )
      )
    )
}
