package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.MonkTalents.DisciplineDesc

object MonkTalentRenderer {
  def monkTalentRenderer(
      t: MonkTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    t match
      case t: DisciplineTalent => {
        val max = DisciplineDesc(c)
        acc withWidgets List(
          MultiCheckbox("FLOW", t.focus(_.flow), max.flow),
          MultiCheckbox("INTERRUPT", t.focus(_.interrupt), max.interrupt)
        )
      }
  }

}
