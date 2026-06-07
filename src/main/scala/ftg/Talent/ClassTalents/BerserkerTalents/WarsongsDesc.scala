package ftg.Talent.ClassTalents.BerserkerTalents

import ftg.Talent.TalentDescriptor
import ftg.Talent.TalentADT.Talent
import ftg.Talent.Markdown
import ftg.Talent.TalentADT.WarsongsTalent
import ftg.Talent.md

case object WarsongsDesc extends TalentDescriptor {

  override def name: String = "Warsongs"

  override def desc: Markdown =
    "Each session, you can sing **2 bardsongs**. Write down the composition of the only 3 songs you know.".md

  override def apply(c: ftg.Character.Character): Talent =
    WarsongsTalent(0, (None, None, None))

}
