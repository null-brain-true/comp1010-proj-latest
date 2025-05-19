import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    ArrayList<Team> team;
    LinkedList<Character> turnOrder;
    ArrayList<String> combatLog;
    int turnCount;
    Scanner scanner;

    // Races
    Race HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);
    Race ELF = new Race("Elf", 2, 6, 6, 4, 2, 5, 3, 6, 6, 18);
    Race DWARF = new Race("Dwarf", 6, 2, 2, 3, 6, 3, 6, 20, 7, 3);
    Race DEMON = new Race("Demon", 6, 6, 3, 4, 3, 3, 3, 15, 5, 10);
    Race UNDEAD = new Race("Undead", 5, 4, 1, 3, 6, 3, 6, 18, 4, 8);

    // Character Classes
    CharacterClass WARRIOR;
    CharacterClass PALADIN;
    CharacterClass ROGUE;
    CharacterClass RANGER;
    CharacterClass MAGE;
    CharacterClass CLERIC;

    public Game() {
        team = new ArrayList<>();
        turnOrder = new LinkedList<>();
        combatLog = new ArrayList<>();
        turnCount = 1;
        scanner = new Scanner(System.in);

        // Initialize Classes
        // Warrior and Warrior Skills
        ArrayList<Action> warriorSkills = new ArrayList<>();
        warriorSkills.add(new Action("Slash", "attack", 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        warriorSkills.add(new Action("War Cry", "buff", 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 10, 0, 0));
        // War Cry - +2 Strength to team
        warriorSkills.add(new Action("Defender Stance", "buff", 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 8, 0));
        // Defender Stance - +2 Defense
        warriorSkills.add(new Action("Berserk", "buff", 0, 0, 0, 0, 2, 3, 0, 0, 0, 0, 0, -1, 10, 0, 0));
        // Berserk - +3 Strength, -1 Resist
        WARRIOR = new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills);

        // Paladin and Paladin Skills
        ArrayList<Action> paladinSkills = new ArrayList<>();
        paladinSkills.add(new Action("Strike", "attack", 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        paladinSkills.add(new Action("Sanctuary", "buff", 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 2, 0, 0, 10));
        // Sanctuary - +2 Defense, +2 Resist to team
        paladinSkills.add(new Action("Judgement", "dot", 10, 0, 4, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 8));
        // Judgement - Damage + DoT
        paladinSkills.add(new Action("Heal Touch", "heal", 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6));
        PALADIN = new CharacterClass("Paladin", 5, 4, 3, 3, 6, 3, 4, 14, 8, 8, paladinSkills);

        // Rogue and Rogue Skills
        ArrayList<Action> rogueSkills = new ArrayList<>();
        rogueSkills.add(new Action("Stab", "attack", 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        rogueSkills.add(new Action("Poison Blade", "dot", 8, 0, 5, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0));
        rogueSkills.add(new Action("Quick Step", "buff", 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 2, 0, 0, 5, 0));
        // Quick Step - +3 Agility, +2 Evasion
        rogueSkills.add(new Action("Cheap Shot", "debuff", 0, 0, 0, 0, 1, 0, 0, -1, 0, -2, 0, 0, 0, 7, 0));
        // Cheap Shot - minor debuff to simulate stun
        ROGUE = new CharacterClass("Rogue", 4, 3, 7, 5, 3, 5, 1, 10, 15, 5, rogueSkills);

        // Ranger and Ranger Skills
        ArrayList<Action> rangerSkills = new ArrayList<>();
        rangerSkills.add(new Action("Shoot", "attack", 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        rangerSkills.add(new Action("Volley", "attack", 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0));
        // Volley - Simulated AoE manually
        rangerSkills.add(new Action("Expose Weakness", "debuff", 0, 0, 0, 0, 3, 0, 0, 0, 0, -2, -2, -2, 0, 6, 0));
        rangerSkills.add(new Action("Camouflage", "buff", 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 3, 0, 0, 5, 0));
        // Camouflage - Evasion buff
        RANGER = new CharacterClass("Ranger", 5, 3, 6, 5, 4, 4, 1, 12, 12, 6, rangerSkills);

        // Mage and Mage Skills
        ArrayList<Action> mageSkills = new ArrayList<>();
        mageSkills.add(new Action("Bolt", "attack", 12, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0));
        mageSkills.add(new Action("Fireball", "dot", 5, 0, 6, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 10));
        mageSkills.add(new Action("Magic Shield", "buff", 0, 0, 0, 0, 3, 0, 2, 0, 0, 3, 0, 2, 0, 0, 7));
        // Magic Shield - buff self/team
        mageSkills.add(new Action("Dispel", "debuff", 0, 0, 0, 0, 1, -2, -2, 0, 0, 0, 0, -2, 0, 0, 8));
        MAGE = new CharacterClass("Mage", 1, 8, 3, 3, 2, 3, 8, 8, 7, 15, mageSkills);

        // Cleric and Cleric Skills
        ArrayList<Action> clericSkills = new ArrayList<>();
        clericSkills.add(new Action("Smite", "attack", 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        clericSkills.add(new Action("Heal", "heal", 0, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10));
        clericSkills.add(new Action("Prayer", "hot", 0, 6, 0, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8));
        clericSkills.add(new Action("Purify", "buff", 0, 0, 0, 0, 1, 0, 2, 0, 0, 1, 0, 2, 0, 0, 6));
        // Purify - removes debuff by overwriting
        CLERIC = new CharacterClass("Cleric", 3, 7, 3, 3, 4, 3, 5, 9, 8, 13, clericSkills);
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
                if (!c.isDefeated()) {
                    turnOrder.add(c);
                }
            }
        }
        turnOrder.sort((c1, c2) -> Integer.compare(c2.Initiative, c1.Initiative));
    }

    public void nextTurn() {
        for (Character current : new ArrayList<>(turnOrder)) {
            if (current.HP <= 0)
                continue;

            for (StatusEffect effect : current.getActiveStatusEffects()) {
                effect.applyTurnEffect(current);
                effect.duration--;
                effect.buffWearOff(current);
                effect.debuffWearOff(current);
            }
            current.getActiveStatusEffects().removeIf(StatusEffect::isExpired);

            System.out.println("\n== " + current.name + "'s Turn ==");
            System.out.println("HP:" + current.HP + " | MP: " + current.MP + " | SP: " + current.SP);

            if (current.actions.isEmpty()) {
                System.out.println(current.name + " has no actions. Skipping turn.");
                continue;
            }
        }
    }

    public boolean checkCondition() {
        int activeTeams = 0;
        for (Team t : team) {
            if (t.isActive()) {
                activeTeams++;
            }
        }
        return activeTeams <= 1;
    }

    public String combatLog() {
        StringBuilder sb = new StringBuilder();
        for (String log : combatLog)
            sb.append(log).append("\n");
        return sb.toString();
    }
}
