package ftg.command

import utest._
import ftg.page.DefaultCharacter.detherilStarren
import ftg.page.Model
import ftg.Talent.ClassTalents.BardTalents.FriendlyFaceDesc
import ftg.command.ModifyCharacter.modify
import ftg.DicePool.RollGenerator
import ftg.DicePool.UnimplementedRollGenerator
import ftg.Talent.TalentADT.BardicLoreTalent
import ftg.Talent.TalentADT.FriendlyFaceTalent
import ftg.Talent.ClassTalents.BardTalents.BardsongTalentDesc
import ftg.Talent.TalentADT.BardsongTalent
import ftg.command.ModifyCharacter.undo

class ToggleTalentCommandTests extends TestSuite {
  given RollGenerator = UnimplementedRollGenerator

  val tests = Tests {
    test("ToggleTalentCommand") {

      val model = Model(detherilStarren.copy(talents = Nil), Nil)

      test("should add talent to empty list") {
        val testDesc    = FriendlyFaceDesc
        val testTalent  = FriendlyFaceDesc(model.character)
        val cmd         = ToggleTalentCommand(testDesc, model.character.talents)
        val afterModify = modify(cmd, model).character
        afterModify.talents ==> List(testTalent)
      }

      test("should add talent to existing list and sort alphabetically") {
        val existingList = List(BardicLoreTalent(false), FriendlyFaceTalent)
        val premade      = model withChar (_.copy(talents = existingList))
        val testDesc     = BardsongTalentDesc
        val testTalent   = testDesc(premade.character)
        val cmd = ToggleTalentCommand(testDesc, premade.character.talents)
        val afterModify = modify(cmd, premade).character
        afterModify.talents ==> List(
          BardicLoreTalent(false),
          testTalent,
          FriendlyFaceTalent
        )
      }

      test("should restore original list on undo") {
        val tals = List(BardsongTalent(2, 2))
        val cmd =
          ToggleTalentCommand(FriendlyFaceDesc, tals)
        val afterModify = undo(cmd, model).character
        afterModify.talents ==> tals
      }

      test("cause no change after immediate undo") {
        val cmd = ToggleTalentCommand(FriendlyFaceDesc, model.character.talents)
        undo(
          cmd,
          modify(cmd, model)
        ).character ==> model.character
      }
    }
  }
}
