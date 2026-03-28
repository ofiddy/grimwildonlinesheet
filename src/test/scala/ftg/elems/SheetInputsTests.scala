package ftg.elems

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.elems.SheetInputs.handleCharNameChange
import ftg.page.Msg.NoOpMsg
import ftg.Character.CharacterName.*
import ftg.command.ChangeName
import ftg.page.Msg.SheetMsg

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
}
