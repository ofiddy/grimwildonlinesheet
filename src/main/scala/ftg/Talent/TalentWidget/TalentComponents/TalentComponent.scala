package ftg.Talent.TalentWidgets.TalentComponents

trait TalentComponent {
  def resetAtSessionStart(char: Character): TalentComponent = this
  def initFromCharState(char: Character): TalentComponent   = this
}
