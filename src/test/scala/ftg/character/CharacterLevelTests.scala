package ftg.character.test

import org.scalatest.*
import flatspec.*
import matchers.*
import ftg.Character.{Character => Character}

class CHaracterLevelTests extends AnyFlatSpec with should.Matchers {
  "Character Experience" should "correctly calculate levels" in {
    Character.level(1) shouldBe 1
    Character.level(2) shouldBe 1
    Character.level(3) shouldBe 2
    Character.level(6) shouldBe 3
    Character.level(10) shouldBe 4
    Character.level(13) shouldBe 4
    Character.level(15) shouldBe 5
    Character.level(21) shouldBe 6
    Character.level(23) shouldBe 6
    Character.level(28) shouldBe 7
    Character.level(99) shouldBe 7
  }

}
