package ftg.Talent.TalentWidgets.TalentComponents

import ftg.Character.{Character => Character}

trait TalentComponent {
  def resetAtSessionStart(char: Character): TalentComponent = this
  def initFromCharState(char: Character): TalentComponent   = this
}
