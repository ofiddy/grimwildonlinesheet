package ftg.page.elems

import tyrian.Html
import tyrian.Html._
import ftg.Character.StoryArc
import ftg.page.Msg
import ftg.page.elems.SheetInputs.handleChangeFor
import ftg.Character.PremadeArcs.allArcs
import ftg.command.CharacterLoc.Loc
import ftg.command.CharacterLoc.StoryArcLoc
import ftg.command.CharacterLoc.CharArcLoc

object StoryArcsInput {
  def renderStoryArcs(
      groupArc: Option[StoryArc],
      charArc: Option[StoryArc]
  ): Html[Msg] = div(
    h2("Story Arcs"),
    p("Finish or move on from an arc: take spark"),
    table(
      tr(
        td(b("Story Arc")),
        td(storyArcSelect(groupArc, StoryArcLoc))
      ),
      tr(
        td(b("Character Arc")),
        td(storyArcSelect(charArc, CharArcLoc))
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
