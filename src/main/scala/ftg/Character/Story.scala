package ftg.Character

opaque type Story = Int

extension (s: Story) {
  def gainStory: Story =
    if s >= 0 && s < 2 then s + 1 else 2

  def spendStory: Option[Story] =
    if s <= 0 then None else Some(s - 1)
}

object Story {
  def startingStory: Story = 2
}
