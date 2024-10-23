public class StatusEffect {
    private String name;
    private int duration;  // 持续回合数
    private int damagePerTurn;  // 每回合造成的伤害
    private int attackModifier;  // 攻击力变化
    private int defenseModifier;  // 防御力变化

    public StatusEffect(String name, int duration, int damagePerTurn, int attackModifier, int defenseModifier) {
        this.name = name;
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void reduceDuration() {
        if (duration > 0) {
            duration--;
        }
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    public int getDamagePerTurn() {
        return damagePerTurn;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    @Override
    public String toString() {
        return name + " (Duration: " + duration + " turns)";
    }
}
