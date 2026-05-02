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
import tyrian.Html
import tyrian.Html._

object BondsInput {
  def renderBonds(bonds: List[Bond]): Html[Msg] =
    div(cls := "shaded-area card-section")(
      div(cls := "card-black-header")(
        h2("BONDS"),
        span(
          "CHANGE A BOND: THE OTHER PC TAKES SPARK | QUARREL: BOTH TAKE SPARK"
        )
      ),
      div(cls := "card-section-inner")(
        div(cls := "white-card-table-wrapper")(
          table(cls := "white-card-table")(
            tr(cls := "white-table-header")(
              th(id := "bonds-x-header")(),
              th(id := "pc-header")("PC"),
              th("Bond"),
              th(id := "bonds-switch-header")("Switch")
            ) :: bonds.zipWithIndex.map(renderBond.tupled)
          )
        ),
        button(
          cls := "bond-add",
          onClick(SheetMsg(AddListElemCommand(NewBond, BondsLoc)))
        )(
          "Add Bond"
        )
      )
    )

  def renderBond(bond: Bond, index: Int): Html[Msg] = tr(
    td(cls := "white-table-cell centre-cell")(
      button(
        cls := "bond-delete-button",
        onClick(SheetMsg(DeleteListElemCommand(bond, index, BondsLoc)))
      )(
        b("X")
      )
    ),
    td(cls := "white-table-cell")(
      exitableTextInput(
        cls     := "white-table-entry",
        `value` := bond.pcName.toString
      )(s =>
        handleChangeForAtIndex(BondsLoc)(
          bond,
          bond.copy(pcName = s.intoCharName),
          index
        )
      )
    ),
    td(cls := "white-table-cell")(
      bond.bondDesc match
        case CustomBond(label) =>
          exitableTextInput(
            cls           := "white-table-entry",
            `value`       := label,
            `placeholder` := "Write Bond Here"
          )(s =>
            handleChangeForAtIndex(BondsLoc)(
              bond,
              bond.copy(bondDesc = CustomBond(s)),
              index
            )
          )
        case p: PremadeBond =>
          renderPremadeBondsList(bond, p, index)
    ),
    td(cls := "white-table-cell centre-cell")(
      bond.bondDesc match
        case CustomBond(label) =>
          button(
            cls := "bond-switch-button",
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
          )("CUSTOM")
        case PremadeBond(labelLeft, labelRight) =>
          button(
            cls := "bond-switch-button",
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
          )("PREMADE")
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

    div(cls := "horizontal")(
      select(
        cls := "bonds-list",
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
        cls := "bonds-list",
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
