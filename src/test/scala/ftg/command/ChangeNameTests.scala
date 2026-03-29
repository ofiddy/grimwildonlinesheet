package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.Character.CharacterName.*
import ftg.command.ValueEditCommand
import ftg.command.CharacterLoc.CharacterNameLoc

class ChangeNameTests extends AnyFlatSpec with should.Matchers {
  "ValueEditCommand" should "overwrite at provided location on modify" in {
    val premadeChar = detherilStarren
    val newName     = "NewName"
    val cmd =
      ValueEditCommand(
        newName,
        "Detheril Starren".intoCharName,
        CharacterNameLoc
      )
    val afterModify = modify(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (newName.intoCharName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      newName.intoCharName
    )
  }

  it should "overwrite at provided location on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd =
      ValueEditCommand(
        "Detheril Starren",
        oldName.intoCharName,
        CharacterNameLoc
      )
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (oldName.intoCharName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      oldName.intoCharName
    )
  }

  it should "ignore mismatch between stored and actual value on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd =
      ValueEditCommand("Dastardly Jim", oldName.intoCharName, CharacterNameLoc)
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (oldName.intoCharName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      oldName.intoCharName
    )
  }
}
