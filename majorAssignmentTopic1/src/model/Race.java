package model;
public class Race {
    public String name;
    public int Strength;
    public int Intellect;
    public int Agility;
    public int Initiative;
    public int Defense;
    public int Evasion;
    public int Resist;
    public int bonusHP, bonusSP, bonusMP;

    public Race(String name, int Strength, int Intellect, int Agility, int Initiative, int Defense, int Evasion,
            int Resist, int bonusHP, int bonusSP, int bonusMP) {
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
    }

    public void applyRaceStat(Character c) {
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
}
