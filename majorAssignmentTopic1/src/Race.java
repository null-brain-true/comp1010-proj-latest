public class Race {
    String name;
    int Strength, Intellect, Agility, Initiative, Defense, Evasion, Resist;
    int bonusHP, bonusSP, bonusMP;

    public Race(String name, int Strength, int Intellect, int Agility, int Initiative, int Defense, int Evasion, int Resist, int bonusHP, int bonusSP, int bonusMP){
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

    public void applyRaceStat(Character c){
        c.Strength += Strength;
        c.Intellect += Intellect;
        c.Agility += Agility;
        c.Initiative += Initiative;
        c.Defense += Defense;
        c.Evasion += Evasion;
        c.Resist += Resist;
        c.HP += bonusHP;
        c.SP += bonusSP;
        c.MP += bonusMP;
    }
}
