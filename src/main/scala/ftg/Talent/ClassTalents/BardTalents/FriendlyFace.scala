package ftg.Talent.ClassTalents.BardTalents

import ftg.Talent.Talent
import ftg.Talent.Markdown
import ftg.Talent.md

object FriendlyFace extends Talent {
  override def freshFromChar(c: ftg.Character.Character): Talent = FriendlyFace
  override def name: String = "Friendly Face"
  override def desc: Markdown =
    "In any new town or district of a city you go to, you can always: *know someone useful*—*quickly make a friend*—*be recognized by a fan*. They’ll gladly do you a reasonable favor. **Take spark** if you promise to pay them back.".md
}
