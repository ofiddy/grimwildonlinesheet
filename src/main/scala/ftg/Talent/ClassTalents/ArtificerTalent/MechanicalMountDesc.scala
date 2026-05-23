package ftg.Talent.ClassTalents.ArtificerTalent

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.md
import ftg.Talent.TalentADT.MechanicalMountTalent
import ftg.Talent.TalentADT.MarkableSelectable

case object MechanicalMountDesc extends TalentDescriptor {

  override def name: String = "Mechanical Mount"

  override def desc: Markdown =
    "You have a small mechanical vehicle. It has three features: *all-terrain*—*armored*—*burrowing*—*grappling hook*—*submergible*—*turbo boost*. You can activate each perk once per session to pull off a ***potent feat of piloting***. It also has one drawback: *conspicuous*—*horrifying*—*slow*—*unreliable*.".md

  override def apply(c: ftg.Character.Character): Talent =
    MechanicalMountTalent(
      (
        MarkableSelectable(false, None),
        MarkableSelectable(false, None),
        MarkableSelectable(false, None)
      ),
      None
    )

  type MechanicalMountFeatures =
    (MarkableSelectable, MarkableSelectable, MarkableSelectable)

  val mechanicalMountFeatureNames = List(
    "All-terrain",
    "Armored",
    "Burrowing",
    "Grappling Hook",
    "Submergible",
    "Turbo Boost"
  )

  val mechanicalMountDrawbackNames = List(
    "Conspicuous",
    "Horrifying",
    "Slow",
    "Unreliable"
  )
}
