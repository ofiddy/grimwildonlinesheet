package ftg.page.elems

import tyrian.Html
import tyrian.Html._
import ftg.Character.Bond
import ftg.page.Msg
import ftg.page.Msg.SheetMsg
import ftg.command.AddListElemCommand
import ftg.command.CharacterListFactories.NewBond
import ftg.command.CharacterLoc.BondsLoc
import ftg.command.DeleteListElemCommand

object BondsInput {
  def renderBonds(bonds: List[Bond]): Html[Msg] = div(
    h2("Bonds"),
    ul(
      li("Change a Bond: The other PC takes spark"),
      li("Quarrel: Both take spark")
    ),
    table(
      tr(
        th(),
        th("PC"),
        th("Bond")
      ) :: bonds.zipWithIndex.map(renderBond.tupled)
    ),
    button(onClick(SheetMsg(AddListElemCommand(NewBond, BondsLoc))))("Add Bond")
  )

  def renderBond(bond: Bond, index: Int): Html[Msg] = tr(
    td(
      button(onClick(SheetMsg(DeleteListElemCommand(bond, index, BondsLoc))))(
        "🗑️"
      )
    ),
    td(bond.pcName.label),
    td(bond.bondDesc.label)
  )
}
