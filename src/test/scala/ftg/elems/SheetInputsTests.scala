package ftg.elems

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.elems.SheetInputs.handleCharNameChange
import ftg.page.Msg.NoOpMsg
import ftg.Character.CharacterName.*
import ftg.Character.PlayerName.*
import ftg.page.Msg.SheetMsg
import ftg.page.elems.SheetInputs.handlePlayerNameChange
import ftg.command.ValueEditCommand
import ftg.command.CharacterLoc.CharacterNameLoc
import ftg.command.CharacterLoc.PlayerNameLoc

class CharNameInputTests extends AnyFlatSpec with should.Matchers {
  "handleCharNameChange" should "produce an eventful Msg when old and new names differ" in {
    handleCharNameChange("name1".intoCharName)("name2") shouldBe SheetMsg(
      ValueEditCommand(
        "name2",
        "name1".intoCharName,
        CharacterNameLoc
      )
    )
  }

  it should "produce eventful Msg when the new name is empty" in {
    handleCharNameChange("name1".intoCharName)("") shouldBe SheetMsg(
      ValueEditCommand(
        "",
        "name1".intoCharName,
        CharacterNameLoc
      )
    )
  }

  it should "produce a noop Msg when the names are the same" in {
    handleCharNameChange("name1".intoCharName)("name1") shouldBe NoOpMsg
  }

  "handlePlayerNameChange" should "produce an eventful Msg when old and new names differ" in {
    handlePlayerNameChange("name1".intoPlayerName)("name2") shouldBe SheetMsg(
      ValueEditCommand(
        "name2",
        "name1".intoPlayerName,
        PlayerNameLoc
      )
    )
  }

  it should "produce eventful Msg when the new name is empty" in {
    handlePlayerNameChange("name1".intoPlayerName)("") shouldBe SheetMsg(
      ValueEditCommand(
        "",
        "name1".intoPlayerName,
        PlayerNameLoc
      )
    )
  }

  it should "produce a noop Msg when the names are the same" in {
    handleCharNameChange("name1".intoCharName)("name1") shouldBe NoOpMsg
  }
}
