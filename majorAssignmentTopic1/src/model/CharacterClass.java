package model;

import java.util.ArrayList;

public class CharacterClass {
    public static ArrayList<CharacterClass> presets = new ArrayList<>();

    public String name;
    public int Strength, Intellect, Agility, Initiative, Defense, Evasion, Resist;
    public int bonusHP, bonusSP, bonusMP;
    public ArrayList<Action> abilities;

    // class skills list
    static ArrayList<Action> warriorSkills = new ArrayList<>();
    static ArrayList<Action> paladinSkills = new ArrayList<>();
    static ArrayList<Action> rogueSkills = new ArrayList<>();
    static ArrayList<Action> rangerSkills = new ArrayList<>();
    static ArrayList<Action> mageSkills = new ArrayList<>();
    static ArrayList<Action> clericSkills = new ArrayList<>();

    /**
     * Constructor for CharacterClas class
     * 
     * @param name
     * @param Strength
     * @param Intellect
     * @param Agility
     * @param Initiative
     * @param Defense
     * @param Evasion
     * @param Resist
     * @param bonusHP
     * @param bonusSP
     * @param bonusMP
     * @param abilities
     */
    public CharacterClass(String name, int Strength, int Intellect, int Agility, int Initiative, int Defense,
            int Evasion, int Resist, int bonusHP, int bonusSP, int bonusMP, ArrayList<Action> abilities) {
        this.name = name;
        this.Strength = Strength;
        this.Intellect = Intellect;
        this.Agility = Agility;
        this.Initiative = Initiative;
        this.Defense = Defense;
        this.Evasion = Evasion;
        this.Resist = Resist;
        this.bonusHP = bonusHP;
        this.bonusSP = bonusSP;
        this.bonusMP = bonusMP;
        this.abilities = abilities;
    }

    // stat application to character
    public void applyClassStat(Character c) {
        c.Strength += this.Strength;
        c.Intellect += this.Intellect;
        c.Agility += this.Agility;
        c.Initiative += this.Initiative;
        c.Defense += this.Defense;
        c.Evasion += this.Evasion;
        c.Resist += this.Resist;
        c.HP += this.bonusHP;
        c.SP += this.bonusSP;
        c.MP += this.bonusMP;
    }

    public void applyClassAbilities(Character c) {
        for (Action ability : abilities) {
            c.addAction(ability);
        }
    }

    public String getName() {
        return this.name;
    }

    // for file io
    public static CharacterClass getCharacterClass(String name) {
        for (CharacterClass cc : presets) {
            if (cc.name.equalsIgnoreCase(name)) {
                return cc;
            }
        }
        return null;
    }

    static {
        presets.add(new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills));
        presets.add(new CharacterClass("Paladin", 5, 4, 3, 3, 6, 3, 4, 14, 8, 8, paladinSkills));
        presets.add(new CharacterClass("Rogue", 4, 3, 7, 5, 3, 5, 1, 10, 15, 5, rogueSkills));
        presets.add(new CharacterClass("Ranger", 5, 3, 6, 5, 4, 4, 1, 12, 12, 6, rangerSkills));
        presets.add(new CharacterClass("Mage", 1, 8, 3, 3, 2, 3, 8, 8, 7, 15, mageSkills));
        presets.add(new CharacterClass("Cleric", 3, 7, 3, 3, 4, 3, 5, 9, 8, 13, clericSkills));
    }
}
