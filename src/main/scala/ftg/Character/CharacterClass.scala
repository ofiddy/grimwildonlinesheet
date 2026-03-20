package ftg.Character

enum CharacterClass {
  case Bard,
    Berserker,
    Cleric,
    Druid,
    Fighter,
    Monk,
    Paladin,
    Ranger,
    Rogue,
    Sorcerer,
    Warlock
  case Custom(label: String)
}
