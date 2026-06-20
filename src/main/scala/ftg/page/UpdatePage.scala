package ftg.page

import cats.effect.IO
import ftg.DicePool.DicePool.given_PoolRollable_DicePool.roll
import ftg.DicePool.RandomRollGenerator
import ftg.DicePool.RollGenerator
import ftg.command._
import ftg.command.ModifyCharacter.modify
import ftg.page.Msg.BlurMsg
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.SheetMsg
import ftg.page.cmds.GwCmds
import tyrian.Cmd
import ftg.page.Msg.IoMsg
import ftg.page.IoCmd.SaveCharacterMsg
import ftg.page.IoCmd.IoCmd
import ftg.page.IoCmd.LoadCharacterMsg
import ftg.page.Msg.TryParseAndLoadCharacter
import ftg.Character.{Character => Character}
import ftg.page.IoCmd.NewBlankCharacterMsg
import ftg.page.DefaultCharacter.blankChar
import ftg.page.Msg.OpenModal
import ftg.page.cmds.GwCmds.openModal
import ftg.page.cmds.GwCmds.closeModal
import ftg.page.Msg._

object UpdatePage {
  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case NoOpMsg                        => (model, Cmd.None)
    case SheetMsg(cmd)                  => applySheetCommand(model, cmd)
    case BlurMsg                        => (model, GwCmds.unfocusCurrentblur)
    case IoMsg(cmd)                     => applyIoCmd(model, cmd)
    case TryParseAndLoadCharacter(json) => tryLoadCharacter(json, model)
    case OpenModal =>
      (model.copy(currentModal = Some(TalentModal(""))), openModal)
    case CloseModal => (model.copy(currentModal = None), closeModal)
    case EditTalentModal(s) =>
      (model.copy(currentModal = Some(TalentModal(s))), Cmd.None)
    case EditTalentModalFilter(talentClass) =>
      (model.copy(classFilter = talentClass), Cmd.None)

  def applySheetCommand(
      model: Model,
      cmd: CharCommand
  ): (Model, Cmd[IO, Msg]) =
    given RollGenerator = RandomRollGenerator
    cmd match
      case r @ RollStatCommand(loc) =>
        val stat = loc(model.character).get
        val roll = stat.dice.roll
        (model log r withModal Some(DiceRollModal(roll)), openModal)

      case e: EffectCommand =>
        (
          modify(e, model) log e,
          Cmd.None
        )

      case RollLogAndThen(pool, andThen) => {
        val roll = pool.roll
        update(model log s"Rolled ${roll}")(SheetMsg(andThen(roll)))
      }

  def applyIoCmd(
      model: Model,
      cmd: IoCmd
  ): (Model, Cmd[IO, Msg]) = cmd match
    case SaveCharacterMsg => (model, GwCmds.downloadCharacter(model.character))
    case LoadCharacterMsg(id) => (model, GwCmds.loadCharacter(id))
    case NewBlankCharacterMsg => (Model.blankWithNewChar(blankChar), Cmd.None)

  def tryLoadCharacter(json: String, prevModel: Model): (Model, Cmd[IO, Msg]) =
    try {
      val char = upickle.read[Character](json)
      (Model.blankWithNewChar(char), Cmd.None)
    } catch
      _ => (prevModel, Cmd.None)

}
