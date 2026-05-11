package ftg.page

import tyrian.Html._
import tyrian._
import tyrian.syntax._
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.CloseModal
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.Msg.EditTalentModal
import ftg.Talent.ClassTalents.TalentsRefs.allBaseTalents
import ftg.page.talentRenderers.renderTalentDesc
import ftg.page.Msg.SheetMsg
import ftg.command.ToggleTalentCommand

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
      exitableTextInput(
        `placeholder` := "Search...",
        id            := "talent-modal-search"
      )(s => EditTalentModal(s)),
      div(id := "talent-modal-scrolling-section")(
        allBaseTalents
          .filter(_.name.toLowerCase.contains(tm.search.toLowerCase))
          .flatMap(t =>
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
                renderTalentDesc(t)
              ),
              hr
            )
          )
          .dropRight(1)
      )
    )
}
