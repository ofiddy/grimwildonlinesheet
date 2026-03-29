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
import ftg.Character.FromString.FromString

object SheetInputs {
  def handleChangeFor[T: FromString](
      find: Loc[T]
  )(old: T, newVal: String): Msg = if newVal.into == old then NoOpMsg
  else SheetMsg(ValueEditCommand(newVal, old, find))

  def charNameInput(name: CharacterName) = exitableTextInput(
    styles(CSS.font("24pt bold")),
    `value` := name.label
  )(handleChangeFor(CharacterNameLoc)(name, _))

  def playerNameInput(name: PlayerName) = exitableTextInput(
    styles(CSS.font("18pt bold")),
    `value` := name.label
  )(handleChangeFor(PlayerNameLoc)(name, _))

  def distinctiveFeaturesInput(dfs: DistinctiveFeatures) = exitableTextArea(
    styles(CSS.width("300px"))
  )(handleChangeFor(DistinctiveFeaturesLoc)(dfs, _))(dfs.label)
}
