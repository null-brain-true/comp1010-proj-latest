public class Action {
    public String name;
    public String type; // e.g "attack", "heal", "dot", "hot", "buff", "debuff"
    public String damageType; // e.g "physical", "magical", "hybrid"

    public int damage = 0;
    public int heal = 0;
    public int damagePerTurn = 0;
    public int healPerTurn = 0;
    public int duration = 0;

    public int strengthMod = 0;
    public int intellectMod = 0;
    public int agilityMod = 0;
    public int initiativeMod = 0;
    public int defenseMod = 0;
    public int evasionMod = 0;
    public int resistMod = 0;

    public int hpCost = 0;
    public int spCost = 0;
    public int mpCost = 0;

    public Action(String name, String type, int damage, int heal, int damagePerTurn, int healPerTurn, int duration,
            int strengthMod, int intellectMod, int agilityMod, int initiativeMod,
            int defenseMod, int evasionMod, int resistMod, int hpCost, int spCost, int mpCost) {
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

        this.hpCost = hpCost;
        this.spCost = spCost;
        this.mpCost = mpCost;
    }

    public void effect(Character user, Character target, boolean isEnemy) {

        // Check if user has enough points to use the skill
        if (user.HP < hpCost || user.SP < spCost || user.MP < mpCost) {
            System.out.println(user.name + " does not have enough resources to use " + name);
            return; // or handle as failure
        }

        // Deduct costs from the caster
        user.HP -= hpCost;
        user.SP -= spCost;
        user.MP -= mpCost;

        if (type.equals("attack") || type.equals("dot") || type.equals("debuff")) {
            if (isEnemy) {
                apply(user, target);
            } else {
                System.out.println("Cannot use " + type + "  on allies.");
            }
        } else if (type.equals("heal") || type.equals("hot") || type.equals("buff")) {
            if (!isEnemy) {
                apply(user, target);
            } else {
                System.out.println("Cannot use " + type + " on enemies.");
            }
        } else {
            System.out.println("Unknown action type " + type);
        }
    }

    private void apply(Character user, Character target) {
        if (type.equals("attack")) {
            attackEffect(user, target);
        } else if (type.equals("heal")) {
            healEffect(user, target);
        } else if (type.equals("dot")) {
            dotEffect(user, target);
        } else if (type.equals("hot")) {
            hotEffect(user, target);
        } else if (type.equals("buff")) {
            buffEffect(user, target);
        } else if (type.equals("debuff")) {
            debuffEffect(user, target);
        }
    }

    public void attackEffect(Character user, Character target) {
        Dice dice = new Dice();
        int roll = dice.roll();

        if (roll == 20) {
            int rawDamage = calculateDamage(user, target);
            rawDamage *= 2;
            target.HP -= rawDamage;
            System.out.println(
                    "Critical Hit! " + user.name + " dealt " + rawDamage + " to " + target.name + " with " + name);
        } else if (roll <= 3) {
            System.out.println(user.name + "'s attack missed " + target.name + "!");
        } else if (roll <= 6) {
            int rawDamage = calculateDamage(user, target) / 2;
            rawDamage = Math.max(0, rawDamage);
            target.HP -= rawDamage;
            System.out.println(target.name + " blocked! " + user.name + " dealt " + rawDamage + " with " + name);
        } else {
            int rawDamage = calculateDamage(user, target);
            target.HP -= rawDamage;
            System.out.println(user.name + " dealt " + rawDamage + " to " + target.name + " with " + name);
        }
    }

    private int calculateDamage(Character user, Character target) {

        int rawDamage = 0;

        if (damageType.equals("physical")) {
            rawDamage = user.Strength - target.Defense + damage;
        } else if (damageType.equals("magical")) {
            rawDamage = user.Intellect - target.Resist + damage;
        } else if (damageType.equals("hybrid")) {
            int phys = user.Strength - target.Defense;
            int mag = user.Intellect - target.Resist;
            rawDamage = (phys + mag) / 2 + damage;
        }

        return Math.max(0, rawDamage);
    }

    public void healEffect(Character user, Character target) {
        target.HP += heal;
        System.out.println(user.name + " healed " + target.name + " for " + heal + " HP with " + name);
    }

    public void dotEffect(Character user, Character target) {
        target.HP -= damage;
        StatusEffect dot = new StatusEffect("DoT: " + name, damagePerTurn, 0, 0, 0, 0, 0, 0, 0, 0, duration);
        target.addStatusEffect(dot);
        System.out.println(user.name + " inflicted DoT on " + target.name + " with " + name);
    }

    public void hotEffect(Character user, Character target) {
        target.HP += heal;
        StatusEffect hot = new StatusEffect("HoT: " + name, 0, healPerTurn, 0, 0, 0, 0, 0, 0, 0, duration);
        target.addStatusEffect(hot);
        System.out.println(user.name + " applied HoT to " + target.name + " with " + name);
    }

    public void buffEffect(Character user, Character target) {
        StatusEffect buff = new StatusEffect("Buff: " + name, 0, 0,
                strengthMod, intellectMod, agilityMod, initiativeMod,
                defenseMod, evasionMod, resistMod, duration);
        target.addStatusEffect(buff);
        System.out.println(user.name + " buffed " + target.name + " with " + name);
    }

    public void debuffEffect(Character user, Character target) {
        StatusEffect debuff = new StatusEffect("Debuff: " + name, 0, 0,
                -strengthMod, -intellectMod, -agilityMod, -initiativeMod,
                -defenseMod, -evasionMod, -resistMod, duration);
        target.addStatusEffect(debuff);
        System.out.println(user.name + " debuffed " + target.name + " with " + name);
    }
}
