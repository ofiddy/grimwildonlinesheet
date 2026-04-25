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
  ): Html[Msg] = div(
    h2("Story Arcs"),
    p("Finish or move on from an arc: take spark"),
    prefilledOrCustomSelector(char, "Group Arc", GroupArcLoc),
    prefilledOrCustomSelector(char, "Character Arc 1", CharArcLoc1),
    prefilledOrCustomSelector(char, "Character Arc 2", CharArcLoc2)
  )
}
