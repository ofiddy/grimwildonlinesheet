package ftg.Character.SelectableDetails

sealed trait SelectableDetail[T] {
  def label: String
}

final case class PremadeDetail[T](label: String) extends SelectableDetail[T]
final case class CustomDetail[T](label: String)  extends SelectableDetail[T]

final case class DetailSystem[T](premadeDetails: List[SelectableDetail[T]]) {
  type Premade = PremadeDetail[T]
  type Custom  = CustomDetail[T]
  type Detail  = SelectableDetail[T]
}

def produceDetailSystem[T](premades: List[String]) =
  DetailSystem[T](premades.map(PremadeDetail[T](_)))
