package ftg.page.talentRenderers

import tyrian.Html
import tyrian.Html._
import ftg.page.Msg
import ftg.page.CharacterHtmlRenderer.createAndFillCheckboxes
import monocle.AppliedLens
import ftg.Talent.TalentADT.Talent
import ftg.page.Msg.SheetMsg
import ftg.command.CharCommand
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.Msg.NoOpMsg
import ftg.Character.Wise
import ftg.Character.Wise._
import monocle.Lens
import ftg.DicePool.DicePool
import ftg.page.elems.DicePoolEntry.dicePoolEntry
import ftg.command.RollLogAndThen

object FluentTalentRenderers {
  case class WidgetBuilding(
      html: Html[Msg],
      widgets: List[(FluentTalentWidget, TalentEditBuilder)],
      footer: Option[(FluentTalentFooter, TalentEditBuilder)]
  ) {
    infix def withWidget(widget: FluentTalentWidget)(using
        teb: TalentEditBuilder
    ) =
      WidgetBuilding(this.html, this.widgets :+ (widget, teb), this.footer)

    infix def withWidgets(widgets: List[FluentTalentWidget])(using
        teb: TalentEditBuilder
    ) = WidgetBuilding(
      this.html,
      this.widgets ++ widgets.map(w => (w, teb)),
      this.footer
    )

    infix def withFooter(ft: FluentTalentFooter)(using teb: TalentEditBuilder) =
      copy(footer = Some(ft, teb))

    def toHtml: Html[Msg] =
      val body = widgets match
        case Nil => html
        case x =>
          div(cls := "talent-with-widgets")(
            html,
            div(cls := "widgets-sidebar")(
              widgets.map((w, teb) => buildFluent(w, teb))
            )
          )
      footer.map((f, teb) => div(body, buildFooter(f, teb))).getOrElse(body)

  }

  given fluentTalent: Conversion[Html[Msg], WidgetBuilding] {
    override def apply(x: Html[Msg]): WidgetBuilding =
      WidgetBuilding(x, Nil, None)
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
  final case class SquareBox[T <: Talent](
      ref: AppliedLens[T, Boolean],
      label: String
  ) extends FluentTalentWidget
  final case class Pool[T <: Talent](
      label: String,
      ref: AppliedLens[T, DicePool]
  ) extends FluentTalentWidget

  def PushBox[T <: Talent](ref: AppliedLens[T, Boolean]) =
    SquareBox(ref, "PUSH")

  sealed trait FluentTalentFooter
  final case class WisesFooter[T <: Talent](
      ref: AppliedLens[T, (Option[Wise], Option[Wise], Option[Wise])]
  ) extends FluentTalentFooter

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
          cls       := "widget-checkbox--story",
          `type`    := "checkbox",
          `checked` := ref.get,
          onClick(SheetMsg(editBuilder(ref.modify(!_))))
        ),
        b(cls := "widget-title")("STORY")
      )

    case SquareBox(ref, label) =>
      div(cls := "horizontal")(
        input(
          cls       := "widget-checkbox--push",
          `type`    := "checkbox",
          `checked` := ref.get,
          onClick(SheetMsg(editBuilder(ref.modify(!_))))
        ),
        b(cls := "widget-title")(label)
      )

    case Pool(label, ref) =>
      div(cls := "vertical")(
        b(cls := "widget-title")(label),
        button(
          cls := "widget-dice-pool-roll",
          onClick(
            SheetMsg(
              RollLogAndThen(
                ref.get,
                roll => editBuilder(ref.replace(DicePool(roll.hits)))
              )
            )
          )
        )("Roll"),
        dicePoolEntry(
          `value` := ref.get.diceRemaining.toString(),
          cls     := "widget-dice-pool-entry"
        )(n =>
          newTalOnChange(editBuilder(ref.replace(DicePool(n))), n, ref.get)
        )
      )

  def buildFooter(
      w: FluentTalentFooter,
      editBuilder: TalentEditBuilder
  ): Html[Msg] = w match
    case WisesFooter(ref) =>
      def wiseCell(
          lens: Lens[TripWise, Option[Wise]]
      ): Html[Msg] = td(cls := "white-table-cell")(
        exitableTextInput(
          `value` := ref.andThen(lens).get.map(_.toString).getOrElse(""),
          cls     := "white-table-entry wise-entry"
        )(s =>
          newTalOnChange(
            editBuilder(
              ref
                .andThen(lens)
                .replace(
                  if s.isEmpty then None else Some(s.wise)
                )
            ),
            s,
            ref.andThen(lens).get.map(_.toString).getOrElse("")
          )
        )
      )

      div(cls := "white-card-table-wrapper wises-footer")(
        table(cls := "white-card-table")(
          tr(
            td(b("WISES")),
            wiseCell(firstWise),
            wiseCell(secondWise),
            wiseCell(thirdWise)
          )
        )
      )

  private def newTalOnChange[T](
      built: CharCommand,
      newVal: T,
      oldVal: T
  ): Msg =
    if oldVal == newVal then NoOpMsg else SheetMsg(built)

}
