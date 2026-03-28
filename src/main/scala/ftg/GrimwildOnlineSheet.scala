package ftg

import cats.effect.IO
import tyrian.*

import scala.scalajs.js.annotation.*
import ftg.page.DefaultCharacter
import ftg.page.Model
import ftg.page.CharacterHtmlRenderer.renderCharacter
import ftg.page.Msg
import ftg.page.Msg.NoOpMsg
import ftg.page.UpdatePage

@JSExportTopLevel("TyrianApp")
object GrimwildOnlineSheet extends TyrianIOApp[Msg, Model]:

  def router: Location => Msg =
    Routing.none(NoOpMsg)

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    (Model(DefaultCharacter.detherilStarren), Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    UpdatePage.update(model)

  def view(model: Model): Html[Msg] =
    renderCharacter(model.character)

  def subscriptions(model: Model): Sub[IO, Msg] =
    Sub.None
