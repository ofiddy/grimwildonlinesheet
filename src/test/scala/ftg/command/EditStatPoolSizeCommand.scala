package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.EditStatPoolSizeCommand
import ftg.command.CharacterLoc.StatLocs.*

class EditStatPoolSizeCommandTests extends AnyFlatSpec with should.Matchers {
  "EditStatPoolSizeCommand" should "overwrite at provided location on modify" in {
    val premadeChar = detherilStarren
    val newVal      = 6
    val cmd =
      EditStatPoolSizeCommand(
        newVal,
        premadeChar.stats.bodyStats.brawn.dice.diceRemaining,
        Brawn
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.stats.bodyStats.brawn.dice.diceRemaining shouldBe newVal
    afterModify.stats.bodyStats.brawn.dice shouldBe premadeChar.stats.bodyStats.brawn.dice
      .copy(diceRemaining = newVal)
  }

  it should "overwrite at provided location on undo" in {
    val premadeChar = detherilStarren
    val oldVal      = 6
    val cmd =
      EditStatPoolSizeCommand(
        premadeChar.stats.bodyStats.brawn.dice.diceRemaining,
        oldVal,
        Brawn
      )
    val afterModify = undo(cmd, premadeChar)
    afterModify.stats.bodyStats.brawn.dice.diceRemaining shouldBe oldVal
    afterModify.stats.bodyStats.brawn.dice shouldBe premadeChar.stats.bodyStats.brawn.dice
      .copy(diceRemaining = oldVal)
  }

  it should "ignore mismatch between stored and actual value on undo" in {
    val premadeChar = detherilStarren
    val oldVal      = 6
    val cmd =
      EditStatPoolSizeCommand(
        999999,
        oldVal,
        Brawn
      )
    val afterModify = undo(cmd, premadeChar)
    afterModify.stats.bodyStats.brawn.dice.diceRemaining shouldBe oldVal
    afterModify.stats.bodyStats.brawn.dice shouldBe premadeChar.stats.bodyStats.brawn.dice
      .copy(diceRemaining = oldVal)
  }

  it should "get correct stat at each provided location" in {
    val premadeChar = detherilStarren
    Brawn(premadeChar).get shouldBe premadeChar.stats.bodyStats.brawn
    Agility(premadeChar).get shouldBe premadeChar.stats.bodyStats.agility
    Wits(premadeChar).get shouldBe premadeChar.stats.mentalStats.wits
    Presence(premadeChar).get shouldBe premadeChar.stats.mentalStats.presence
  }

  it should "be reflective on modify and undo" in {
    val premadeChar = detherilStarren
    val newVal      = 6
    val cmd =
      EditStatPoolSizeCommand(
        newVal,
        premadeChar.stats.bodyStats.brawn.dice.diceRemaining,
        Brawn
      )
    undo(cmd, modify(cmd, premadeChar)) shouldBe premadeChar
  }
}
