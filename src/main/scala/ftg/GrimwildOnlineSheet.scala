package ftg

import cats.effect.IO
import ftg.page.CharacterHtmlRenderer.renderCharacter
import ftg.page.DefaultCharacter
import ftg.page.Model
import ftg.page.Msg
import ftg.page.Msg.NoOpMsg
import ftg.page.UpdatePage
import tyrian.Html._
import tyrian._

import scala.scalajs.js.annotation._
import ftg.page.Toolbar.renderToolbar
import ftg.page.Modals.renderModal

@JSExportTopLevel("TyrianApp")
object GrimwildOnlineSheet extends TyrianIOApp[Msg, Model]:

  def router: Location => Msg =
    Routing.none(NoOpMsg)

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    (Model(DefaultCharacter.detherilStarren, Nil, None, None), Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    UpdatePage.update(model)

  def view(model: Model): Html[Msg] =
    div(
      h1("Grimwild Online Character Sheet"),
      renderToolbar(model),
      renderCharacter(model.character),
      renderModal(model),
      h2("Debug Log"),
      div(model.log.map(l => p(l.toString())))
    )

  def subscriptions(model: Model): Sub[IO, Msg] =
    Sub.None
