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

@JSExportTopLevel("TyrianApp")
object GrimwildOnlineSheet extends TyrianIOApp[Msg, Model]:

  def router: Location => Msg =
    Routing.none(NoOpMsg)

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    (Model(DefaultCharacter.detherilStarren, Nil), Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    UpdatePage.update(model)

  def view(model: Model): Html[Msg] =
    div(
      renderCharacter(model.character),
      h1("Debug Log"),
      div(model.log.map(l => p(l.toString())))
    )

  def subscriptions(model: Model): Sub[IO, Msg] =
    Sub.None
