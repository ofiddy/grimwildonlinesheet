package ftg.page.elems

import ftg.Character.Bond
import ftg.Character.CharacterName._
import ftg.Character.CustomBond
import ftg.Character.PremadeBond
import ftg.Character.PremadeBonds.premadeLeftBonds
import ftg.Character.PremadeBonds.premadeRightBonds
import ftg.command.AddListElemCommand
import ftg.command.CharacterListFactories.NewBond
import ftg.command.CharacterLoc.BondsLoc
import ftg.command.DeleteListElemCommand
import ftg.command.ModifyListElemCommand
import ftg.page.Msg
import ftg.page.Msg.SheetMsg
import ftg.page.elems.ExitableInput.exitableTextInput
import ftg.page.elems.SheetInputs.handleChangeForAtIndex
import tyrian.CSS
import tyrian.Html
import tyrian.Html._

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
        th("Bond"),
        th()
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
    td(
      exitableTextInput(`value` := bond.pcName.toString)(s =>
        handleChangeForAtIndex(BondsLoc)(
          bond,
          bond.copy(pcName = s.intoCharName),
          index
        )
      )
    ),
    td(
      bond.bondDesc match
        case CustomBond(label) =>
          exitableTextInput(`value` := label)(s =>
            handleChangeForAtIndex(BondsLoc)(
              bond,
              bond.copy(bondDesc = CustomBond(s)),
              index
            )
          )
        case p: PremadeBond =>
          renderPremadeBondsList(bond, p, index)
    ),
    td(
      bond.bondDesc match
        case CustomBond(label) =>
          button(
            onClick(
              SheetMsg(
                ModifyListElemCommand(
                  bond.copy(bondDesc =
                    PremadeBond(premadeLeftBonds(0), premadeRightBonds(0))
                  ),
                  bond,
                  index,
                  BondsLoc
                )
              )
            )
          )("📝")
        case PremadeBond(labelLeft, labelRight) =>
          button(
            onClick(
              SheetMsg(
                ModifyListElemCommand(
                  bond.copy(bondDesc = CustomBond("")),
                  bond,
                  index,
                  BondsLoc
                )
              )
            )
          )("🔧")
    )
  )

  def renderPremadeBondsList(
      bond: Bond,
      leftAndRight: PremadeBond,
      index: Int
  ) = {
    val leftBondMap =
      premadeLeftBonds.map(lb => (lb.toString.toLowerCase, lb)).toMap
    val rightBondMap =
      premadeRightBonds.map(rb => (rb.toString.toLowerCase, rb)).toMap

    div(styles(CSS.`display`("flex")))(
      select(
        onInput(s =>
          handleChangeForAtIndex(BondsLoc)(
            bond,
            bond.copy(bondDesc =
              PremadeBond(leftBondMap(s), leftAndRight.labelRight)
            ),
            index
          )
        )
      )(
        premadeLeftBonds.map(lb =>
          option(
            `value`  := lb.toString.toLowerCase,
            selected := leftAndRight.labelLeft == lb
          )(
            lb.toString
          )
        )
      ),
      select(
        onInput(s =>
          handleChangeForAtIndex(BondsLoc)(
            bond,
            bond.copy(bondDesc =
              PremadeBond(leftAndRight.labelLeft, rightBondMap(s))
            ),
            index
          )
        )
      )(
        premadeRightBonds.map(rb =>
          option(
            `value`  := rb.toString.toLowerCase,
            selected := leftAndRight.labelRight == rb
          )(
            rb.toString
          )
        )
      )
    )
  }

}
