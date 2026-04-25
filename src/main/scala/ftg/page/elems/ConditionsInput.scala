package ftg.page.elems

import ftg.Character.Condition
import ftg.Character.LongTermCondition
import ftg.Character.PermanentCondition
import ftg.Character.ShortTermCondition
import ftg.Character.UrgentCondition
import ftg.DicePool.DicePool
import ftg.command.AddListElemCommand
import ftg.command.CharacterListFactories.NewCondition
import ftg.command.CharacterLoc.ConditionsLoc
import ftg.command.DeleteListElemCommand
import ftg.command.RollAndDropConditionPoolCommand
import ftg.page.Msg
import ftg.page.Msg.SheetMsg
import ftg.page.elems.DicePoolEntry.dicePoolEntry
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.elems.SheetInputs.handleChangeForAtIndex
import ftg.util.Util.asOption
import tyrian.Empty
import tyrian.Html
import tyrian.Html._

object ConditionsInput {
  def renderConditions(cs: List[Condition]): Html[Msg] = {
    val addButton =
      button(
        onClick(SheetMsg(AddListElemCommand(NewCondition, ConditionsLoc)))
      )(em("Add Condition"))
    div(
      h2("Conditions"),
      ul(cs.zipWithIndex.map((c, i) => renderedCondition(c, i))),
      addButton
    )
  }

  private def renderedCondition(c: Condition, i: Int): Html[Msg] = div(
    li(cls := "horizontal")(
      button(onClick(SheetMsg(DeleteListElemCommand(c, i, ConditionsLoc))))(
        "🗑️"
      ),
      exitableTextInput(`value` := c.name.getOrElse(""))(s =>
        handleChangeForAtIndex(ConditionsLoc)(c, c.copy(name = s.asOption), i)
      ),
      select(
        onInput(s =>
          handleChangeForAtIndex(ConditionsLoc)(
            c,
            c.copy(condType = s match {
              case "urgent"    => UrgentCondition(DicePool(4))
              case "short"     => ShortTermCondition
              case "long"      => LongTermCondition
              case "permanent" => PermanentCondition
            }),
            i
          )
        )
      )(
        option(
          `value`    := "urgent",
          `selected` := c.condType.isInstanceOf[UrgentCondition]
        )(
          "Urgent"
        ),
        option(
          `value`    := "short",
          `selected` := c.condType.isInstanceOf[ShortTermCondition.type]
        )(
          "Short"
        ),
        option(
          `value`    := "long",
          `selected` := c.condType.isInstanceOf[LongTermCondition.type]
        )(
          "Long"
        ),
        option(
          `value`    := "permanent",
          `selected` := c.condType.isInstanceOf[PermanentCondition.type]
        )(
          "Permanent"
        )
      ),
      c.condType match {
        case UrgentCondition(pool) =>
          div(
            dicePoolEntry(`value` := pool.diceRemaining.toString)(n =>
              handleChangeForAtIndex(ConditionsLoc)(
                c,
                c.copy(condType = UrgentCondition(DicePool(n))),
                i
              )
            ),
            button(
              onClick(
                SheetMsg(
                  RollAndDropConditionPoolCommand(i, pool)
                )
              )
            )("Roll and Drop")
          )
        case _ => Empty
      }
    )
  )

}
