package ftg.page.talentRenderers

import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import ftg.page.CharacterHtmlRenderer.createAndFillCheckboxes
import monocle.AppliedLens
import ftg.Talent.TalentADT.Talent
import ftg.page.Msg.SheetMsg
import ftg.command.CharCommand

object FluentTalentRenderers {
  case class WidgetBuilding(
      html: Html[Msg],
      widgets: List[(FluentTalentWidget, TalentEditBuilder)]
  ) {
    infix def withWidget(widget: FluentTalentWidget)(using
        teb: TalentEditBuilder
    ) =
      WidgetBuilding(this.html, this.widgets :+ (widget, teb))

    def toHtml: Html[Msg] =
      widgets match
        case Nil => html
        case x =>
          div(cls := "talent-with-widgets")(
            html,
            div(cls := "widgets-sidebar")(
              widgets.map((w, teb) => buildFluent(w, teb))
            )
          )

  }

  given fluentTalent: Conversion[Html[Msg], WidgetBuilding] {
    override def apply(x: Html[Msg]): WidgetBuilding = WidgetBuilding(x, Nil)
  }
  given fluentTalentBuilder: Conversion[WidgetBuilding, Html[Msg]] {
    override def apply(x: WidgetBuilding): Html[Msg] = x.toHtml
  }

  sealed trait FluentTalentWidget
  final case class MultiCheckbox[T <: Talent](
      label: String,
      ref: AppliedLens[T, Int],
      max: Int
  ) extends FluentTalentWidget
  final case class StoryBox[T <: Talent](ref: AppliedLens[T, Boolean])
      extends FluentTalentWidget
  final case class PushBox[T <: Talent](ref: AppliedLens[T, Boolean])
      extends FluentTalentWidget

  type TalentEditBuilder = (newTal: Talent) => CharCommand

  def buildFluent(
      w: FluentTalentWidget,
      editBuilder: TalentEditBuilder
  ): Html[Msg] = w match
    case MultiCheckbox(label, ref, max) =>
      div(cls := "widget-multicheckbox")(
        b(cls := "widget-title")(label),
        div(cls := "widget-boxes")(
          button(
            disabled(ref.get <= 0),
            cls := "story-crementer",
            onClick(
              SheetMsg(
                editBuilder(
                  ref.modify(_ - 1)
                )
              )
            )
          )("–"),
          div(cls := "widget-boxes-inner")(
            createAndFillCheckboxes(
              ref.get,
              max - ref.get,
              "widget-multicheckbox-checkbox"
            )
          ),
          button(
            disabled(ref.get >= max),
            cls := "story-crementer",
            onClick(
              SheetMsg(
                editBuilder(
                  ref.modify(_ + 1)
                )
              )
            )
          )(
            "+"
          )
        )
      )

    case StoryBox(ref) =>
      div(cls := "horizontal")(
        input(
          cls       := "widget-checkbox",
          `type`    := "checkbox",
          `checked` := ref.get,
          onClick(SheetMsg(editBuilder(ref.modify(!_))))
        ),
        b(cls := "widget-title")("STORY")
      )

    case PushBox(ref) =>
      div(cls := "horizontal")(
        input(
          cls       := "widget-checkbox--push",
          `type`    := "checkbox",
          `checked` := ref.get,
          onClick(SheetMsg(editBuilder(ref.modify(!_))))
        ),
        b(cls := "widget-title")("PUSH")
      )

}
