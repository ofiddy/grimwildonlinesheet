package ftg.elems

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.page.Msg.NoOpMsg
import ftg.Character.CharacterName.*
import ftg.page.Msg.SheetMsg
import ftg.command.ValueEditCommand
import ftg.command.CharacterLoc.CharacterNameLoc
import ftg.page.elems.SheetInputs.handleChangeFor

class SheetInputsTests extends AnyFlatSpec with should.Matchers {
  "handleChangeFor" should "produce an eventful Msg when old and new names differ" in {
    handleChangeFor(CharacterNameLoc)(
      "name1".intoCharName,
      "name2".intoCharName
    ) shouldBe SheetMsg(
      ValueEditCommand(
        "name2".intoCharName,
        "name1".intoCharName,
        CharacterNameLoc
      )
    )
  }

  it should "produce eventful Msg when the new name is empty" in {
    handleChangeFor(CharacterNameLoc)(
      "name1".intoCharName,
      "".intoCharName
    ) shouldBe SheetMsg(
      ValueEditCommand(
        "".intoCharName,
        "name1".intoCharName,
        CharacterNameLoc
      )
    )
  }

  it should "produce a noop Msg when the names are the same" in {
    handleChangeFor(CharacterNameLoc)(
      "name1".intoCharName,
      "name1".intoCharName
    ) shouldBe NoOpMsg
  }
}
