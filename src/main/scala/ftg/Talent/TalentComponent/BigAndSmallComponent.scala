package ftg.Talent.TalentComponent

import ftg.Talent.TalentComponents.TalentComponent

final case class BigAndSmallComponent(
    smallIsChecked: Boolean,
    bigIsChecked: Boolean
) extends TalentComponent
