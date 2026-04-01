package ftg.page

import ftg.command.CharCommand

type Log = List[CharCommand | String]

final case class Model(
    character: ftg.Character.Character,
    log: Log
)
