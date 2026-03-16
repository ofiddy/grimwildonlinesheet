package ftg.Talent.TalentWidget.TalentComponents

import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent

final case class StorySelectable(isFull: Boolean) extends TalentComponent {
  override def initFromCharState(char: Character): TalentComponent =
    StorySelectable(true)

  override def resetAtSessionStart(char: Character): TalentComponent =
    StorySelectable(true)
}
