package ftg.page.talentRenderers

import ftg.Talent.TalentADT.ArtificerTalent
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.Msg
import tyrian.Html
import ftg.Talent.TalentADT._
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import monocle.syntax.all.focus

object ArtificerTalentRender {
  def artificerTalentRender(
      t: ArtificerTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = t match
    case t: IngenuityTalent  => acc withWidget PushBox(t.focus(_.marked))
    case t: AnchorshotTalent => acc withWidget Pool("ANCHOR", t.focus(_.pool))
    case t: AutomatonsTalent =>
      acc withWidget Pool("Helper", t.focus(_.pool1)) withWidget Pool(
        "Helper",
        t.focus(_.pool2)
      ) withWidget Pool("Helper", t.focus(_.pool3))
    case t: DoubleBarreledBlunderbussTalent =>
      acc withWidgets List(
        SquareBox(t.focus(_.blast), "BLAST"),
        SquareBox(t.focus(_.drill), "DRILL"),
        SquareBox(t.focus(_.inferno), "INFERNO"),
        SquareBox(t.focus(_.scatter), "SCATTER"),
        SquareBox(t.focus(_.shrapnel), "SHRAPNEL"),
        SquareBox(t.focus(_.tangler), "TANGLER")
      )
    case t: GrenadesTalent => acc withWidget Pool("GRENADES", t.focus(_.pool))

}
