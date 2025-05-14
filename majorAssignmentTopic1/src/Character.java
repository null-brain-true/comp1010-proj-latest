import java.util.ArrayList;

public class Character {
    String name;
    int HP, SP, MP;
    int Strength, Intellect, Agility, Initiative, Defense, Evasion, Resist;
    Race race;
    CharacterClass characterClass;
    ArrayList<Action> actions;
    ArrayList<StatusEffect> activeStatusEffects;

    public Character(String name, Race race, CharacterClass characterClass){
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.actions = new ArrayList<>();

        //default HP and SP values
        int baseHP = 50;
        int baseSP = 100;
        int baseMP = 100;

        this.HP = baseHP;
        this.SP = baseSP;
        this.MP = baseMP;
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

    public void addAction(Action action){
        actions.add(action);
    }

    public void addStatusEffect(StatusEffect statusEffect){
        activeStatusEffects.add(statusEffect);
    }

    public void removeStatusEffect(StatusEffect statusEffect){
        activeStatusEffects.remove(statusEffect);
    }

    public ArrayList<StatusEffect> getActiveStatusEffects(){
        return activeStatusEffects;
    }
}
