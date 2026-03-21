package ftg

import cats.effect.IO
import tyrian.Html.*
import tyrian.*

import scala.scalajs.js.annotation.*
import ftg.page.DefaultCharacter
import ftg.page.Model
import ftg.page.CharacterHtmlRenderer.renderCharacter

@JSExportTopLevel("TyrianApp")
object GrimwildOnlineSheet extends TyrianIOApp[Msg, Model]:

  def router: Location => Msg =
    Routing.none(Msg.NoOp)

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    (Model(DefaultCharacter.detherilStarren), Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case Msg.Increment => (model, Cmd.None)
    case Msg.Decrement => (model, Cmd.None)
    case Msg.NoOp      => (model, Cmd.None)

  def view(model: Model): Html[Msg] =
    renderCharacter(model.character)

  def subscriptions(model: Model): Sub[IO, Msg] =
    Sub.None

enum Msg:
  case Increment, Decrement, NoOp
