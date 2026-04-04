package ftg.Talent.ClassTalents

import ftg.DicePool.DicePool
import ftg.Talent.Talent
import ftg.Talent.TalentWidget.TalentComponents.FluentCheckboxes.Checkbox
import ftg.Talent.TalentWidget.TalentComponents.FluentDicePoolComponent.d
import ftg.Talent.TalentWidgets.TalentWidget
import ftg.Talent.md

object FighterTalents {
  val weaponMastery = Talent(
    "Weapon Mastery",
    "Choose a fighting style that you have mastered: *brawling*—*dual-wielding*—*one-handed weapons*—*ranged weapons*—*thrown weapons*—*two-handed weapons*. You have a mastery die, a special d6. When you fight in your style, take +1d (*the mastery die*) on the roll. If the mastery die is a 6, it counts as a critical. If it’s already a critical, **take spark**. [Growth: 3 levels/+1d mastery die]".md,
    Nil
  )

  val arcaneTraining = Talent(
    "Arcane Training",
    "You have spellcasting as the Spellcraft talent. You roll Wits and can cast 3 spells and 1 potent spell per session. You know 3 spell theorems, created with the Spell Crucible, and can learn new spells from scrolls.".md,
    List(
      TalentWidget("Spells", Checkbox withMax 3 withStart 0),
      TalentWidget("Potent", Checkbox withMax 1 withStart 0)
    )
  )

  val bulwark = Talent(
    "Bulwark",
    "Each session, you have a 3d Bulwark pool from armor or other defenses. When you get bloodied or dropped from physical damage, roll the pool. If dice remain, ignore the damage.".md,
    List(TalentWidget("Bulwark", 3 d DicePool))
  )

  val control = Talent(
    "Control",
    "You can **push yourself** to declare a zone that enemies cannot cross without dealing with you. If they attempt to move past you, you can ***interrupt*** them. On a perfect, **take spark**.".md,
    List(TalentWidget("Push", Checkbox withMax 1 withStart 0))
  )

  val gotYourBack = Talent(
    "Got Your Back",
    "You can **push yourself** to assist an ally after they roll a grim. The ally can also roll 1d. On a perfect, you both **take spark**.".md,
    List(TalentWidget("Push", Checkbox withMax 1 withStart 0))
  )

  val measuredTones = Talent(
    "Measured Tones",
    "When you speak in measured tones, people always stop and listen—declare ***low risk*** if desired. Unless completely surprised, you can **push yourself** to ***interrupt*** an impact move initiating aggression. If you change their mind, **take spark**.".md,
    List(TalentWidget("Push", Checkbox withMax 1 withStart 0))
  )

  val swiftRecovery = Talent(
    "Swift Recovery",
    "On a perfect roll with an unmarked stat, clear a mark on another stat.".md,
    Nil
  )

  val tactician = Talent(
    "Tactician",
    "During an intense action sequence, you can **push yourself** to tag 3 scene elements right away and 1 later in the sequence. When an ally interacts with a tagged element, you assist without risk on the roll. If no roll is needed, they **take spark**.".md,
    List(TalentWidget("Push", Checkbox withMax 1 withStart 0))
  )
}
