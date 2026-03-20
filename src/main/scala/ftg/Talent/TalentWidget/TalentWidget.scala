package ftg.Talent.TalentWidgets

import ftg.Talent.TalentWidgets.TalentComponents.TalentComponent

final case class TalentWidget(
    label: Option[String],
    component: TalentComponent
)

object TalentWidget {
  def apply(s: String, c: TalentComponent): TalentWidget =
    TalentWidget(Some(s), c)
  def apply(c: TalentComponent): TalentWidget = TalentWidget(None, c)
}
