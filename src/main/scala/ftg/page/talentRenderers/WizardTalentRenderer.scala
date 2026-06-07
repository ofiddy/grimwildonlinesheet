package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.WizardTalents.SpellcraftDesc
import ftg.Character.LevelGrowth.`and every 2 levels`

object WizardTalentRenderer {
  def wizardTalentRenderer(
      t: WizardTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given Int = c.level
    t match
      case t: SpellcraftTalent => {
        val max = SpellcraftDesc(c)
        acc withWidgets List(
          MultiCheckbox("SPELLS", t.focus(_.spells), max.spells),
          MultiCheckbox(
            "OF WHICH POTENT",
            t.focus(_.potentSpells),
            max.potentSpells
          )
        ) withFooter TextEntryFooter(
          "THEOREMS",
          List(
            t.focus(_.theorems._1),
            t.focus(_.theorems._2),
            t.focus(_.theorems._3),
            t.focus(_.theorems._4),
            t.focus(_.theorems._5),
            t.focus(_.theorems._6),
            t.focus(_.theorems._7)
          ).take(4 `and every 2 levels` (_ + 1))
        )
      }
  }
}
