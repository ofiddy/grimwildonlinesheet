package ftg.page.elems

import ftg.Character.PremadeArcs.allArcs
import ftg.Character.StoryArc
import ftg.command.CharacterLoc.CharArcLoc1
import ftg.command.CharacterLoc.CharArcLoc2
import ftg.command.CharacterLoc.Loc
import ftg.command.CharacterLoc.StoryArcLoc
import ftg.page.Msg
import ftg.page.elems.SheetInputs.handleChangeFor
import tyrian.Html
import tyrian.Html._

object StoryArcsInput {
  def renderStoryArcs(
      groupArc: Option[StoryArc],
      charArc: (Option[StoryArc], Option[StoryArc])
  ): Html[Msg] = div(
    h2("Story Arcs"),
    p("Finish or move on from an arc: take spark"),
    table(
      tr(
        td(b("Story Arc")),
        td(storyArcSelect(groupArc, StoryArcLoc))
      ),
      tr(
        td(b("Character Arc 1")),
        td(storyArcSelect(charArc._1, CharArcLoc1))
      ),
      tr(
        td(b("Character Arc 2")),
        td(storyArcSelect(charArc._2, CharArcLoc2))
      )
    )
  )

  def storyArcSelect(
      current: Option[StoryArc],
      loc: Loc[Option[StoryArc]]
  ): Html[Msg] = {
    val arcsMap = (("none", None) :: allArcs.map(a => (a.label, Some(a)))).toMap

    select(onInput(s => handleChangeFor(loc)(current, arcsMap(s))))(
      option(`value` := "none", selected := None == current)("") ::
        allArcs.map(a =>
          option(`value` := a.label, selected := Some(a) == current)(a.label)
        )
    )
  }
}
