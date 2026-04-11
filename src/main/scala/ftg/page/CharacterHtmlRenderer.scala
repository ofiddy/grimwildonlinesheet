package ftg.page

import ftg.Character.Bond
import ftg.Character.CharacterName._
import ftg.Character.CharacterProfile
import ftg.Character.Condition
import ftg.Character.Experience
import ftg.Character.Spark
import ftg.Character.Story
import ftg.Character.Story.startingStory
import ftg.Character.Story.toInt
import ftg.Character.StoryArc
import ftg.Character.{Character => Character}
import ftg.command.CharacterLoc.HarmLocs.Bloodied
import ftg.command.CharacterLoc.HarmLocs.Rattled
import ftg.command.CharacterLoc.StatLocs._
import ftg.command.RollStatCommand.RollAgility
import ftg.command.RollStatCommand.RollBrawn
import ftg.command.RollStatCommand.RollPresence
import ftg.command.RollStatCommand.RollWits
import ftg.page.Msg.SheetMsg
import ftg.page.elems.SheetInputs.charCheckboxInput
import ftg.page.elems.SheetInputs.charNameInput
import ftg.page.elems.SheetInputs.distinctiveFeaturesInput
import ftg.page.elems.SheetInputs.markedInput
import ftg.page.elems.SheetInputs.playerNameInput
import ftg.page.elems.SheetInputs.statPoolInput
import tyrian.Attribute
import tyrian.CSS
import tyrian.Html
import tyrian.Html._

import scala.Range
import scala.annotation.tailrec
import ftg.page.elems.BackgroundsElements.renderBackgroundRows
import ftg.command.CharacterLoc.BackgroundLocs.*
import ftg.page.elems.ConditionsInput.renderConditions

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

  def displayCondition(condition: Condition): String =
    condition.name.getOrElse("")

  def createAndFillCheckboxes[T](filled: Int, empty: Int): List[Html[T]] =
    Range(0, filled + empty)
      .map(i => input(`type` := "checkbox", `checked` := i < filled))
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
      div(
        h3("Traits") +:
          char.details.traits.twoYouAre.map(t => p(s"✔️ ${t.label}")) :+
          p(char.details.traits.oneYouArent match
            case None    => "❌"
            case Some(t) => s"❌ ${t.label}")
      ),
      div(
        h3("Desires") +:
          char.details.desires.twoYouWant.map(t => p(s"✔️ ${t.label}")) :+
          p(char.details.desires.oneYouDont match
            case None    => "❌"
            case Some(t) => s"❌ ${t.label}")
      )
    )
  )

  def renderBonds[T](bonds: List[Bond]): Html[T] = div(
    h2("Bonds"),
    ul(
      li("Change a Bond: The other PC takes spark"),
      li("Quarrel: Both take spark")
    ),
    table(
      tr(
        th("PC"),
        th("Bond")
      ) :: bonds.map(renderBond)
    )
  )

  def renderBond[T](bond: Bond): Html[T] = tr(
    td(bond.pcName.label),
    td(bond.bondDesc.label)
  )

  def renderStoryArcs[T](groupArc: StoryArc, charArc: StoryArc): Html[T] = div(
    h2("Story Arcs"),
    p("Finish or move on from an arc: take spark"),
    table(
      tr(
        td(b("Story Arc")),
        td(groupArc.label)
      ),
      tr(
        td(b("Character Arc")),
        td(charArc.label)
      )
    )
  )

  def renderExperience[T](exp: Experience): Html[T] =
    div(styles(CSS.`background-color`("#DDDDDD")))(
      h2("Experience"),
      b("Each session, take 1 XP."),
      renderExperienceBlocks[T](exp)
    )

  def renderExperienceBlocks[T](exp: Experience): Html[T] =
    renderExperienceBlocks(exp, List(2, 3, 4, 5, 6, 7), List(Nil))

  @tailrec
  def renderExperienceBlocks[T](
      exp: Experience,
      breakpoints: List[Int],
      rows: List[List[Html[T]]]
  ): Html[T] = breakpoints match
    case b :: bs =>
      val (row, finishedRows) = (rows.head, rows.tail)
      val numCreated          = rows.flatten.size
      val newCheckbox =
        input(
          `type`    := "checkbox",
          `checked` := numCreated < exp.toInt,
          Attribute("num", numCreated.toString)
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
