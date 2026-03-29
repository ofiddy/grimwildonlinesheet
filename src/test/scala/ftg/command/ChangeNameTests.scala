package ftg.command.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.command.ModifyCharacter.modify
import ftg.command.ModifyCharacter.undo
import ftg.page.DefaultCharacter.detherilStarren
import ftg.command.ChangeName
import ftg.Character.CharacterName.*

class ChangeNameTests extends AnyFlatSpec with should.Matchers {
  "ChangeName" should "overwrite a characters name on modify" in {
    val premadeChar = detherilStarren
    val newName     = "NewName"
    val cmd         = ChangeName(newName, "Detheril Starren".charName)
    val afterModify = modify(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (newName.charName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      newName.charName
    )
  }

  it should "apply the old name on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd         = ChangeName("Detheril Starren", oldName.charName)
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (oldName.charName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      oldName.charName
    )
  }

  it should "ignore mismatch between stored and actual character name on undo" in {
    val premadeChar = detherilStarren
    val oldName     = "OldName"
    val cmd         = ChangeName("Dastardly Jim", oldName.charName)
    val afterModify = undo(cmd, premadeChar)
    afterModify.profile.characterName shouldBe (oldName.charName)
    afterModify.profile shouldBe premadeChar.profile.copy(characterName =
      oldName.charName
    )
  }
}
