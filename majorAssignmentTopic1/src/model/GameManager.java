package model;
import java.util.ArrayList;
import java.util.List;


public class GameManager {
    public static Game createSampleGame() {
        Game game = new Game();

        // Create characters
        Character c1 = new Character("Aragon", game.HUMAN, game.WARRIOR);
        Character c2 = new Character("Elara", game.ELF, game.MAGE);
        Character c3 = new Character("Durin", game.DWARF, game.CLERIC);
        Character c4 = new Character("Raze", game.DEMON, game.ROGUE);

        // Create teams
        Team team1 = new Team("Team Red", new ArrayList<>(List.of(c1, c2)));
        Team team2 = new Team("Team Blu", new ArrayList<>(List.of(c3, c4)));

        game.team.add(team1);
        game.team.add(team2);

        return game;
    }
}
