package ftg.page.elems

import ftg.page.elems.ExitableInput.exitableTextInput
import tyrian.CSS
import tyrian.Html.*
import ftg.Character.CharacterName
import ftg.Character.CharacterName.*
import ftg.page.Msg.SheetMsg
import ftg.command.ChangeName
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg

object SheetInputs {
  def handleCharNameChange(name: CharacterName): (String => Msg) = s =>
    if s.charName != name then SheetMsg(ChangeName(s, name))
    else NoOpMsg

  def charNameInput(name: CharacterName) = exitableTextInput(
    styles(CSS.font("24pt bold")),
    `value` := name.label
  )(handleCharNameChange(name))
}
