package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.ToggleCommand
import ftg.command.CharacterLoc.HarmLocs.Bloodied
import ftg.DicePool.RollGenerator
import ftg.DicePool.UnimplementedRollGenerator
import ftg.page.Model

class ToggleCommandTests extends AnyFlatSpec with should.Matchers {
  given RollGenerator = UnimplementedRollGenerator

  "ToggleCommand" should "change false to true on modify" in {
    val premadeChar = detherilStarren
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.stats.bodyStats.isBloodied shouldBe true
  }

  it should "change true to false on modify" in {
    val premadeChar = Bloodied(detherilStarren).replace(true)
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterModify = modify(cmd, Model(premadeChar, Nil)).character
    afterModify.stats.bodyStats.isBloodied shouldBe false
  }

  it should "change false to true on undo" in {
    val premadeChar = detherilStarren
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.stats.bodyStats.isBloodied shouldBe true
  }

  it should "change true to false on undo" in {
    val premadeChar = Bloodied(detherilStarren).replace(true)
    val cmd =
      ToggleCommand(
        Bloodied
      )
    val afterUndo = undo(cmd, Model(premadeChar, Nil)).character
    afterUndo.stats.bodyStats.isBloodied shouldBe false
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val cmd =
      ToggleCommand(
        Bloodied
      )
    undo(
      cmd,
      modify(cmd, Model(premadeChar, Nil))
    ).character shouldBe premadeChar
  }
}
