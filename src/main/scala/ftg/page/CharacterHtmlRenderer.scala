package ftg.page

import ftg.Character.CharacterProfile
import ftg.Character.Condition
import ftg.Character.Experience
import ftg.Character.Experience._
import ftg.Character.Spark
import ftg.Character.Spark._
import ftg.Character.Story
import ftg.Character.Story._
import ftg.Character.{Character => Character}
import ftg.command.CharacterLoc.BackgroundLocs._
import ftg.command.CharacterLoc.ExpLoc
import ftg.command.CharacterLoc.HarmLocs.Bloodied
import ftg.command.CharacterLoc.HarmLocs.Rattled
import ftg.command.CharacterLoc.SparkLoc
import ftg.command.CharacterLoc.StatLocs._
import ftg.command.CharacterLoc.StoryLoc
import ftg.command.RollStatCommand.RollAgility
import ftg.command.RollStatCommand.RollBrawn
import ftg.command.RollStatCommand.RollPresence
import ftg.command.RollStatCommand.RollWits
import ftg.command.ValueEditCommand
import ftg.page.Msg.SheetMsg
import ftg.page.elems.BackgroundsElements.renderBackgroundRows
import ftg.page.elems.BondsInput.renderBonds
import ftg.page.elems.ConditionsInput.renderConditions
import ftg.page.elems.SheetInputs.charCheckboxInput
import ftg.page.elems.SheetInputs.charNameInput
import ftg.page.elems.SheetInputs.distinctiveFeaturesInput
import ftg.page.elems.SheetInputs.handleChangeFor
import ftg.page.elems.SheetInputs.markedInput
import ftg.page.elems.SheetInputs.playerNameInput
import ftg.page.elems.SheetInputs.statPoolInput
import ftg.page.elems.StoryArcsInput.renderStoryArcs
import ftg.page.elems.TraitsDesiresInput.renderDesires
import ftg.page.elems.TraitsDesiresInput.renderTraits
import tyrian.CSS
import tyrian.Html
import tyrian.Html._

import scala.Range
import scala.annotation.tailrec

object CharacterHtmlRenderer {
  def renderCharacter(char: Character): Html[Msg] = div(
    h1("Grimwild Online Character Sheet"),
    renderProfile(char.profile),
    renderStats(char),
    renderConditions(char.conditions),
    renderStoryAndSpark(char.story, char.spark),
    renderCharacterDetails(char),
    renderBonds(char.bonds),
    renderStoryArcs(char.groupArc, char.characterArc),
    renderExperience(char.experience)
  )

  def renderProfile(profile: CharacterProfile): Html[Msg] = div(
    p("Character Name"),
    charNameInput(profile.characterName),
    p("Player Name"),
    playerNameInput(profile.playerName),
    h3("Distinctive Features"),
    distinctiveFeaturesInput(profile.distinctiveFeatures)
  )

  def renderStats(char: Character): Html[Msg] =
    div(style(CSS.`background-color`("#DDDDDD")))(
      table(
        tr(
          th(
            button(
              styles(CSS.`font-weight`("bold")),
              onClick(SheetMsg(RollBrawn))
            )("Brawn")
          ),
          th(
            button(
              styles(CSS.`font-weight`("bold")),
              onClick(SheetMsg(RollAgility))
            )("Agility")
          ),
          th(
            button(
              styles(CSS.`font-weight`("bold")),
              onClick(SheetMsg(RollWits))
            )("Wits")
          ),
          th(
            button(
              styles(CSS.`font-weight`("bold")),
              onClick(SheetMsg(RollPresence))
            )("Presence")
          )
        ),
        tr(
          td(statPoolInput(Brawn)(char)),
          td(statPoolInput(Agility)(char)),
          td(statPoolInput(Wits)(char)),
          td(statPoolInput(Presence)(char))
        ),
        tr(
          td(markedInput(Brawn)(char)),
          td(markedInput(Agility)(char)),
          td(markedInput(Wits)(char)),
          td(markedInput(Presence)(char))
        )
      ),
      table(
        tr(
          td("Bloodied"),
          td(charCheckboxInput(Bloodied)(char))
        ),
        tr(
          td("Rattled"),
          td(charCheckboxInput(Rattled)(char))
        )
      )
    )

