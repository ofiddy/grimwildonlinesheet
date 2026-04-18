package ftg.page.elems

import ftg.Character.Condition
import tyrian.Html
import ftg.page.Msg
import tyrian.Html._
import ftg.page.Msg.SheetMsg
import ftg.command.AddConditionCommand
import tyrian.CSS
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.util.Util.asOption
import ftg.command.ModifyConditionCommand
import ftg.Character.ShortTermCondition
import ftg.Character.LongTermCondition
import ftg.Character.PermanentCondition
import ftg.Character.UrgentCondition
import ftg.DicePool.DicePool
import tyrian.Empty
import ftg.page.elems.DicePoolEntry.dicePoolEntry
import ftg.command.RollAndDropConditionPoolCommand
import ftg.command.DeleteConditionCommand

object ConditionsInput {
  def renderConditions(cs: List[Condition]): Html[Msg] = {
    val addButton =
      button(onClick(SheetMsg(AddConditionCommand)))(em("Add Condition"))
    div(
      h2("Conditions"),
      ul(cs.zipWithIndex.map((c, i) => renderedCondition(c, i))),
      addButton
    )
  }

  private def renderedCondition(c: Condition, i: Int): Html[Msg] = div(
    li(styles(CSS.`display`("flex")))(
      button(onClick(SheetMsg(DeleteConditionCommand(c, i))))("🗑️"),
      exitableTextInput(`value` := c.name.getOrElse(""))(s =>
        SheetMsg(ModifyConditionCommand(c.copy(name = s.asOption), c, i))
      ),
      select(
        onInput(s =>
          SheetMsg(
            ModifyConditionCommand(
              c.copy(condType = s match {
                case "urgent"    => UrgentCondition(DicePool(4))
                case "short"     => ShortTermCondition
                case "long"      => LongTermCondition
                case "permanent" => PermanentCondition
              }),
              c,
              i
            )
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
              SheetMsg(
                ModifyConditionCommand(
                  c.copy(condType = UrgentCondition(DicePool(n))),
                  c,
                  i
                )
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
