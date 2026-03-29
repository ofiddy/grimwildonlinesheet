package ftg.page.elems

import ftg.page.elems.ExitableInput.exitableTextInput
import tyrian.CSS
import tyrian.Html.*
import ftg.Character.CharacterName
import ftg.Character.CharacterName.*
import ftg.page.Msg.SheetMsg
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg
import ftg.Character.PlayerName
import ftg.Character.PlayerName.*
import ftg.command.*
import ftg.Character.DistinctiveFeatures
import ftg.page.elems.ExitableTextArea.exitableTextArea
import ftg.command.CharacterLoc.*
import ftg.Character.CharacterName.into

object SheetInputs {
  def handleCharNameChange(name: CharacterName): (String => Msg) = s =>
    if s.into != name then SheetMsg(ValueEditCommand(s, name, CharacterNameLoc))
    else NoOpMsg

  def charNameInput(name: CharacterName) = exitableTextInput(
    styles(CSS.font("24pt bold")),
    `value` := name.label
  )(handleCharNameChange(name))

  def handlePlayerNameChange(name: PlayerName): (String => Msg) = s =>
    if s.into != name then SheetMsg(ValueEditCommand(s, name, PlayerNameLoc))
    else NoOpMsg

  def playerNameInput(name: PlayerName) = exitableTextInput(
    styles(CSS.font("18pt bold")),
    `value` := name.label
  )(handlePlayerNameChange(name))

  def handleDistinctiveFeaturesChange(
      dfs: DistinctiveFeatures
  ): (String => Msg) = s =>
    if s.into != dfs then
      SheetMsg(ValueEditCommand(s, dfs, DistinctiveFeaturesLoc))
    else NoOpMsg

  def distinctiveFeaturesInput(dfs: DistinctiveFeatures) = exitableTextArea(
    styles(CSS.width("300px"))
  )(handleDistinctiveFeaturesChange(dfs))(dfs.label)
}
