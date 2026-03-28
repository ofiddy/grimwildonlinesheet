package ftg.page

import ftg.Character.Character as Character
import tyrian.Cmd
import cats.effect.IO
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.SheetMsg
import ftg.command.CharCommand
import ftg.command.PureCommand
import ftg.command.EffectCommand
import ftg.command.ModifyCharacter.modify

object UpdatePage {
  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case NoOpMsg => (model, Cmd.None)
    case SheetMsg(cmd) =>
      (Model(applySheetCommand(model.character, cmd)), Cmd.None)

  def applySheetCommand(character: Character, cmd: CharCommand): Character =
    cmd match
      case _: PureCommand   => character
      case e: EffectCommand => modify(e, character)

}
