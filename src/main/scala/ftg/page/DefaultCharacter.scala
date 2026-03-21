package ftg.page

import ftg.Character.Character as Character
import ftg.Character.CharacterProfile
import ftg.Character.CharacterBaseStats
import ftg.Character.BodyStats
import ftg.Character.MentalStats
import ftg.Character.Story
import ftg.Character.Spark
import ftg.Character.CharacterDetails
import ftg.Character.Background
import ftg.Character.CharacterTrait.TraitSection
import ftg.Character.CharacterTrait.PremadeTrait
import ftg.Character.CharacterName.*
import ftg.Character.PlayerName.*
import ftg.Character.Wise.*
import ftg.Character.DistinctiveFeature.*
import ftg.Character.CharacterDesire.DesireSection
import ftg.Character.CharacterDesire.PremadeDesire
import ftg.Character.Bond
import ftg.Character.PremadeBond
import ftg.Character.PremadeBonds.premadeLeftBonds
import ftg.Character.PremadeBonds.premadeRightBonds
import ftg.Character.PremadeArcs.struggleArcs
import ftg.Character.PremadeArcs.growthArcs
import ftg.Character.Experience.*
import ftg.Character.CharacterClass
import ftg.Talent.ClassTalents.FighterTalents

object DefaultCharacter {
  val detherilStarren = Character(
    CharacterProfile(
      "Detheril Starren".charName,
      "Lucy Holderton".playerName,
      List(
        "Blue jacket".distinctiveFeature,
        "No shirt".distinctiveFeature,
        "Left peg-leg".distinctiveFeature,
        "Extremely old".distinctiveFeature,
        "Huge beard".distinctiveFeature
      )
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
        Some(
          Background(
            "Sailor",
            ("navigation".wise, "folklore".wise, "naval law".wise)
          )
        ),
        Some(
          Background(
            "Old Soldier",
            ("first aid".wise, "repair".wise, "tactics".wise)
          )
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
        "Sora".charName,
        PremadeBond(premadeLeftBonds(5), premadeRightBonds(1))
      ),
      Bond(
        "Malcolm".charName,
        PremadeBond(premadeLeftBonds(0), premadeRightBonds(3))
      ),
      Bond(
        "Aust".charName,
        PremadeBond(premadeLeftBonds(2), premadeRightBonds(4))
      ),
      Bond(
        "Glynrel".charName,
        PremadeBond(premadeLeftBonds(1), premadeRightBonds(1))
      ),
      Bond(
        "Andromeda".charName,
        PremadeBond(premadeLeftBonds(4), premadeRightBonds(0))
      ),
      Bond(
        "Stonarta".charName,
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
