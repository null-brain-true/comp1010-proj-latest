# RPG Turn Based Game

## Porject Overview

This Java-based **RPG Turn Based Game** allows users to play a turn based game by typing in numbers as prompted in the terminal. Users can choose the skill they want to use using the numbers 1-4, afterwards using the numbers to choose their target.

Users can:
-Run a turn based game.
-Choose the skills they want to use.
-Pick their target based on the skill.
-Create new team with characters and save them to a file.

## Features
- **Playable Game**: Can play the game through user inputs.
- **Character Creation**: Can create characters from a given preset of races and character classes.
- **FILE I/O**: Can create characters and group them into teams.

## How to Use

1. Navigate to `src\` directory.

2. Compile Java Files.
    javac Client.java

3. Run the program:

## Dependencies
**JUnit** For running test cases.

## File I/O
- **Character Manager**: Enter the number of characters you want to create and create each character one by one.
//NOTE - when choosing Race or CharacterClass, choose from the existing list.
-Races : HUMAN, ELF, DWARF, DEMON, UNDEAD
-Classes : WARRIOR, PALADIN, ROGUE, RANGER, MAGE, CLERIC

## Example for creating team/characters
Enter number of characters to create: 3
Character #1
Name: Gar
Race: Dwarf
Class: Paladin
Character #2
Name: Yed
Race: Undead
Class: Ranger
Character #3
Name: Ul
Race: Elf
Class: Mage
Enter the CSV filename to save to (e.g., yellowteam.csv): redteam.csv
Characters saved to redteam.csv

## File Structure
`Action.java`: Handles the class skills and how they effect the target.
`Character.java` : Handles the character creation and stats.
`CharacterClass.java` : Handles the character classes, stats and skills.
`CharacterManager.java` : Handles the team and character creation.
`Dice.java` : Handles the dice rolls which ensures the crits, hits, misses and blocks.
`Game.java` : Holds the game rules and logics.
`GameManager.java` : Holds the characters for the game.
`Race.java` : Holds the races and stats.
`StatusEffect.java` : Handles the Damage Over Time, Heal Over Time, Buff and Debuff.
`Team.java` : Handles the character teams to mark enemy or friend.

## Recursive Structure in StatusEffect class
The `StatusEffect.java` class uses the recursive structure to reduce the duration of the DoT, HoT, Buff and Debuff.

## Future Enhancements
-Ability to directly implement characters into GameManager to be playable with custom characters.
-Create a GUI to be more interactive for users.