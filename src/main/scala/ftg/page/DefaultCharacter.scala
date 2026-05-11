package ftg.page

import ftg.Character.Background
import ftg.Character.BodyStats
import ftg.Character.Bond
import ftg.Character.CharacterBaseStats
import ftg.Character.CharacterClass
import ftg.Character.CharacterDesire.DesireSection
import ftg.Character.CharacterDesire.PremadeDesire
import ftg.Character.CharacterDetails
import ftg.Character.CharacterName._
import ftg.Character.CharacterProfile
import ftg.Character.CharacterTrait.PremadeTrait
import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.DistinctiveFeatures.intoDistinctiveFeatures
import ftg.Character.Experience._
import ftg.Character.MentalStats
import ftg.Character.PlayerName._
import ftg.Character.PremadeArcs.growthArcs
import ftg.Character.PremadeArcs.struggleArcs
import ftg.Character.PremadeBond
import ftg.Character.PremadeBonds.premadeLeftBonds
import ftg.Character.PremadeBonds.premadeRightBonds
import ftg.Character.Spark
import ftg.Character.Story
import ftg.Character.Wise._
import ftg.Character.{Character => Character}
import ftg.Talent.TalentADT.BardsongTalent
import ftg.Talent.TalentADT.FriendlyFaceTalent

object DefaultCharacter {
  val detherilStarren = Character(
    CharacterProfile(
      "Detheril Starren".intoCharName,
      "Lucy Holderton".intoPlayerName,
      "Blue jacket, No shirt, Left peg-leg, Extremely old, Huge beard".intoDistinctiveFeatures
    ),
    CharacterBaseStats(
      BodyStats withBrawn 2 withAgility 3,
      MentalStats withWits 2 withPresence 1
    ),
    Nil,
    Story.startingStory,
    Spark.startingSpark,
    CharacterDetails(
      (
        Background(
          "Sailor",
          ("navigation".wise, "folklore".wise, "naval law".wise)
        ),
        Background(
          "Old Soldier",
          ("first aid".wise, "repair".wise, "tactics".wise)
        )
      ),
      TraitSection(
        (Some(PremadeTrait("Brave")), Some(PremadeTrait("Stubborn"))),
        Some(PremadeTrait("Honest"))
      ),
      DesireSection(
        (Some(PremadeDesire("Harmony")), Some(PremadeDesire("Thrills"))),
        Some(PremadeDesire("Power"))
      )
    ),
    List(
      Bond(
        "Sora".intoCharName,
        PremadeBond(premadeLeftBonds(5), premadeRightBonds(1))
      ),
      Bond(
        "Malcolm".intoCharName,
        PremadeBond(premadeLeftBonds(0), premadeRightBonds(3))
      ),
      Bond(
        "Aust".intoCharName,
        PremadeBond(premadeLeftBonds(2), premadeRightBonds(4))
      ),
      Bond(
        "Glynrel".intoCharName,
        PremadeBond(premadeLeftBonds(1), premadeRightBonds(1))
      ),
      Bond(
        "Andromeda".intoCharName,
        PremadeBond(premadeLeftBonds(4), premadeRightBonds(0))
      ),
      Bond(
        "Stonarta".intoCharName,
        PremadeBond(premadeLeftBonds(2), premadeRightBonds(3))
      )
    ),
    Some(struggleArcs(5)),
    (Some(growthArcs(5)), None),
    8.xp,
    CharacterClass.Fighter,
    List(BardsongTalent(2, 2), FriendlyFaceTalent),
    ""
  )

  val blankChar = Character(
    CharacterProfile(
      "".intoCharName,
      "".intoPlayerName,
      "".intoDistinctiveFeatures
    ),
    CharacterBaseStats(
      BodyStats withBrawn 1 withAgility 1,
      MentalStats withWits 1 withPresence 1
    ),
    Nil,
    Story.startingStory,
    Spark.startingSpark,
    CharacterDetails(
      (
        Background(
          None,
          (None, None, None)
        ),
        Background(
          None,
          (None, None, None)
        )
      ),
      TraitSection(
        (None, None),
        None
      ),
      DesireSection(
        (None, None),
        None
      )
    ),
    Nil,
    None,
    (None, None),
    1.xp,
    CharacterClass.Fighter,
    Nil,
    ""
  )
}
