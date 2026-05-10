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
import ftg.page.elems.SheetInputs.charNameInput
import ftg.page.elems.SheetInputs.distinctiveFeaturesInput
import ftg.page.elems.SheetInputs.handleChangeFor
import ftg.page.elems.SheetInputs.markedInput
import ftg.page.elems.SheetInputs.playerNameInput
import ftg.page.elems.SheetInputs.statPoolInput
import ftg.page.elems.StoryArcsInput.renderStoryArcs
import ftg.page.elems.TraitsDesiresInput.renderDesires
import ftg.page.elems.TraitsDesiresInput.renderTraits
import tyrian.Html
import tyrian.Html._
import scala.Range
import scala.annotation.tailrec
import ftg.page.elems.SheetInputs.charHarmInput
import ftg.page.talentRenderers.renderTalent

object CharacterHtmlRenderer {
  def renderCharacter(char: Character): Html[Msg] = div(
    h1("Grimwild Online Character Sheet"),
    div(id := "page-cards-horizontal", cls := "no-grow")(
      div(cls := "sheet-card")(
        renderProfile(char.profile),
        div(cls := "horizontal no-grow")(
          renderStats(char),
          div(cls := "vertical")(
            renderStoryAndSpark(char.story, char.spark),
            renderConditions(char.conditions)
          )
        ),
        renderCharacterDetails(char),
        renderBonds(char.bonds),
        div(cls := "horizontal no-grow")(
          renderStoryArcs(char),
          renderExperience(char.experience)
        )
      ),
      div(cls := "sheet-card shaded-area card-section")(
        div(cls := "card-section-inner")(
          char.talents.map(renderTalent(_, char))
        )
      )
    )
  )

  def renderProfile(profile: CharacterProfile): Html[Msg] =
    div(
      cls := "shaded-area card-section horizontal card-section-inner",
      id  := "profile-section-card"
    )(
      div(cls := "vertical", id := "name-entry-section")(
        div(cls := "white-card-entry", id := "name-entry")(
          p(cls := "white-card-entry")("NAME"),
          charNameInput(profile.characterName)
        ),
        div(cls := "white-card-entry")(
          p(cls := "white-card-entry")("PLAYER"),
          playerNameInput(profile.playerName)
        )
      ),
      div(cls := "white-card-entry", id := "distinctive-features-section")(
        p("DISTINCTIVE FEATURES"),
        distinctiveFeaturesInput(profile.distinctiveFeatures)
      )
    )

  def renderStats(char: Character): Html[Msg] =
    div(cls := "shaded-area card-section", id := "stats-section-card")(
      table(id := "stats-section-table")(
        tr(
          th(
            button(
              cls := "stat-label",
              onClick(SheetMsg(RollBrawn))
            )("Brawn")
          ),
          th(
            button(
              cls := "stat-label",
              onClick(SheetMsg(RollAgility))
            )("Agility")
          ),
          th(
            button(
              cls := "stat-label",
              onClick(SheetMsg(RollWits))
            )("Wits")
          ),
          th(
            button(
              cls := "stat-label",
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
        ),
        tr(
          charHarmInput(Bloodied)(char),
          charHarmInput(Rattled)(char)
        )
      ),
      hr(id := "stats-rule"),
      div(id := "post-stats-info")(
        p(
          b("MARK"),
          span(": +1T TO STAT, THEN CLEARS | "),
          b("HARM"),
          span(": +1T TO ALL ROLLS")
        ),
        p(
          b("CRITICAL"),
          span(": GREATER EFFECT (DROP 1)—SECONDARY EFFECT—SETUP")
        )
      )
    )

  def renderStoryAndSpark(story: Story, spark: Spark): Html[Msg] = div(
    cls := "horizontal"
  )(
    div(cls := "shaded-area story-card")(
      b(cls := "story-card-label")("STORY"),
      hr,
      div(cls := "horizontal story-buttons")(
        button(
          disabled(story.toInt <= 0),
          onClick(
            SheetMsg(ValueEditCommand(story.spendStory, story, StoryLoc))
          ),
          cls := "story-crementer"
        )("–") +:
          createAndFillCheckboxes(
            story.toInt,
            maxStory.toInt - story.toInt,
            "story-checkbox"
          ) :+ button(
            disabled(story.toInt >= maxStory.toInt),
            onClick(
              SheetMsg(ValueEditCommand(story.gainStory, story, StoryLoc))
            ),
            cls := "story-crementer"
          )(
            "+"
          )
      )
    ),
    div(cls := "shaded-area story-card")(
      b(cls := "story-card-label")("SPARK"),
      hr,
      div(cls := "horizontal story-buttons")(
        button(
          disabled(spark.toInt <= 0),
          onClick(
            SheetMsg(ValueEditCommand(spark.spendSpark, spark, SparkLoc))
          ),
          cls := "story-crementer"
        )("–") +:
          createAndFillCheckboxes(
            spark.toInt,
            maxSpark.toInt - spark.toInt,
            "story-checkbox--spark"
          ) :+ button(
            disabled(spark.toInt >= maxSpark.toInt),
            onClick(
              SheetMsg(ValueEditCommand(spark.gainSpark, spark, SparkLoc))
            ),
            cls := "story-crementer"
          )(
            "+"
          )
      )
    )
  )

  def displayCondition(condition: Condition): String =
    condition.name.getOrElse("")

  def createAndFillCheckboxes(
      filled: Int,
      empty: Int,
      clsExt: String
  ): List[Html[Msg]] =
    Range(0, filled + empty)
      .map(i =>
        input(
          `type`    := "checkbox",
          `checked` := i < filled,
          onClick(Msg.NoOpMsg),
          `cls` := clsExt
        )
      )
      .toList

  def renderCharacterDetails(char: Character): Html[Msg] =
    div(cls := "shaded-area card-section")(
      div(cls := "card-black-header")(
        h2("CHARACTER DETAILS"),
        span("INTRODUCE A TANGLE: TAKE SPARK")
      ),
      div(cls := "card-section-inner")(
        div(cls := "white-card-table-wrapper")(
          table(cls := "white-card-table")(
            tr(cls := "white-table-header")(
              th("BACKGROUNDS"),
              th(`span` := 3, id := "wises-header")("WISES")
            ),
            renderBackgroundRows(char, BackgroundLoc1),
            renderBackgroundRows(char, BackgroundLoc2)
          )
        ),
        div(id := "traits-desires")(
          renderTraits(char),
          renderDesires(char)
        )
      )
    )

  def renderExperience(exp: Experience): Html[Msg] =
    div(cls := "shaded-area card-section")(
      div(cls := "card-black-header")(
        h2("EXPERIENCE")
      ),
      div(id := "xp-section-inner")(
        b(cls := "xp-subtitle")("Each session, take 1 XP."),
        renderExperienceBlocks(exp),
        b(cls := "xp-subtitle")("Each full row, take a new talent.")
      )
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
          cls       := "xp-box",
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
    case Nil => div(id := "xp-block-section")(rows.reverse.map(div(_)))

}
