package ftg.page.talentRenderers

import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import ftg.page.CharacterHtmlRenderer.createAndFillCheckboxes

object FluentTalentRenderers {
  case class WidgetBuilding(
      html: Html[Msg],
      widgets: List[FluentTalentWidget]
  ) {
    infix def withWidget(widget: FluentTalentWidget) =
      WidgetBuilding(this.html, widget :: this.widgets)

    def toHtml: Html[Msg] =
      widgets match
        case Nil => html
        case x =>
          div(cls := "talent-with-widgets")(
            html,
            div(cls := "widgets-sidebar")(widgets.map(buildFluent))
          )

  }

  given fluentTalent: Conversion[Html[Msg], WidgetBuilding] {
    override def apply(x: Html[Msg]): WidgetBuilding = WidgetBuilding(x, Nil)
  }
  given fluentTalentBuilder: Conversion[WidgetBuilding, Html[Msg]] {
    override def apply(x: WidgetBuilding): Html[Msg] = x.toHtml
  }

  sealed trait FluentTalentWidget
  final case class MultiCheckbox(label: String, current: Int, max: Int)
      extends FluentTalentWidget

  def buildFluent(w: FluentTalentWidget): Html[Msg] = w match
    case MultiCheckbox(label, current, max) =>
      div(cls := "widget-multicheckbox")(
        b(cls := "widget-title")(label),
        div(cls := "widget-boxes")(
          button(
            disabled(current <= 0),
            cls := "story-crementer"
          )("–"),
          div(cls := "widget-boxes-inner")(
            createAndFillCheckboxes(
              current,
              max - current,
              "widget-checkbox"
            )
          ),
          button(
            disabled(current >= max),
            cls := "story-crementer"
          )(
            "+"
          )
        )
      )

}
