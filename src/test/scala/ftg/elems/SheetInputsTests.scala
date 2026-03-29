package ftg.elems

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.elems.SheetInputs.handleCharNameChange
import ftg.page.Msg.NoOpMsg
import ftg.Character.CharacterName.*
import ftg.Character.PlayerName.*
import ftg.command.ChangeName
import ftg.page.Msg.SheetMsg
import ftg.command.ChangePlayerName
import ftg.page.elems.SheetInputs.handlePlayerNameChange

class CharNameInputTests extends AnyFlatSpec with should.Matchers {
  "handleCharNameChange" should "produce an eventful Msg when old and new names differ" in {
    handleCharNameChange("name1".charName)("name2") shouldBe SheetMsg(
      ChangeName(
        "name2",
        "name1".charName
      )
    )
  }

  it should "produce eventful Msg when the new name is empty" in {
    handleCharNameChange("name1".charName)("") shouldBe SheetMsg(
      ChangeName(
        "",
        "name1".charName
      )
    )
  }

  it should "produce a noop Msg when the names are the same" in {
    handleCharNameChange("name1".charName)("name1") shouldBe NoOpMsg
  }

  "handlePlayerNameChange" should "produce an eventful Msg when old and new names differ" in {
    handlePlayerNameChange("name1".playerName)("name2") shouldBe SheetMsg(
      ChangePlayerName(
        "name2",
        "name1".playerName
      )
    )
  }

  it should "produce eventful Msg when the new name is empty" in {
    handlePlayerNameChange("name1".playerName)("") shouldBe SheetMsg(
      ChangePlayerName(
        "",
        "name1".playerName
      )
    )
  }

  it should "produce a noop Msg when the names are the same" in {
    handleCharNameChange("name1".charName)("name1") shouldBe NoOpMsg
  }
}
