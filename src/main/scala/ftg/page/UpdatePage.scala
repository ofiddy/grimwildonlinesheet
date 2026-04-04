package ftg.page

import ftg.Character.Character as Character
import tyrian.Cmd
import cats.effect.IO
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.SheetMsg
import ftg.command.CharCommand
import ftg.command.EffectCommand
import ftg.command.ModifyCharacter.modify
import ftg.page.cmds.GwCmds
import ftg.page.Msg.BlurMsg
import ftg.command.RollStatCommand
import ftg.DicePool.DicePool.given_PoolRollable_DicePool.roll
import ftg.DicePool.RollGenerator
import ftg.DicePool.RandomRollGenerator

object UpdatePage {
  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case NoOpMsg       => (model, Cmd.None)
    case SheetMsg(cmd) => applySheetCommand(model, cmd)
    case BlurMsg       => (model, GwCmds.unfocusCurrentblur)

  def applySheetCommand(
      model: Model,
      cmd: CharCommand
  ): (Model, Cmd[IO, Msg]) =
    given RollGenerator = RandomRollGenerator
    cmd match
      case r @ RollStatCommand(loc) =>
        val stat    = loc(model.character).get
        val roll    = stat.dice.roll
        val rollLog = s"Rolled ${roll.diceResults}"
        (model.copy(log = rollLog :: r :: model.log), Cmd.None)

      case e: EffectCommand =>
        (
          model.copy(
            character = modify(e, model.character),
            log = e :: model.log
          ),
          Cmd.None
        )

}
