package model;

import java.util.List;

public class StatusEffect {
    public String name;
    public int DoT, HoT; // Damage Over Time, Heal OverTime
    public int strengthMod, intellectMod, agilityMod, initiativeMod, defenseMod, evasionMod, resistMod;
    public int duration;
    public boolean buffApplied = false;
    public boolean debuffApplied = false;

    public StatusEffect(String name, int DoT, int HoT, int strengthMod, int intellectMod, int agilityMod,
            int initiativeMod, int defenseMod, int evasionMod, int resistMod, int duration) {
        this.name = name;
        this.DoT = DoT;
        this.HoT = HoT;
        this.strengthMod = strengthMod;
        this.intellectMod = intellectMod;
        this.agilityMod = agilityMod;
        this.initiativeMod = initiativeMod;
        this.defenseMod = defenseMod;
        this.evasionMod = evasionMod;
        this.resistMod = resistMod;
        this.duration = duration;
    }

    public void applyBuff(Character c) {
        if (!buffApplied) {
            c.Strength += strengthMod;
            c.Intellect += intellectMod;
            c.Agility += agilityMod;
            c.Initiative += initiativeMod;
            c.Defense += defenseMod;
            c.Evasion += evasionMod;
            c.Resist += resistMod;
            buffApplied = true;
        }
    }

    public void buffWearOff(Character c) {
        if (buffApplied && duration <= 0) {
            c.Strength -= strengthMod;
            c.Intellect -= intellectMod;
            c.Agility -= agilityMod;
            c.Initiative -= initiativeMod;
            c.Defense -= defenseMod;
            c.Evasion -= evasionMod;
            c.Resist -= resistMod;
            buffApplied = false;
        }
    }

    public void applyDebuff(Character c) {
        if (!debuffApplied) {
            c.Strength -= strengthMod;
            c.Intellect -= intellectMod;
            c.Agility -= agilityMod;
            c.Initiative -= initiativeMod;
            c.Defense -= defenseMod;
            c.Evasion -= evasionMod;
            c.Resist -= resistMod;
            debuffApplied = true;
        }
    }

    public void debuffWearOff(Character c) {
        if (debuffApplied && duration <= 0) {
            c.Strength += strengthMod;
            c.Intellect += intellectMod;
            c.Agility += agilityMod;
            c.Initiative += initiativeMod;
            c.Defense += defenseMod;
            c.Evasion += evasionMod;
            c.Resist += resistMod;
            debuffApplied = false;
        }
    }

    public void applyTurnEffect(Character c) {
        if (DoT > 0) {
            c.HP -= DoT;
        }
        if (HoT > 0) {
            c.HP += HoT;
        }
    }

    public boolean isExpired() {
        return duration <= 0;
    }

     public static void updateStatusEffectsRecursive(List<StatusEffect> effects, Character c, int index) {
        if (index >= effects.size()) {
            return; // Base case: processed all effects
        }

        StatusEffect effect = effects.get(index);

        effect.applyBuff(c);
        effect.applyDebuff(c);
        effect.applyTurnEffect(c);

        effect.duration--;

        if (effect.isExpired()) {
            effect.buffWearOff(c);
            effect.debuffWearOff(c);
            effects.remove(index);
            // After removing current effect, recurse on same index because list shifted left
            updateStatusEffectsRecursive(effects, c, index);
        } else {
            // Move on to next effect
            updateStatusEffectsRecursive(effects, c, index + 1);
        }
    }
}
