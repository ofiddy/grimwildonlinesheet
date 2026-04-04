package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.ToggleCommand
import ftg.command.CharacterLoc.HarmLocs.Bloodied

class ToggleCommandTests extends AnyFlatSpec with should.Matchers {
  "ToggleCommand" should "change false to true on modify" in {
    val premadeChar = detherilStarren
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.stats.bodyStats.isBloodied shouldBe true
  }

  it should "change true to false on modify" in {
    val premadeChar = Bloodied(detherilStarren).replace(true)
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.stats.bodyStats.isBloodied shouldBe false
  }

  it should "change false to true on undo" in {
    val premadeChar = detherilStarren
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterUndo = undo(cmd, premadeChar)
    afterUndo.stats.bodyStats.isBloodied shouldBe true
  }

  it should "change true to false on undo" in {
    val premadeChar = Bloodied(detherilStarren).replace(true)
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterUndo = undo(cmd, premadeChar)
    afterUndo.stats.bodyStats.isBloodied shouldBe false
  }
}
