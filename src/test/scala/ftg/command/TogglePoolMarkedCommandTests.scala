package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.TogglePoolMarkedCommand
import ftg.command.CharacterLoc.StatLocs.Brawn
import ftg.Character.StatPool.markedLens
import ftg.DicePool.RollGenerator
import ftg.DicePool.UnimplementedRollGenerator
import ftg.page.Model

class TogglePoolMarkedCommandTests extends AnyFlatSpec with should.Matchers {
  given RollGenerator = UnimplementedRollGenerator

  "TogglePoolMarkedCommand" should "change false to true on modify" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.stats.bodyStats.brawn.isMarked shouldBe true
  }

  it should "change true to false on modify" in {
    val premadeChar = Brawn(detherilStarren).andThen(markedLens).replace(true)
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.stats.bodyStats.brawn.isMarked shouldBe false
  }

  it should "change false to true on undo" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.stats.bodyStats.brawn.isMarked shouldBe true
  }

  it should "change true to false on undo" in {
    val premadeChar = Brawn(detherilStarren).andThen(markedLens).replace(true)
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.stats.bodyStats.brawn.isMarked shouldBe false
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))
    ).character shouldBe premadeChar
  }
}
