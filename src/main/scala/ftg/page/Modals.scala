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

object Modals {
  def renderModal(model: Model): Html[Msg] =
    dialog(id := "modal", onClick(CloseModal))(
      model.currentModal
        .map(m =>
          m match
            case t: TalentModal =>
              div(
                cls := "modal-inner",
                onEvent(
                  "click",
                  e => {
                    e.stopPropagation()
                    NoOpMsg
                  }
                )
              )(
                renderTalentModal(model, t)
              )
        )
        .orEmpty
    )

  def renderTalentModal(model: Model, tm: TalentModal): Html[Msg] =
    div(id := "talent-select-modal")(
      h1("SELECT TALENT"),
      div(cls := "horizontal")(
        exitableTextInput(
          `placeholder` := "Search...",
          id            := "talent-modal-search"
        )(s => EditTalentModal(s)),
        select(
          id := "class-change-selector",
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
        allClasses
          .map(x =>
            (
              x.nonCoreTalents.filter(
                _.name.toLowerCase.contains(tm.search.toLowerCase)
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
                  cls := s"sheet-talent modal-talent ${exists}",
                  onClick(
                    SheetMsg(ToggleTalentCommand(t, model.character.talents))
                  )
                )(
                  div(cls := "talent-selector-line")(
                    renderTalentDesc(t),
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
}
