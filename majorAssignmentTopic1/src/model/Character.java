package model;
import java.util.ArrayList;

public class Character {
    public String name;
    public int HP, SP, MP;
    public int Strength, Intellect, Agility, Initiative, Defense, Evasion, Resist;
    public Race race;
    public CharacterClass characterClass;
    public ArrayList<Action> actions;
    public ArrayList<StatusEffect> activeStatusEffects;
    public boolean isDefeated, outOfSP, outOfMP;
    private int currentHP, currentSP, currentMP;

    //character constructor
    public Character(String name, Race race, CharacterClass characterClass) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.actions = new ArrayList<>();

        this.activeStatusEffects = new ArrayList<>();

        // default HP and SP values
        int baseHP = 50;
        int baseSP = 100;
        int baseMP = 100;

        this.HP = baseHP;
        this.currentHP = this.HP;
        this.SP = baseSP;
        this.currentSP = this.SP;
        this.MP = baseMP;
        this.currentMP = this.MP;
        this.Strength = 0;
        this.Intellect = 0;
        this.Agility = 0;
        this.Initiative = 0;
        this.Defense = 0;
        this.Evasion = 0;
        this.Resist = 0;

        race.applyRaceStat(this);
        characterClass.applyClassStat(this);

        characterClass.applyClassAbilities(this);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addStatusEffect(StatusEffect statusEffect) {
        activeStatusEffects.add(statusEffect);
    }

    public void removeStatusEffect(StatusEffect statusEffect) {
        activeStatusEffects.remove(statusEffect);
    }

    public ArrayList<StatusEffect> getActiveStatusEffects() {
        return activeStatusEffects;
    }

    public boolean isDefeated() {
        return this.currentHP <= 0;
    }

    public boolean outOfSP(){
        return this.currentSP <= 0;
    }

    public boolean outOfMP(){
        return this.currentMP <= 0;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int hp) {
        this.currentHP = hp;
    }

    public int getCurrentSP() {
        return this.currentSP;
    }

    public void setCurretSP(int sp) {
        this.currentSP = sp;
    }

    public int getCurrentMP() {
        return this.currentMP;
    }

    public void setCurrentMP(int mp) {
        this.currentMP = mp;
    }
}
