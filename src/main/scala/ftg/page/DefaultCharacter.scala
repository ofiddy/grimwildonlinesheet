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
import ftg.Talent.ClassTalents.FighterTalents

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
        List(PremadeTrait("Brave"), PremadeTrait("Stubborn")),
        Some(PremadeTrait("Honest"))
      ),
      DesireSection(
        List(PremadeDesire("Harmony"), PremadeDesire("Thrills")),
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
    struggleArcs(5),
    growthArcs(5),
    8.xp,
    CharacterClass.Fighter,
    List(
      FighterTalents.weaponMastery,
      FighterTalents.bulwark,
      FighterTalents.gotYourBack,
      FighterTalents.measuredTones
    ),
    ""
  )
}
