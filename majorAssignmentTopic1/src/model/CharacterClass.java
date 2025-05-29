package model;
import java.util.ArrayList;

public class CharacterClass {
    public String name;
    public int Strength, Intellect, Agility, Initiative, Defense, Evasion, Resist;
    public int bonusHP, bonusSP, bonusMP;
    public ArrayList<Action> abilities;

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

}
