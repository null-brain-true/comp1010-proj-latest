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
- Playing the Game
1. Make sure you have JDK 11+ installed.
2. Compile everything:
   ```bash
   javac -d bin src/**/*.java
   ```
3. Run the game:
   ```bash
   java -cp bin Client
   ```

- Creating Characters
1. Navigate to `src\` -> `model\` directory.

2. javac CharacterManager.java

3. Run the program

## Dependencies
**JUnit** For running test cases.

## File I/O
- **Character Manager**: Enter the number of characters you want to create and create each character one by one.
//NOTE - when choosing Race or CharacterClass, choose from the existing list.
-Races : HUMAN, ELF, DWARF, DEMON, UNDEAD
-Classes : WARRIOR, PALADIN, ROGUE, RANGER, MAGE, CLERIC

## Example for playing the Game
== TURN 1 ===

== Raze's Turn ==
HP:75 | MP: 115 | SP: 120
Current Stats: Str:10 Int:9 Agi:10 Ini:9 Def:6 Eva:8 Res:4
Choose an action:
1: Stab (Type: attack, HP Cost: 0, SP Cost: 0, MP Cost: 0)
2: Poison Blade (Type: dot, HP Cost: 0, SP Cost: 6, MP Cost: 0)
3: Quick Step (Type: buff, HP Cost: 0, SP Cost: 5, MP Cost: 0)
4: Cheap Shot (Type: debuff, HP Cost: 0, SP Cost: 7, MP Cost: 0)
Enter action number:
1
Select target: 
1: Aragon (HP: 75)
2: Elara (HP: 64)
Enter target number:
2
Raze dealt 20 to Elara with Stab

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
`bluteam.csv`, `redteam.csv` — custom team data files

## Recursive Structure in StatusEffect class
The `StatusEffect.java` class uses the recursive structure to reduce the duration of the DoT, HoT, Buff and Debuff.

## Future Enhancements
-Ability to directly implement characters into GameManager to be playable with custom characters.
-Create a GUI to be more interactive for users.

## Method Comparisons
- Method#1 - generateTurnOrder() from Game.java
    The method generateTurnOrder() we implementd goes through each team and their characters to get their initiative stats and then order them highest to lowest. The alternate method could be done recursively but it would have been less readable, uses huge memory and prone to more error

- Method#2 - updateStatusEffects(List<StatusEffect> effects, Character c, int index) from StatusEffect.java
    The method updateStatusEffects(List<StatusEffect> effects, Character c, int index) was implemented recursively which makes it more readable for smaller lists while using more memory and be more prone to errors. The alternate method could haven been done using loops which uses less memory, straightforward and much more reliable.

## Task Allocation
- Min Khant Tun 37% - Ideation, UML Diagram, Action, CharacterManager, Game, GameManager, JUnit tests, primary contributor
- Ilham Tazrian Athoy 21% - JUnit tests, CharacterClass, Action
- Osei Boadu 22% - Ideation, Code testing, Team, Dice, StatusEffect, GameManager, Got feedback from TA, ReadME.md
- John Ochigbo 20% - JUnit tests, Race, CharacterManager