package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    public ArrayList<Team> team;
    public LinkedList<Character> turnOrder;
    public int turnCount;
    Scanner scanner;

    // Race names and their stat points
    static Race HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);
    static Race ELF = new Race("Elf", 2, 6, 6, 4, 2, 5, 3, 6, 6, 18);
    static Race DWARF = new Race("Dwarf", 6, 2, 2, 3, 6, 3, 6, 20, 7, 3);
    static Race DEMON = new Race("Demon", 6, 6, 3, 4, 3, 3, 3, 15, 5, 10);
    static Race UNDEAD = new Race("Undead", 5, 4, 1, 3, 6, 3, 6, 18, 4, 8);

    // Character Classes
    static CharacterClass WARRIOR;
    static CharacterClass PALADIN;
    static CharacterClass ROGUE;
    static CharacterClass RANGER;
    static CharacterClass MAGE;
    static CharacterClass CLERIC;

    public Game() {
        team = new ArrayList<>();
        turnOrder = new LinkedList<>();
        turnCount = 1;
        scanner = new Scanner(System.in);

        // Initialize Classes
        // Warrior and Warrior Skills
        ArrayList<Action> warriorSkills = new ArrayList<>();
        warriorSkills.add(new Action("Slash", "attack", "physical", 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        warriorSkills.add(new Action("War Cry", "buff", "magical", 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 10, 0, 0));
        // War Cry - +2 Strength to team
        warriorSkills
                .add(new Action("Defender Stance", "buff", "physical", 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 8, 0));
        // Defender Stance - +2 Defense
        warriorSkills.add(new Action("Berserk", "buff", "magical", 0, 0, 0, 0, 2, 3, 0, 0, 0, 0, 0, -1, 10, 0, 0));
        // Berserk - +3 Strength, -1 Resist
        WARRIOR = new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills);

        // Paladin and Paladin Skills
        ArrayList<Action> paladinSkills = new ArrayList<>();
        paladinSkills.add(new Action("Strike", "attack", "physical", 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        paladinSkills.add(new Action("Sanctuary", "buff", "magical", 0, 0, 0, 0, 3, 0, 0, 0, 0, 2, 0, 2, 0, 0, 10));
        // Sanctuary - +2 Defense, +2 Resist to team
        paladinSkills.add(new Action("Judgement", "dot", "hybrid", 10, 0, 4, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 8));
        // Judgement - Damage + DoT
        paladinSkills.add(new Action("Heal Touch", "heal", "magical", 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6));
        PALADIN = new CharacterClass("Paladin", 5, 4, 3, 3, 6, 3, 4, 14, 8, 8, paladinSkills);

        // Rogue and Rogue Skills
        ArrayList<Action> rogueSkills = new ArrayList<>();
        rogueSkills.add(new Action("Stab", "attack", "physical", 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        rogueSkills.add(new Action("Poison Blade", "dot", "physical", 8, 0, 5, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0));
        rogueSkills.add(new Action("Quick Step", "buff", "physical", 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 2, 0, 0, 5, 0));
        // Quick Step - +3 Agility, +2 Evasion
        rogueSkills.add(new Action("Cheap Shot", "debuff", "physical", 0, 0, 0, 0, 1, 0, 0, -1, 0, -2, 0, 0, 0, 7, 0));
        // Cheap Shot - minor debuff to simulate stun
        ROGUE = new CharacterClass("Rogue", 4, 3, 7, 5, 3, 5, 1, 10, 15, 5, rogueSkills);

        // Ranger and Ranger Skills
        ArrayList<Action> rangerSkills = new ArrayList<>();
        rangerSkills.add(new Action("Shoot", "attack", "physical", 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        rangerSkills.add(new Action("Volley", "attack", "physical", 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0));
        // Volley - Simulated AoE manually
        rangerSkills.add(
                new Action("Expose Weakness", "debuff", "magical", 0, 0, 0, 0, 3, 0, 0, 0, 0, -2, -2, -2, 0, 6, 0));
        rangerSkills.add(new Action("Camouflage", "buff", "magical", 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 3, 0, 0, 5, 0));
        // Camouflage - Evasion buff
        RANGER = new CharacterClass("Ranger", 5, 3, 6, 5, 4, 4, 1, 12, 12, 6, rangerSkills);

        // Mage and Mage Skills
        ArrayList<Action> mageSkills = new ArrayList<>();
        mageSkills.add(new Action("Bolt", "attack", "magical", 12, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0));
        mageSkills.add(new Action("Fireball", "dot", "magical", 5, 0, 6, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 10));
        mageSkills.add(new Action("Magic Shield", "buff", "magical", 0, 0, 0, 0, 3, 0, 2, 0, 0, 3, 0, 2, 0, 0, 7));
        // Magic Shield - buff self/team
        mageSkills.add(new Action("Dispel", "debuff", "magical", 0, 0, 0, 0, 1, -2, -2, 0, 0, 0, 0, -2, 0, 0, 8));
        MAGE = new CharacterClass("Mage", 1, 8, 3, 3, 2, 3, 8, 8, 7, 15, mageSkills);

        // Cleric and Cleric Skills
        ArrayList<Action> clericSkills = new ArrayList<>();
        clericSkills.add(new Action("Smite", "attack", "magical", 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        clericSkills.add(new Action("Heal", "heal", "magical", 0, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10));
        clericSkills.add(new Action("Prayer", "hot", "magical", 0, 6, 0, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8));
        clericSkills.add(new Action("Purify", "buff", "magical", 0, 0, 0, 0, 1, 0, 2, 0, 0, 1, 0, 2, 0, 0, 6));
        // Purify - removes debuff by overwriting
        CLERIC = new CharacterClass("Cleric", 3, 7, 3, 3, 4, 3, 5, 9, 8, 13, clericSkills);
    }

    public void start() {
        generateTurnOrder();

        while (!checkCondition()) {
            System.out.println("\n== TURN " + turnCount + " ===");
            nextTurn();
            turnCount++;
        }

        System.out.println("=== COMBAT ENDED ===");
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
        turnOrder.sort(Comparator.comparingInt(Character::getInitiative).reversed()); // sorts the turn order based on
                                                                                      // initiative
    }

    public void nextTurn() {
        for (Character current : new ArrayList<>(turnOrder)) {
            if (current.HP <= 0)
                continue;

            updateStatusEffects(current);

            // Apply and manage status effects at the start of the turn
            for (StatusEffect effect : new ArrayList<>(current.getActiveStatusEffects())) {
                effect.applyTurnEffect(current);
                effect.duration--;
                if (effect.isExpired()) {
                    effect.buffWearOff(current); // Remove stat modifications if duration is 0
                    effect.debuffWearOff(current);
                }
            }
            current.getActiveStatusEffects().removeIf(StatusEffect::isExpired); // handles expired buff and debuff

            // lines in terminal
            System.out.println("\n== " + current.name + "'s Turn ==");
            System.out.println("HP:" + current.HP + " | MP: " + current.MP + " | SP: " + current.SP);
            System.out.println(
                    "Current Stats: Str:" + current.Strength + " Int:" + current.Intellect + " Agi:" + current.Agility +
                            " Ini:" + current.Initiative + " Def:" + current.Defense + " Eva:" + current.Evasion
                            + " Res:" + current.Resist);

            System.out.println("Choose an action:");
            for (int i = 0; i < current.actions.size(); i++) {
                Action action = current.actions.get(i);
                System.out.println(
                        (i + 1) + ": " + action.name + " (Type: " + action.type + ", HP Cost: " + action.hpCost +
                                ", SP Cost: " + action.spCost + ", MP Cost: " + action.mpCost + ")");
            }

            int choice = 0;
            while (choice < 1 || choice > current.actions.size()) {
                System.out.println("Enter action number: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }
            Action selectedAction = current.actions.get(choice - 1);

            Team enemyTeam = getEnemyTeam(current);
            Team friendlyTeam = getFriendlyTeam(current);

            Character target = null;
            boolean isEnemyTarget = false;

            // Determine target type based on action type
            if (selectedAction.type.equals("attack") || selectedAction.type.equals("dot")
                    || selectedAction.type.equals("debuff")) {
                if (enemyTeam == null || enemyTeam.members.isEmpty()) {
                    System.out.println("No enemies left to target!");
                    continue; // Skip turn if no valid target
                }
                target = selectTarget(enemyTeam);
                isEnemyTarget = true;
            } else if (selectedAction.type.equals("heal") || selectedAction.type.equals("hot")
                    || selectedAction.type.equals("buff")) {
                if (friendlyTeam == null || friendlyTeam.members.isEmpty()) {
                    System.out.println("No allies to target!");
                    continue; // Skip turn if no valid target
                }
                target = selectTarget(friendlyTeam);
                isEnemyTarget = false;
            } else {
                System.out.println("Unknown action type " + selectedAction.type);
                continue; // Skip turn for unknown action type
            }

            if (target == null) {
                System.out.println("No valid target selected.");
                continue;
            }

            // pass isEnemy to the effect method
            selectedAction.effect(current, target, isEnemyTarget);
        }
        generateTurnOrder(); // recreate turn order when characters are defeated
    }

    private void updateStatusEffects(Character current) {
        if (current == null) {
            return;
        }

        current.getActiveStatusEffects();
    }

    private Team getEnemyTeam(Character current) {
        for (Team t : team) {
            // if team does not contain the current character, it is enemy team
            if (!t.members.contains(current)) {
                if (t.isActive()) {
                    return t;
                }
            }
        }
        return null;
    }

    private Team getFriendlyTeam(Character current) {
        for (Team t : team) {
            // if team contains the current character, it is friendly team
            if (t.members.contains(current)) {
                if (t.isActive()) {
                    return t;
                }
            }
        }
        return null;
    }

    // target selection
    private Character selectTarget(Team teamToTarget) {
        System.out.println("Select target: ");
        for (int i = 0; i < teamToTarget.members.size(); i++) {
            Character c = teamToTarget.members.get(i);
            if (!c.isDefeated()) {
                System.out.println((i + 1) + ": " + c.name + " (HP: " + c.HP + ")");
            } else {
                System.out.println((i + 1) + ": " + c.name + " (Defeated)");
            }
        }
        int targetChoice = 0;
        while (true) {
            System.out.println("Enter target number: ");
            if (scanner.hasNextInt()) {
                targetChoice = scanner.nextInt();
                if (targetChoice >= 1 && targetChoice <= teamToTarget.members.size()) {
                    Character potentialTarget = teamToTarget.members.get(targetChoice - 1);
                    if (!potentialTarget.isDefeated()) {
                        return potentialTarget;
                    } else {
                        System.out.println("That target has been defeated. Please choose another.");
                    }
                } else {
                    System.out.println(
                            "Invalid number. Please enter a number between 1 and " + teamToTarget.members.size() + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
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
}