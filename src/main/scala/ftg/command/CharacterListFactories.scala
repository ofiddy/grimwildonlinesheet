package ftg.command

import ftg.Character.Bond
import ftg.Character.CharacterName._
import ftg.Character.Condition
import ftg.Character.PremadeBond
import ftg.Character.PremadeBonds
import ftg.Character.PremadeBonds.premadeLeftBonds
import ftg.Character.PremadeBonds.premadeRightBonds
import ftg.Character.ShortTermCondition

object CharacterListFactories {
  trait ElemFactory[T] {
    def build: T
  }

  case object NewCondition extends ElemFactory[Condition] {
    override def build: Condition =
      Condition(Some("New Condition"), ShortTermCondition)
  }

  case object NewBond extends ElemFactory[Bond] {
    override def build: Bond =
      Bond(
        "Name".intoCharName,
        PremadeBond(premadeLeftBonds(0), premadeRightBonds(0))
      )
  }
}
