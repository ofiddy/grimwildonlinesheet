package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.ChangePlayerName
import ftg.Character.PlayerName.*

class ChangePlayerNameTests extends AnyFlatSpec with should.Matchers {
  "ChangePlayerName" should "overwrite a player name on modify" in {
    val premadeChar = detherilStarren
    val newName     = "NewName"
    val cmd         = ChangePlayerName(newName, "Lucy Holderton".playerName)
    val afterModify = modify(cmd, premadeChar)
    afterModify.profile.playerName shouldBe (newName.playerName)
    afterModify.profile shouldBe premadeChar.profile.copy(playerName =
      newName.playerName
    )
  }

  it should "apply the old name on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd         = ChangePlayerName("Lucy Holderton", oldName.playerName)
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.playerName shouldBe (oldName.playerName)
    afterModify.profile shouldBe premadeChar.profile.copy(playerName =
      oldName.playerName
    )
  }

  it should "ignore mismatch between stored and actual character name on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd         = ChangePlayerName("Dastardly Jim", oldName.playerName)
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.playerName shouldBe (oldName.playerName)
    afterModify.profile shouldBe premadeChar.profile.copy(playerName =
      oldName.playerName
    )
  }
}
