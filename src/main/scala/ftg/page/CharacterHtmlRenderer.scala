package ftg.page

import ftg.Character.Character as Character
import tyrian.Html
import tyrian.Html.*
import tyrian.syntax.*
import ftg.Character.CharacterProfile
import ftg.Character.CharacterBaseStats
import tyrian.CSS
import ftg.Character.Condition
import ftg.Character.ShortTermCondition
import ftg.Character.LongTermCondition
import ftg.Character.PermanentCondition
import ftg.Character.UrgentCondition
import ftg.Character.Story
import scala.Range
import ftg.Character.Story.toInt
import ftg.Character.Story.startingStory
import ftg.Character.Spark
import ftg.Character.CharacterDetails
import ftg.Character.Background

object CharacterHtmlRenderer {
  def renderCharacter[T](char: Character): Html[T] = div(
    h1("Grimwild Online Character Sheet"),
    renderProfile(char.profile),
    renderStats(char.stats),
    renderConditions(char.conditions),
    renderStoryAndSpark(char.story, char.spark),
    renderCharacterDetails(char.details)
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

  def renderConditions[T](conditions: List[Condition]): Html[T] =
    div(
      h3("Conditions"),
      ul(
        conditions.map(c => li(displayCondition(c)))
      ),
      p(em("VEX:"), span(" FIGHT–FLIGHT–FREEZE–FREAKOUT"))
    )

  def renderStoryAndSpark[T](story: Story, spark: Spark): Html[T] = div(
    table(
      tr(
        td(em("STORY")) +:
          createAndFillCheckboxes(
            story.toInt,
            startingStory.toInt - story.toInt
          ).map(b => td(b))
      ),
      tr(
        td(em("SPARK")) +:
          createAndFillCheckboxes(spark.toInt, 2 - spark.toInt).map(b => td(b))
      )
    )
  )

  def displayCondition(condition: Condition): String = condition match
    case ShortTermCondition(name)    => name
    case LongTermCondition(name)     => name
    case PermanentCondition(name)    => name
    case UrgentCondition(name, pool) => s"${name} ${pool.diceRemaining}d"

  def createAndFillCheckboxes[T](filled: Int, empty: Int): List[Html[T]] =
    Range(0, filled + empty)
      .map(i => input(`type` := "checkbox", `checked` := i < filled))
      .toList

  def renderCharacterDetails[T](details: CharacterDetails): Html[T] = div(
    h2("Character Details"),
    table(
      tr(
        th("Background"),
        th("Wise"),
        th("Wise"),
        th("Wise")
      ),
      renderBackgroundRows(details.backgrounds._1),
      renderBackgroundRows(details.backgrounds._2)
    ),
    div(styles(CSS.`display`("flex"), CSS.`gap`("10px")))(
      div(
        h3("Traits") +:
          details.traits.twoYouAre.map(t => p(s"✔️ ${t.label}")) :+
          p(details.traits.oneYouArent match
            case None    => "❌"
            case Some(t) => s"❌ ${t.label}")
      ),
      div(
        h3("Desires") +:
          details.desires.twoYouWant.map(t => p(s"✔️ ${t.label}")) :+
          p(details.desires.oneYouDont match
            case None    => "❌"
            case Some(t) => s"❌ ${t.label}")
      )
    )
  )

  def renderBackgroundRows[T](background: Background): Html[T] = tr(
    td(background.description.getOrElse("")) +:
      background.wises.productIterator.toList.map {
        _ match
          case Some(wise) => td(wise.toString)
          case None       => td("")
      }
  )

}
