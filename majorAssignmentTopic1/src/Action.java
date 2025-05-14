public class Action {
    public String name;
    public String type; //e.g "attack", "heal", "dot", "hot", "buff", "debuff"

    public int damage = 0;
    public int heal = 0;
    public int damagePerTurn = 0;
    public int healPerTurn = 0;
    public int duration = 0;

    public int strengthMod = 0;
    public int intellectMod = 0;
    public int agilityMod = 0;
    public int initiativeMod =0;
    public int defenseMod = 0;
    public int evasionMod = 0;
    public int resistMod = 0;

    public Action(String name, String type, int damage, int heal, int damagePerTurn, int healPerTurn, int duration, 
                int strengthMod, int intellectMod, int agilityMod, int initiativeMod,
                int defenseMod, int evasionMod, int resistMod){
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.heal = heal;
        this.damagePerTurn = damagePerTurn;
        this.healPerTurn = healPerTurn;
        this.duration = duration;

        this.strengthMod = strengthMod;
        this.intellectMod = intellectMod;
        this.agilityMod = agilityMod;
        this.initiativeMod = initiativeMod;
        this.defenseMod = defenseMod;
        this.evasionMod = evasionMod;
        this.resistMod = resistMod;
    }

    public void effect(Character target){
        if(type.equals("attack")){
            attackEffect(target);
        } else if (type.equals("heal")){
            healEffect(target);
        } else if (type.equals("dot")){
            dotEffect(target);
        } else if (type.equals("hot")){
            hotEffect(target);
        } else if (type.equals("buff")){
            buffEffect(target);
        } else if (type.equals("debuff")){
            debuffEffect(target);
        }
    }

    public void attackEffect(Character target){
        target.HP -= damage;
    }

    public void healEffect(Character target){
        target.HP += heal;
    }

    public void dotEffect(Character target){
        target.HP -= damage;
        StatusEffect dot = new StatusEffect("DoT: " + name, damagePerTurn, 0, 0, 0, 0, 0, 0, 0, 0, duration);
        target.addStatusEffect(dot);
    }

    public void hotEffect(Character target){
        target.HP += heal;
        target.HP += healPerTurn;
    }

    public void buffEffect(Character target){
        target.
    }

    public void debuffEffect(Character target){
        
    }
}
