package ftg.Talent.ClassTalents.MonkTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.PrimordialForcesTalent
import ftg.Talent.TalentADT.PrimordialForcesIITalent

object PrimordialForcesDesc extends TalentDescriptor {

  override def name: String = "Primordial Forces"

  override def desc: Markdown =
    "Choose an element: *Air*—*Earth*—*Fire*—*Water*. You can cast cantrips with it, useful as *set dressing* and *magic utility*. On a critical, charge it (*mark its box*). Spend it to pull off a ***potent feat of force or movement*** empowered by the element.".md

  override def apply(c: ftg.Character.Character): Talent =
    PrimordialForcesTalent(None, false)

}

object PrimordialForcesIIDesc extends TalentDescriptor {

  override def name: String = "Primordial Forces (II)"

  override def desc: Markdown =
    "Choose an element: *Air*—*Earth*—*Fire*—*Water*. You can cast cantrips with it, useful as *set dressing* and *magic utility*. On a critical, charge it (*mark its box*). Spend it to pull off a ***potent feat of force or movement*** empowered by the element.".md

  override def apply(c: ftg.Character.Character): Talent =
    PrimordialForcesIITalent(None, false)

}
