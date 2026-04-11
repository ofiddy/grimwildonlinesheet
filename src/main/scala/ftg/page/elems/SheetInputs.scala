package ftg.page.elems

import ftg.Character.CharacterName
import ftg.Character.CharacterName._
import ftg.Character.DistinctiveFeatures
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
import tyrian.CSS
import tyrian.Html._
import ftg.Character.DistinctiveFeatures.intoDistinctiveFeatures

object SheetInputs {
  def handleChangeFor[T](
      find: Loc[T]
  )(old: T, newVal: T): Msg = if newVal == old then NoOpMsg
  else SheetMsg(ValueEditCommand(newVal, old, find))

  def charNameInput(name: CharacterName) =
    exitableTextInput(
      styles(CSS.font("24pt bold")),
      `value` := name.label
    )((s: String) => handleChangeFor(CharacterNameLoc)(name, s.intoCharName))

  def playerNameInput(name: PlayerName) = exitableTextInput(
    styles(CSS.font("18pt bold")),
    `value` := name.label
  )((s: String) => handleChangeFor(PlayerNameLoc)(name, s.intoPlayerName))

  def distinctiveFeaturesInput(dfs: DistinctiveFeatures) = exitableTextArea(
    styles(CSS.width("300px"))
  )((s: String) =>
    handleChangeFor(DistinctiveFeaturesLoc)(dfs, s.intoDistinctiveFeatures)
  )(dfs.label)

  def statPoolInput(loc: StatLoc)(char: Character) =
    dicePoolEntry(
      `value` := loc(char).get.dice.diceRemaining.toString,
      styles(CSS.width("20px"), CSS.height("20px"))
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
    onClick(SheetMsg(TogglePoolMarkedCommand(loc)))
  )

  def charCheckboxInput(
      loc: Loc[Boolean]
  )(char: Character) = input(
    `type`    := "checkbox",
    `checked` := loc(char).get,
    onClick(SheetMsg(ToggleCommand(loc)))
  )

}
