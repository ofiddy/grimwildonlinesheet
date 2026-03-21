package ftg.page

import ftg.Character.Character as Character
import tyrian.Html
import tyrian.Html.*
import tyrian.syntax.*
import ftg.Character.CharacterProfile
import ftg.Character.CharacterBaseStats
import tyrian.CSS

object CharacterHtmlRenderer {
  def renderCharacter[T](char: Character): Html[T] = div(
    h1("Grimwild Online Character Sheet"),
    renderProfile(char.profile),
    renderStats(char.stats)
  )

  def renderProfile[T](profile: CharacterProfile): Html[T] = div(
    p("Character Name"),
    h2(profile.characterName.label),
    p("Player Name"),
    h2(profile.playerName.label),
    h3("Distinctive Features"),
    p(profile.distinctiveFeatures.mkString(", "))
  )

  def renderStats[T](stats: CharacterBaseStats): Html[T] =
    div(style(CSS.`background-color`("#DDDDDD")))(
      table(
        tr(
          th("Brawn"),
          th("Agility"),
          th("Wits"),
          th("Presence")
        ),
        tr(
          td(stats.bodyStats.brawn.dice.diceRemaining.toString()),
          td(stats.bodyStats.agility.dice.diceRemaining.toString()),
          td(stats.mentalStats.wits.dice.diceRemaining.toString()),
          td(stats.mentalStats.presence.dice.diceRemaining.toString())
        ),
        tr(
          td(
            input(
              `type`    := "checkbox",
              `value`   := "isMarked",
              `checked` := stats.bodyStats.brawn.isMarked
            )
          ),
          td(
            input(
              `type`    := "checkbox",
              `value`   := "isMarked",
              `checked` := stats.bodyStats.agility.isMarked
            )
          ),
          td(
            input(
              `type`    := "checkbox",
              `value`   := "isMarked",
              `checked` := stats.mentalStats.wits.isMarked
            )
          ),
          td(
            input(
              `type`    := "checkbox",
              `value`   := "isMarked",
              `checked` := stats.mentalStats.presence.isMarked
            )
          )
        )
      ),
      table(
        tr(
          td("Bloodied"),
          td(
            div(
              input(
                `type`    := "checkbox",
                `checked` := stats.bodyStats.isBloodied
              ),
              stats.bodyStats.bloodied
                .map(v => button(v.diceRemaining.toString()))
                .orEmpty
            )
          )
        ),
        tr(
          td("Rattled"),
          td(
            div(
              input(
                `type`    := "checkbox",
                `checked` := stats.mentalStats.isRattled
              ),
              stats.mentalStats.rattled
                .map(v => button(v.diceRemaining.toString()))
                .orEmpty
            )
          )
        )
      )
    )
}
