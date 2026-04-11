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

class TogglePoolMarkedCommandTests extends AnyFlatSpec with should.Matchers {
  "TogglePoolMarkedCommand" should "change false to true on modify" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.stats.bodyStats.brawn.isMarked shouldBe true
  }

  it should "change true to false on modify" in {
    val premadeChar = Brawn(detherilStarren).andThen(markedLens).replace(true)
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.stats.bodyStats.brawn.isMarked shouldBe false
  }

  it should "change false to true on undo" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterUndo = undo(cmd, premadeChar)
    afterUndo.stats.bodyStats.brawn.isMarked shouldBe true
  }

  it should "change true to false on undo" in {
    val premadeChar = Brawn(detherilStarren).andThen(markedLens).replace(true)
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    val afterUndo = undo(cmd, premadeChar)
    afterUndo.stats.bodyStats.brawn.isMarked shouldBe false
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val cmd =
      TogglePoolMarkedCommand(
        Brawn
      )
    undo(cmd, modify(cmd, premadeChar)) shouldBe premadeChar
  }
}
