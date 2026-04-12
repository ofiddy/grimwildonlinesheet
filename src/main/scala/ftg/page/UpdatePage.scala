package ftg.page

import cats.effect.IO
import ftg.DicePool.DicePool.given_PoolRollable_DicePool.roll
import ftg.DicePool.RandomRollGenerator
import ftg.DicePool.RollGenerator
import ftg.command.CharCommand
import ftg.command.EffectCommand
import ftg.command.ModifyCharacter.modify
import ftg.command.RollStatCommand
import ftg.page.Msg.BlurMsg
import ftg.page.Msg.NoOpMsg
import ftg.page.Msg.SheetMsg
import ftg.page.cmds.GwCmds
import tyrian.Cmd

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
        (model log r log rollLog, Cmd.None)

      case e: EffectCommand =>
        (
          modify(e, model) log e,
          Cmd.None
        )

}
