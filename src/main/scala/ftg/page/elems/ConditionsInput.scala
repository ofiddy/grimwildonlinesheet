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
        cls := "add-condition",
        onClick(SheetMsg(AddListElemCommand(NewCondition, ConditionsLoc)))
      )("+")
    div(id := "conditions-section")(
      h2("CONDITIONS"),
      div(
        cs.zipWithIndex.map((c, i) => renderedCondition(c, i))
      ),
      addButton
    )
  }

  private def renderedCondition(c: Condition, i: Int): Html[Msg] =
    div(cls := "condition-elem")(
      div(cls := "horizontal condition-header")(
        select(
          cls := "condition-duration",
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
        button(
          cls := "condition-delete",
          onClick(SheetMsg(DeleteListElemCommand(c, i, ConditionsLoc)))
        )(
          "X"
        )
      ),
      exitableTextInput(
        cls           := "condition-input",
        `value`       := c.name.getOrElse(""),
        `placeholder` := "Condition Text"
      )(s =>
        handleChangeForAtIndex(ConditionsLoc)(c, c.copy(name = s.asOption), i)
      ),
      c.condType match {
        case UrgentCondition(pool) =>
          div(cls := "urgent-input-section")(
            button(
              cls := "urgent-roll-button",
              onClick(
                SheetMsg(
                  RollAndDropConditionPoolCommand(i, pool)
                )
              )
            )("ROLL"),
            dicePoolEntry(
              cls     := "urgent-pool-entry",
              `value` := pool.diceRemaining.toString
            )(n =>
              handleChangeForAtIndex(ConditionsLoc)(
                c,
                c.copy(condType = UrgentCondition(DicePool(n))),
                i
              )
            )
          )
        case _ => Empty
      }
    )

}
