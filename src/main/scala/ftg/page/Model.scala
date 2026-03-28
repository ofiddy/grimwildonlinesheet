package ftg.page

import ftg.page.Msg.SheetMsg

final case class Model(character: ftg.Character.Character, log: List[SheetMsg])
