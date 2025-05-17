import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Game {
    ArrayList<Team> team;
    ArrayList<Character> turnOrder;
    ArrayList<String> combatLog;
    int turnCount;
    String messages;

    // Races
    Race HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);
    Race ELF = new Race("Elf", 2, 6, 6, 4, 2, 5, 3, 6, 6, 18);
    Race DWARF = new Race("Dwarf", 6, 2, 2, 3, 6, 3, 6, 20, 7, 3);
    Race DEMON = new Race("Demon", 6, 6, 3, 4, 3, 3, 3, 15, 5, 10);
    Race UNDEAD = new Race("Undead", 5, 4, 1, 3, 6, 3, 6, 18, 4, 8);

    Scanner scanner;

    public Game() {
        team = new ArrayList<>();
        turnOrder = new ArrayList<>();
        combatLog = new ArrayList<>();
        turnCount = 1;
        scanner = new Scanner(System.in);
    }

    public void start() {
        generateTurnOrder();

        while (!checkCondition()) {
            System.out.println("\n== TURN" + " ===");
            nextTurn();
            turnCount++;
        }

        System.out.println("=== COMBAT ENDED ===");
        System.out.println(combatLog());
    }

    public void generateTurnOrder() {
        turnOrder.clear();

        for (Team t : team) {
            for (Character c : t.members) {
                if (c.HP > 0) {
                    turnOrder.add(c);
                }
            }
        }
        turnOrder.sort((c1, c2) -> Integer.compare(c2.Initiative, c1.Initiative));
    }

    public void nextTurn(){
        for (Character current : new ArrayList<>(turnOrder)){
            if(current.HP <= 0) continue;
        
            for (StatusEffect effect : current.getActiveStatusEffects()){
                effect.applyTurnEffect(current);
                effect.duration--;
                effect.buffWearOff(current);
                effect.debuffWearOff(current);
            }
            current.getActiveStatusEffects().removeIf(StatusEffect::isExpired);

            System.out.println("\n== " + current.name + "'s Turn ==");
            System.out.println("HP:" + current.HP + " | MP: " + current.MP + " | SP: " + current.SP);

            if(current.actions.isEmpty()) {
                System.out.println(current.name + " has no actions. Skipping turn.");
                continue;
            }
    }

    public boolean checkCondition(){
        int activeTeams = 0;
        for(Team t : team){
            if(t.isActive()){
                activeTeams++;
            }
        }
        return activeTeams <= 1;
    }

    public String combatLog(){

    }
    
    }
}
