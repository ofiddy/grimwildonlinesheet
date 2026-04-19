package ftg.Character

import upickle.default.ReadWriter

sealed trait StoryArc derives ReadWriter {
  def label: String
}
final case class AmbitionArc(label: String) extends StoryArc
final case class StruggleArc(label: String) extends StoryArc
final case class GrowthArc(label: String)   extends StoryArc

object PremadeArcs {

  val ambitionArcs: List[AmbitionArc] =
    List(
      "Build a Reputation",
      "Explore the World",
      "Finish the Mission",
      "Make Things Right",
      "Satisfy Desires",
      "Uncover the Truth"
    ).map(AmbitionArc.apply)

  val struggleArcs: List[StruggleArc] =
    List(
      "Come Unraveled",
      "Doubt Convictions",
      "Feed My Vices",
      "Flirt With Betrayal",
      "Keep a Secret",
      "Survive the Storm"
    ).map(StruggleArc.apply)

  val growthArcs: List[GrowthArc] =
    List(
      "Embrace Change",
      "Escape My Past",
      "Find Belonging",
      "Just Enjoy Life",
      "Prove Myself",
      "Settle Debts"
    ).map(GrowthArc.apply)

  val allArcs = ambitionArcs ++ struggleArcs ++ growthArcs
}
