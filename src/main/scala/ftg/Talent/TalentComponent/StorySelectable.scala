package ftg.Talent.TalentComponent

import ftg.Character.{Character => Character}
import ftg.Talent.TalentComponents.TalentComponent

final case class StorySelectable(isFull: Boolean) extends TalentComponent {
  override def initFromCharState(char: Character): TalentComponent =
    StorySelectable(true)

  override def resetAtSessionStart(char: Character): TalentComponent =
    StorySelectable(true)
}
