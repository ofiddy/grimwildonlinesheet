package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Character.LevelGrowth.`and every 3 levels`

object FighterTalentRenderer {
  def fighterTalentRenderer(
      t: FighterTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given Int = c.level
    t match
      case t: WeaponMasteryTalent => {
        val masteryDice = 3 `and every 3 levels` (_ + 1)
        acc withWidget TextEntry(s"MASTERY: ${masteryDice}", t.focus(_.style))
      }
  }

}
