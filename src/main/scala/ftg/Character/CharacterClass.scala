package ftg.Character

import upickle.default.ReadWriter

enum CharacterClass derives ReadWriter {
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
