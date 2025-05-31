package model;

import java.util.ArrayList;

public class Race {
    public static ArrayList<Race> presets = new ArrayList<>();

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

    public String getName() {
        return this.name;
    }

    public static Race getRace(String name){
        for(Race r : presets){
            if(r.name.equalsIgnoreCase(name)){
                return r;
            }
        }
        return null;
    }

    static {
        presets.add(new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10));
        presets.add(new Race("Elf", 2, 6, 6, 4, 2, 5, 3, 6, 6, 18));
        presets.add(new Race("Dwarf", 6, 2, 2, 3, 6, 3, 6, 20, 7, 3));
        presets.add(new Race("Demon", 6, 6, 3, 4, 3, 3, 3, 15, 5, 10));
        presets.add(new Race("Undead", 5, 4, 1, 3, 6, 3, 6, 18, 4, 8));
    }
}
