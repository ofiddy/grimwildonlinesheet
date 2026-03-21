package ftg.Character

opaque type Story = Int

object Story {
  extension (s: Story) {
    def gainStory: Story =
      if s >= 0 && s < 2 then s + 1 else 2

    def spendStory: Option[Story] =
      if s <= 0 then None else Some(s - 1)

    def toInt: Int = s
  }

  def startingStory: Story = 2
}
