package ftg.command

import ftg.Character.Condition
import ftg.Character.ShortTermCondition

object CharacterListFactories {
  trait ElemFactory[T] {
    def build: T
  }

  case object NewCondition extends ElemFactory[Condition] {
    override def build: Condition =
      Condition(Some("New Condition"), ShortTermCondition)
  }
}
