package ftg.command

import utest._
import ftg.DicePool.RollGenerator
import ftg.DicePool.UnimplementedRollGenerator
import ftg.page.DefaultCharacter.detherilStarren
import ftg.page.Model
import ftg.Talent.ClassTalents.TestClass
import ftg.command.ModifyCharacter.modify
import ftg.Talent.ClassTalents.BardClass
import ftg.Talent.TalentADT.FriendlyFaceTalent
import ftg.command.ModifyCharacter.undo

class ChangeClassCommandTests extends TestSuite {
  given RollGenerator = UnimplementedRollGenerator

  val tests = Tests {
    test("ChangeClassCommand") {
      val premade = Model(detherilStarren, Nil)

      test("should change class and add new talent") {
        val cmd = ChangeClassCommand(
          TestClass,
          (premade.character.charClass, premade.character.coreTalent)
        )
        val after = modify(cmd, premade)
        after.character.charClass ==> TestClass
        after.character.coreTalent ==> TestClass.coreTalent(premade.character)
      }

      test("should restore on undo") {
        val cmd = ChangeClassCommand(
          BardClass,
          (TestClass, FriendlyFaceTalent)
        )
        val after = undo(cmd, premade)
        after.character.charClass ==> TestClass
        after.character.coreTalent ==> FriendlyFaceTalent
      }

      test("should be unchanged on modify then undo") {
        val cmd = ChangeClassCommand(
          TestClass,
          (premade.character.charClass, premade.character.coreTalent)
        )
        val after = undo(cmd, modify(cmd, premade))
        after.character ==> premade.character
      }
    }
  }
}
