package ftg.page

import tyrian.Html._
import tyrian._
import tyrian.syntax._
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.CloseModal
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.Msg.EditTalentModal
import ftg.page.talentRenderers.renderTalentDesc
import ftg.page.Msg.SheetMsg
import ftg.command.ToggleTalentCommand
import ftg.Talent.ClassTalents.TalentsRefs.allClasses
import ftg.command.ChangeClassCommand
import ftg.page.Msg.EditTalentModalFilter

object Modals {
  def renderModal(model: Model): Html[Msg] =
    dialog(id := "modal", onClick(CloseModal))(
      model.currentModal
        .map(m =>
          div(
            cls := "modal-inner",
            onEvent(
              "click",
              e => {
                e.stopPropagation()
                NoOpMsg
              }
            )
          )(m match {
            case t: TalentModal   => renderTalentModal(model, t)
            case d: DiceRollModal => renderDiceRollModal(model, d)
          })
        )
        .orEmpty
    )

  def renderTalentModal(model: Model, tm: TalentModal): Html[Msg] =
    div(id := "talent-select-modal")(
      h1("SELECT TALENT"),
      div(cls := "horizontal talent-select-interact-header")(
        exitableTextInput(
          `placeholder` := "Search...",
          id            := "talent-modal-search"
        )(s => EditTalentModal(s)),
        span(b("FILTER:")),
        select(
          cls := "talent-modal-selector",
          id  := "class-filter-selector",
          onInput(s =>
            if model.classFilter.map(_.name == s).getOrElse(s == "None") then
              NoOpMsg
            else
              EditTalentModalFilter(
                allClasses.find(_.name == s).map(Some(_)).getOrElse(None)
              )
          )
        )(
          option(
            `value`    := "NONE",
            `selected` := model.classFilter == None
          )("NONE") :: allClasses.map(c =>
            option(
              `value`    := c.name,
              `selected` := model.classFilter == Some(c)
            )(c.name.toUpperCase)
          )
        ),
        span(b("CLASS:")),
        select(
          cls := "talent-modal-selector",
          id  := "class-change-selector",
          onInput(s =>
            if s == model.character.charClass.name then NoOpMsg
            else
              SheetMsg(
                ChangeClassCommand(
                  allClasses.find(_.name == s).get, // im sorry
                  (model.character.charClass, model.character.coreTalent)
                )
              )
          )
        )(
          allClasses.map(c =>
            option(
              `value`    := c.name,
              `selected` := model.character.charClass == c
            )(c.name.toUpperCase)
          )
        )
      ),
      div(id := "talent-modal-scrolling-section")(
        model.classFilter
          .map(List(_))
          .getOrElse(allClasses)
          .map(x =>
            (
              x.nonCoreTalents.filter(t =>
                t.name.toLowerCase.contains(tm.search.toLowerCase)
              ),
              x.name
            )
          )
          .flatMap((ts, name) =>
            ts.flatMap(t => {
              val exists =
                if model.character.talents.exists(_.talentDesc == t) then
                  "modal-talent-exists"
                else ""
              List(
                button(
                  cls := s"sheet-talent modal-talent",
                  onClick(
                    SheetMsg(ToggleTalentCommand(t, model.character.talents))
                  )
                )(
                  div(cls := "talent-selector-line")(
                    renderTalentDesc(t, exists),
                    p(cls := "talent-selector-class-label")(name.toUpperCase())
                  )
                ),
                hr
              )
            })
          )
          .dropRight(1)
      )
    )

  def renderDiceRollModal(model: Model, dm: DiceRollModal): Html[Msg] = {
    val roll    = dm.roll
    val perfect = roll(6)
    val messy   = (roll(5), roll(4))
    val grim    = (roll(3), roll(2), roll(1))
    val label = (perfect, messy, grim) match {
      case (0, (0, 0), (0, 0, 0)) => span("No Dice!")
      case (0, (0, 0), _) => span(cls := "grim-label roll-label")("GRIM")
      case (0, _, _)      => span(cls := "messy-label roll-label")("MESSY")
      case (1, _, _)      => span(cls := "perfect-label roll-label")("PERFECT")
      case (_, _, _)      => span(cls := "perfect-label roll-label")("CRITICAL")
    }

    div(id := "dice-roll-modal")(
      h1("ROLL RESULT"),
      h2(label),
      p(
        Range(0, perfect)
          .flatMap(_ =>
            List(span(cls := "perfect-dice roll-dice")("6"), span(" "))
          )
          .dropRight(1)
          .toList
      ),
      p(
        (Range(0, messy._1)
          .flatMap(_ =>
            List(span(cls := "messy-dice roll-dice")("5"), span(" "))
          )
          ++
            Range(0, messy._2)
              .flatMap(_ =>
                List(span(cls := "messy-dice roll-dice")("4"), span(" "))
              ))
          .dropRight(1)
          .toList
      ),
      p(
        (Range(0, grim._1)
          .flatMap(_ =>
            List(span(cls := "grim-dice roll-dice")("3"), span(" "))
          )
          ++
            Range(0, grim._2)
              .flatMap(_ =>
                List(span(cls := "grim-dice roll-dice")("2"), span(" "))
              )
            ++
            Range(0, grim._3)
              .flatMap(_ =>
                List(span(cls := "grim-dice roll-dice")("1"), span(" "))
              ))
          .dropRight(1)
          .toList
      )
    )
  }

}
