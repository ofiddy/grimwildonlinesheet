package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.MonkTalents.DisciplineDesc
import monocle.AppliedLens

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
      case FlowStateTalent         => acc
      case HealingHandsTalent      => acc
      case LightningReflexesTalent => acc
      case MindOverMatterTalent    => acc
      case t: PrimordialForcesTalent =>
        renderPrimordialForces(acc, t.focus(_.elem), t.focus(_.charged))
      case t: PrimordialForcesIITalent =>
        renderPrimordialForces(acc, t.focus(_.elem), t.focus(_.charged))
      case t: TetherTalent =>
        acc withWidgets List(
          SquareBox(t.focus(_.tether), "TETHER"),
          PushBox(t.focus(_.push))
        )

  }

  private def renderPrimordialForces[T <: Talent](
      acc: Html[Msg],
      elem: AppliedLens[T, Option[String]],
      charged: AppliedLens[T, Boolean]
  )(using
      TalentEditBuilder
  ) = {
    val elems = List("AIR", "EARTH", "FIRE", "WATER")
    acc withWidgets List(
      SquareBox(charged, "CHARGED"),
      Selectable(elem, elems, "Elem")
    )
  }

}