  def renderStoryAndSpark(story: Story, spark: Spark): Html[Msg] = div(
    table(
      tr(
        td(em("STORY")) +: button(
          disabled(story.toInt <= 0),
          onClick(SheetMsg(ValueEditCommand(story.spendStory, story, StoryLoc)))
        )("-") +:
          createAndFillCheckboxes(
            story.toInt,
            maxStory.toInt - story.toInt
          ).map(b => td(b)) :+ button(
            disabled(story.toInt >= maxStory.toInt),
            onClick(
              SheetMsg(ValueEditCommand(story.gainStory, story, StoryLoc))
            )
          )(
            "+"
          )
      ),
      tr(
        td(em("SPARK")) +: button(
          disabled(spark.toInt <= 0),
          onClick(SheetMsg(ValueEditCommand(spark.spendSpark, spark, SparkLoc)))
        )("-") +:
          createAndFillCheckboxes(
            spark.toInt,
            maxSpark.toInt - spark.toInt
          ).map(b => td(b)) :+ button(
            disabled(spark.toInt >= maxSpark.toInt),
            onClick(
              SheetMsg(ValueEditCommand(spark.gainSpark, spark, SparkLoc))
            )
          )(
            "+"
          )
      )
    )
  )

  def displayCondition(condition: Condition): String =
    condition.name.getOrElse("")

  def createAndFillCheckboxes(filled: Int, empty: Int): List[Html[Msg]] =
    Range(0, filled + empty)
      .map(i =>
        input(
          `type`    := "checkbox",
          `checked` := i < filled,
          onClick(Msg.NoOpMsg)
        )
      )
      .toList

  def renderCharacterDetails(char: Character): Html[Msg] = div(
    h2("Character Details"),
    table(
      tr(
        th("Background"),
        th("Wise"),
        th("Wise"),
        th("Wise")
      ),
      renderBackgroundRows(char, BackgroundLoc1),
      renderBackgroundRows(char, BackgroundLoc2)
    ),
    div(styles(CSS.`display`("flex"), CSS.`gap`("10px")))(
      renderTraits(char.details.traits),
      renderDesires(char.details.desires)
    )
  )

  def renderExperience(exp: Experience): Html[Msg] =
    div(styles(CSS.`background-color`("#DDDDDD")))(
      h2("Experience"),
      b("Each session, take 1 XP."),
      renderExperienceBlocks(exp)
    )

  def renderExperienceBlocks(exp: Experience): Html[Msg] =
    renderExperienceBlocks(exp, List(1, 2, 3, 4, 5, 6, 7), List(Nil))

  @tailrec
  def renderExperienceBlocks(
      exp: Experience,
      breakpoints: List[Int],
      rows: List[List[Html[Msg]]]
  ): Html[Msg] = breakpoints match
    case b :: bs =>
      val (row, finishedRows) = (rows.head, rows.tail)
      val numCreated          = rows.flatten.size
      val newCheckbox =
        input(
          `type`    := "checkbox",
          `checked` := numCreated < exp.toInt,
          `value`   := numCreated.toString,
          onClick(handleChangeFor(ExpLoc)(exp, (numCreated + 1).toInt.xp))
        )
      val newRow = row :+ newCheckbox
      if newRow.size >= b then
        renderExperienceBlocks(
          exp,
          bs,
          List() :: (newRow :: finishedRows)
        )
      else
        renderExperienceBlocks(
          exp,
          breakpoints,
          newRow :: finishedRows
        )
    case Nil => div(rows.reverse.map(div(_)))

}
