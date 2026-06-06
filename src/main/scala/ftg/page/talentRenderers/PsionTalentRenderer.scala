package ftg.page.talentRenderers

import ftg.page.Msg
import tyrian.Html
import ftg.page.talentRenderers.FluentTalentRenderers.TalentEditBuilder
import ftg.page.talentRenderers.FluentTalentRenderers._
import ftg.Talent.TalentADT._
import monocle.syntax.all.focus
import ftg.page.talentRenderers.FluentTalentRenderers.fluentTalent
import ftg.Talent.ClassTalents.PsionTalents.AwakenedMindDesc
import ftg.Character.LevelGrowth.`and every 2 levels`
import ftg.Character.CharacterBaseStats
import ftg.Character.StatPool
import monocle.AppliedLens

object PsionTalentRenderer {
  def psionTalentRenderer(
      t: PsionTalent,
      c: ftg.Character.Character,
      acc: Html[Msg]
  )(using
      TalentEditBuilder
  ): Html[Msg] = {
    given Int = c.level
    t match
      case t: AwakenedMindTalent => {
        val max         = AwakenedMindDesc(c)
        val numBastions = 2 `and every 2 levels` (_ + 1)
        acc withWidgets (MultiCheckbox("POWER", t.focus(_.power), max.power)
          :: List(
            bastionSelectable(t.focus(_.bastions._1), c),
            bastionSelectable(t.focus(_.bastions._2), c),
            bastionSelectable(t.focus(_.bastions._3), c),
            bastionSelectable(t.focus(_.bastions._4), c),
            bastionSelectable(t.focus(_.bastions._5), c)
          ).take(numBastions))
      }

      case t: DisturbedMindTalent =>
        acc withWidget SquareBox(t.focus(_.marked), "VEX")
      case t: MindSeedTalent =>
        acc withWidget SquareBox(t.focus(_.marked), "INTERRUPT")
  }

  final case class Bastion(
      label: String,
      formula: String,
      pool: CharacterBaseStats => (StatPool, StatPool)
  )
  def bastions = List(
    Bastion(
      "BIODYNAMICS",
      "B + A",
      c => (c.bodyStats.brawn, c.bodyStats.agility)
    ),
    Bastion(
      "CLAIRSENTIENCE",
      "W + P",
      c => (c.mentalStats.wits, c.mentalStats.presence)
    ),
    Bastion(
      "METACREATION",
      "B + P",
      c => (c.mentalStats.wits, c.mentalStats.presence)
    ),
    Bastion(
      "PSYCHOKINESIS",
      "B + W",
      c => (c.mentalStats.wits, c.mentalStats.presence)
    ),
    Bastion(
      "TELEPATHY",
      "W + P",
      c => (c.mentalStats.wits, c.mentalStats.presence)
    ),
    Bastion(
      "TRANSPOSITION",
      "A + W",
      c => (c.mentalStats.wits, c.mentalStats.presence)
    )
  )

  private def bastionSelectable(
      ref: AppliedLens[AwakenedMindTalent, Option[String]],
      char: ftg.Character.Character
  ): Selectable[AwakenedMindTalent] = {
    val bastionMap =
      bastions.map(b => (b.label, Some(b))).toMap.withDefault(_ => None)
    Selectable(
      ref,
      bastions.map(_.label),
      (for {
        label   <- ref.get
        bastion <- bastionMap(label)
        pools = bastion.pool(char.stats)
        total = pools._1.dice.diceRemaining + pools._2.dice.diceRemaining - 2
      } yield s"${bastion.formula} - 2 = ${total}").getOrElse("SELECT BASTION")
    )
  }
}
