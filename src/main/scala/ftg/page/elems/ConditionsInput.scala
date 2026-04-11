package ftg.page.elems

import ftg.Character.Condition
import tyrian.Html
import ftg.page.Msg
import tyrian.Html._
import ftg.page.Msg.SheetMsg
import ftg.command.AddCondition

object ConditionsInput {
  def renderConditions(cs: List[Condition]): Html[Msg] = {
    val addButton = button(onClick(SheetMsg(AddCondition)))(em("Add Condition"))
    div(
      h2("Conditions"),
      ul(cs.map(c => li(c.name.getOrElse("")))),
      addButton
    )
  }
}
