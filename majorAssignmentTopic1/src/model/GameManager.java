package model;

import java.io.IOException;//tried to load characters but did not work properly
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    // create sample game
    public static Game createSampleGame() {
        Game game = new Game();

        // Create sample characters
        Character c1 = new Character("Aragon", Game.HUMAN, Game.WARRIOR);
        Character c2 = new Character("Elara", Game.ELF, Game.MAGE);
        Character c3 = new Character("Durin", Game.DWARF, Game.CLERIC);
        Character c4 = new Character("Raze", Game.DEMON, Game.ROGUE);

        // Create sample teams
        Team team1 = new Team("Team Red", new ArrayList<>(List.of(c1, c2)));
        Team team2 = new Team("Team Blu", new ArrayList<>(List.of(c3, c4)));

        // add teams
        game.team.add(team1);
        game.team.add(team2);

        return game;
    }

    public static List<Character> createSampleCharacters() {
        List<Character> characters = new ArrayList<>();

        // create characters using presets
        Character c1 = new Character("Aragon", Game.HUMAN, Game.WARRIOR);
        Character c2 = new Character("Elara", Game.ELF, Game.MAGE);
        Character c3 = new Character("Durin", Game.DWARF, Game.CLERIC);
        Character c4 = new Character("Raze", Game.DEMON, Game.ROGUE);

        // add character to list
        characters.add(c1);
        characters.add(c2);
        characters.add(c3);
        characters.add(c4);

        return characters;
    }

}
