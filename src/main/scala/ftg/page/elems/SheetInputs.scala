package ftg.page.elems

import ftg.Character.CharacterName
import ftg.Character.CharacterName._
import ftg.Character.DistinctiveFeatures
import ftg.Character.DistinctiveFeatures.intoDistinctiveFeatures
import ftg.Character.PlayerName
import ftg.Character.PlayerName._
import ftg.Character.{Character => Character}
import ftg.command.CharacterLoc.StatLocs._
import ftg.command.CharacterLoc._
import ftg.command._
import ftg.page.Msg
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.SheetMsg
import ftg.page.elems.DicePoolEntry.dicePoolEntry
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.elems.ExitableTextArea.exitableTextArea
import tyrian.Html._

object SheetInputs {
  def handleChangeFor[T](
      find: Loc[T]
  )(old: T, newVal: T): Msg = if newVal == old then NoOpMsg
  else SheetMsg(ValueEditCommand(newVal, old, find))

  def handleChangeForAtIndex[T](
      find: Loc[List[T]]
  )(old: T, newVal: T, index: Int): Msg = if newVal == old then NoOpMsg
  else SheetMsg(ModifyListElemCommand(newVal, old, index, find))

  def charNameInput(name: CharacterName) =
    exitableTextInput(
      cls     := "name-entry-input",
      `value` := name.label
    )((s: String) => handleChangeFor(CharacterNameLoc)(name, s.intoCharName))

  def playerNameInput(name: PlayerName) = exitableTextInput(
    cls     := "name-entry-input",
    `value` := name.label
  )((s: String) => handleChangeFor(PlayerNameLoc)(name, s.intoPlayerName))

  def distinctiveFeaturesInput(dfs: DistinctiveFeatures) = exitableTextArea(
    id := "distinctive-features"
  )((s: String) =>
    handleChangeFor(DistinctiveFeaturesLoc)(dfs, s.intoDistinctiveFeatures)
  )(dfs.label)

  def statPoolInput(loc: StatLoc)(char: Character) =
    dicePoolEntry(
      `value` := loc(char).get.dice.diceRemaining.toString,
      cls     := "dice-pool-entry"
    )(i =>
      val oldRemaining = loc(char).get.dice.diceRemaining
      if i != oldRemaining then
        SheetMsg(
          EditStatPoolSizeCommand(i, loc(char).get.dice.diceRemaining, loc)
        )
      else NoOpMsg
    )

  def markedInput(
      loc: StatLoc
  )(char: Character) = input(
    `type`    := "checkbox",
    `checked` := loc(char).get.isMarked,
    onClick(SheetMsg(TogglePoolMarkedCommand(loc))),
    cls := "stat-mark-box"
  )

  def charHarmInput(
      loc: Loc[Boolean]
  )(char: Character) = td(`colspan` := 2)(
    table(cls := "harm-section")(
      tr(
        td(
          input(
            `type`    := "checkbox",
            `checked` := loc(char).get,
            onClick(SheetMsg(ToggleCommand(loc))),
            cls := "stat-mark-box"
          )
        )
      ),
      tr(cls := "harm-section-label")(loc.toString.toUpperCase)
    )
  )

}
