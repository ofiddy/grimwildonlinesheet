package ftg.Talent.ClassTalents

import ftg.DicePool.DicePool
import ftg.Talent.Talent
import ftg.Talent.TalentWidget.TalentComponents.DicePoolComponent
import ftg.Talent.TalentWidget.TalentComponents.FluentCheckboxes.Checkbox
import ftg.Talent.TalentWidget.TalentComponents.StorySelectable
import ftg.Talent.TalentWidgets.TalentWidget
import ftg.Talent.md

object WizardTalents {
  val spellcraft = Talent(
    "Spellcraft",
    "You have spellcasting ability. You roll Wits to cast and the spell theorem you are casting serves as its touchstone. Each session, you can cast 4 spells and 2 potent spells. You choose which spell theorem to use when you cast. You can learn new spell theorems from studying and experimenting with scrolls. This consumes the scroll. [Growth: 2 levels/1 spell, potent spell, and spell theorem]".md,
    List(
      TalentWidget("Spells", Checkbox withMax 4 withStart 0),
      TalentWidget("Potent Spells", Checkbox withMax 2 withStart 0)
    )
  )

  val alchemist = Talent(
    "Alchemist",
    "Each session, you have a ***4d Potions*** resource pool. You can have a minor potion and roll the pool, or drop 1 and roll for a major potion. You know recipes for your spell theorems, plus two more. Learn new recipes by sacrificing potions.".md,
    List(
      TalentWidget(
        "Potions",
        DicePoolComponent(DicePool(4), _ => DicePool(4))
      )
    )
  )

  val arcaneSpecialty = Talent(
    "Arcane Specialty",
    "Choose your specialty school. All of your spell theorems now have it plus another school. When casting, choose which school to use. On a critical with your specialty school, **take spark**.".md,
    Nil
  )

  val arcanist = Talent(
    "Arcanist",
    "Take +1d when rolling an arcana’s pool and ignore the first die that would be dropped. You also gain ***3 minor arcana*** and ***1 major arcana*** that you’ve either created or found.".md,
    Nil
  )

  val colleagues = Talent(
    "Colleagues",
    "In every city, some towns, and the occasional dungeon, you can find a powerful, eccentric wizardly colleague who owes you a favor—or maybe you owe them one. If you go out of your way to meet and catch up, **take spark**.".md,
    Nil
  )

  val familiar = Talent(
    "Familiar",
    "You manifest a small magical creature. You can communicate telepathically and send it on simple tasks, making a story roll to see how it goes. By entering a trance, you can use its senses. You can also **push yourself** to cast a spell through it. If your familiar takes damage, it vanishes and reappears at the start of the next session.".md,
    List(
      TalentWidget("Push", Checkbox withMax 1 withStart 0)
    )
  )

  val masteredTheorem = Talent(
    "Mastered Theorem",
    "Choose one of your spell theorems. You take +1d when casting it and gain 1 extra ***potent spell*** casting of it per session. Your name becomes linked to it and it begins to spread in popularity throughout wizardry—track it with campaign pools.".md,
    List(
      TalentWidget("Potent", Checkbox withMax 1 withStart 0)
    )
  )

  val preparedSpell = Talent(
    "Prepared Spell",
    "You gain 1 story per session and can spend story to flashback to casting a spell with specific triggers. If a montage to get access to a place is needed, the GM always takes suspense in place of an impact move. Roll for the spell’s effectiveness now.".md,
    List(TalentWidget("Story", StorySelectable(false)))
  )
}
