package ftg.page.elems

import ftg.Character.PremadeArcs.allArcs
import ftg.Character.StoryArc
import ftg.page.Msg
import tyrian.Html
import tyrian.Html._
import ftg.command.CharacterLoc.StoryArcLocs._
import ftg.page.elems.inputPatterns.LargelyPrefilledSection
import ftg.Character.CustomArc
import ftg.page.elems.inputPatterns.prefilledOrCustomSelector

object StoryArcsInput {

  private given LargelyPrefilledSection[StoryArc] {

    override def extractCustomLabel(t: Option[StoryArc]): Option[String] =
      t match {
        case Some(CustomArc(l)) => Some(l)
        case _                  => None
      }

    override def sectionMap: Map[String, StoryArc] =
      allArcs.map(a => (a.label.toLowerCase, a)).toMap

    override def custom(s: String): StoryArc = CustomArc(s)
  }

  def renderStoryArcs(
      char: ftg.Character.Character
  ): Html[Msg] = div(id := "story-arcs", cls := "shaded-area card-section")(
    div(cls := "card-black-header")(
      h2("STORY ARCS"),
      span(
        "FINISH OR MOVE ON FROM AN ARC: TAKE SPARK"
      )
    ),
    div(cls := "card-section-inner")(
      table(id := "arc-table")(
        tr(id := "group-arc-row")(
          td(b("GROUP ARC: ")),
          td(prefilledOrCustomSelector(char, "", GroupArcLoc))
        ),
        tr(
          td("CHARACTER ARC: "),
          td(prefilledOrCustomSelector(char, "", CharArcLoc1))
        ),
        tr(
          td("CHARACTER ARC: "),
          td(prefilledOrCustomSelector(char, "", CharArcLoc2))
        )
      )
    )
  )
}
