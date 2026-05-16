package ftg.page

import ftg.Character.{Character => Character}
import ftg.command.CharCommand

type SingleLog = CharCommand | String
type Log       = List[SingleLog]

final case class Model(
    character: ftg.Character.Character,
    log: Log,
    currentModal: Option[TalentModal]
) {
  infix def withChar(f: Character => Character): Model =
    this.copy(character = f(this.character))

  infix def log(l: SingleLog): Model = copy(log = l :: this.log)
}

object Model {
  def blankWithNewChar(c: Character): Model = Model(c, Nil, None)

  def apply(character: ftg.Character.Character, log: Log): Model =
    Model(character, log, None)
}
