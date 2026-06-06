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
import ftg.Talent.TalentADT.MarkableSelectable
import ftg.util.Util.firstStringInTrip
import ftg.util.Util.secondStringInTrip
import ftg.util.Util.thirdStringInTrip
import ftg.Talent.TalentADT.ChannelDivinityTalent
import ftg.Talent.TalentADT.LabelledPool
import monocle.syntax.all.focus
import tyrian.Empty

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
  final case class MarkableSelection[T <: Talent](
      ref: AppliedLens[T, MarkableSelectable],
      options: List[String]
  ) extends FluentTalentWidget
  final case class Selectable[T <: Talent](
      ref: AppliedLens[T, Option[String]],
      options: List[String],
      label: String
  ) extends FluentTalentWidget
  final case class TextEntry[T <: Talent](
      label: String,
      ref: AppliedLens[T, Option[String]]
  ) extends FluentTalentWidget

  def PushBox[T <: Talent](ref: AppliedLens[T, Boolean]) =
    SquareBox(ref, "PUSH")

  sealed trait FluentTalentFooter
  final case class WisesFooter[T <: Talent](
      ref: AppliedLens[T, (Option[Wise], Option[Wise], Option[Wise])]
  ) extends FluentTalentFooter
  final case class WarsongsFooter[T <: Talent](
      ref: AppliedLens[T, (Option[String], Option[String], Option[String])]
  ) extends FluentTalentFooter
  final case class ChannelDivinityFooter(
      tal: ChannelDivinityTalent,
      maxUpgrades: Int
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
    case MarkableSelection(ref, options) =>
      div(cls := "horizontal")(
        input(
          cls       := "widget-checkbox--push",
          `type`    := "checkbox",
          `checked` := ref.get.marked,
          onClick(
            SheetMsg(editBuilder(ref.modify(x => x.copy(marked = !x.marked))))
          )
        ),
        div(cls := "vertical")(
          select(
            cls     := "widget-selectable",
            `value` := ref.get.feature.getOrElse("none"),
            onChange(s => {
              val selected = if s == "none" then None else Some(s)
              newTalOnChange(
                editBuilder(ref.replace(ref.get.copy(feature = selected))),
                selected,
                ref.get
              )
            })
          )(
            option(`value` := "none")("") :: options.map(o =>
              option(`value` := o)(o)
            )
          )
        )
      )

    case Selectable(ref, options, label) =>
      div(cls := "vertical")(
        b(cls := "widget-title")(label),
        select(
          cls     := "widget-selectable",
          `value` := ref.get.getOrElse("none"),
          onChange(s => {
            val selected = if s == "none" then None else Some(s)
            newTalOnChange(
              editBuilder(ref.replace(selected)),
              selected,
              ref.get
            )
          })
        )(
          option(`value` := "none")("") :: options.map(o =>
            option(`value` := o)(o)
          )
        )
      )

    case TextEntry(label, ref) =>
      div(cls := "vertical")(
        p(cls := "widget-title")(b(cls := "widget-title")(label)),
        exitableTextInput(
          `value` := ref.get.getOrElse(""),
          cls     := "talent-widget-text-input"
        )(s =>
          newTalOnChange(
            editBuilder(
              ref.replace(if s.isEmpty then None else Some(s))
            ),
            s,
            ref.get.getOrElse("")
          )
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

    case WarsongsFooter(ref) => {
      def compositionBox(
          lens: Lens[(Option[String], Option[String], Option[String]), Option[
            String
          ]]
      ): Html[Msg] = exitableTextInput(
        `value` := ref.andThen(lens).get.getOrElse(""),
        cls     := "footer-input"
      )(s =>
        newTalOnChange(
          editBuilder(
            ref.andThen(lens).replace(if s.isEmpty then None else Some(s))
          ),
          s,
          ref.andThen(lens).get.getOrElse("")
        )
      )

      div(cls := "horizontal footer-compositions")(
        b("COMPOSITIONS"),
        compositionBox(firstStringInTrip),
        compositionBox(secondStringInTrip),
        compositionBox(thirdStringInTrip)
      )
    }

    case ChannelDivinityFooter(tal, max) => {
      val total        = tal.upgrades._1 + tal.upgrades._2 + tal.upgrades._3
      val canIncrement = total < max
      val showUpgrades = max > 0
      div(cls := "horizontal channel-divinity-footer")(
        div(
          clericBox(tal.focus(_.pools._1), editBuilder),
          if showUpgrades then
            tinyCrementer(tal.focus(_.upgrades._1), editBuilder, canIncrement)
          else Empty
        ),
        div(
          clericBox(tal.focus(_.pools._2), editBuilder),
          if showUpgrades then
            tinyCrementer(tal.focus(_.upgrades._2), editBuilder, canIncrement)
          else Empty
        ),
        div(
          clericBox(tal.focus(_.pools._3), editBuilder),
          if showUpgrades then
            tinyCrementer(tal.focus(_.upgrades._3), editBuilder, canIncrement)
          else Empty
        )
      )
    }

  private def newTalOnChange[A](
      built: CharCommand,
      newVal: A,
      oldVal: A
  ): Msg =
    if oldVal == newVal then NoOpMsg else SheetMsg(built)

  private def clericBox[T <: Talent](
      ref: AppliedLens[T, LabelledPool],
      teb: TalentEditBuilder
  ): Html[Msg] = div(
    `cls` := "cleric-box"
  )(
    dicePoolEntry(
      `value` := ref.get.pool.diceRemaining.toString(),
      cls     := "widget-dice-pool-entry--cleric"
    )(n =>
      newTalOnChange(teb(ref.modify(_.copy(pool = DicePool(n)))), n, ref.get)
    ),
    div(
      exitableTextInput(
        `value` := ref.get.label.getOrElse(""),
        cls     := "channel-divinity-input"
      )(s =>
        newTalOnChange(
          teb(
            ref.modify(_.copy(label = if s.isEmpty then None else Some(s)))
          ),
          s,
          ref.get
        )
      ),
      button(
        cls := "widget-dice-pool-roll--cleric",
        onClick(
          SheetMsg(
            RollLogAndThen(
              ref.get.pool,
              roll => teb(ref.modify(_.copy(pool = DicePool(roll.hits))))
            )
          )
        )
      )("Roll")
    )
  )

  private def tinyCrementer[T <: Talent](
      ref: AppliedLens[T, Int],
      teb: TalentEditBuilder,
      canIncrement: Boolean
  ): Html[Msg] = div(cls := "horizontal tiny-crementer")(
    button(
      disabled(ref.get <= 0),
      cls := "story-crementer",
      onClick(
        SheetMsg(
          teb(
            ref.modify(_ - 1)
          )
        )
      )
    )("–"),
    p(b(ref.get.toString)),
    button(
      disabled(!canIncrement || ref.get >= 2),
      cls := "story-crementer",
      onClick(
        SheetMsg(
          teb(
            ref.modify(_ + 1)
          )
        )
      )
    )(
      "+"
    )
  )

}
