package ftg.Character

import upickle.default.{ReadWriter => RW}
import ftg.util.Util.opaqueIntRW

opaque type Story = Int

object Story {
  extension (s: Story) {
    def gainStory: Story =
      if s >= 0 && s < 2 then s + 1 else 2

    def spendStory: Story =
      if s <= 0 then 0 else s - 1

    def toInt: Int = s
  }

  def startingStory: Story = 2
  def maxStory: Story      = 2

  given RW[Story] = opaqueIntRW[Story](identity, identity)
}
