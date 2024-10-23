import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Character {
    private String name; //名称
    private String race; //比赛
    private String characterClass; //角色
    private int healthPoints; //当前生命值
    private int maxHealthPoints; //最大生命值
    private int strength; //攻击力
    private int defense; //防御力
    private int speed; //速度
    private boolean defending; //是否防御
    private List<StatusEffect> statusEffects; //状态

    public Character(String name, String race, String characterClass, int healthPoints, int strength, int defense, int speed) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
        this.speed = speed;
        this.defending = false;
        this.statusEffects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public int getSpeed() {
        return speed;
    }

    public void takeDamage(int damage) {
        if (defending) {
            damage -= defense; // 防御时伤害减去掉防御力
            if(damage<0) damage=0;
            System.out.println(name + " defended and reduced damage to " + damage + "!");
        }
        this.healthPoints -= damage;
        if (this.healthPoints < 0) this.healthPoints = 0;
    }
    public void takeDamage(int damage,String name) {
        if (defending) {
            damage /= 2; // 防御时中毒效果受伤减半
            System.out.println(name + " defended and reduced damage to " + damage + "!");
        }
        this.healthPoints -= damage;
        if (this.healthPoints < 0) this.healthPoints = 0;
    }
    public void reduce(int attack,int defenseValue) {
        if (defending) {
            attack /= 2; // 防御时效果减半
            defenseValue /= 2;
            }
        this.strength+=attack;
        this.defense+=defenseValue;
        if(this.strength<0) this.strength=0;
        if(this.defense<0) this.defense=0;
        System.out.println(name + " defended and reduced strength to " + strength + ",defense to "+defense+"!");

    }


    public boolean isAlive() {
        return healthPoints > 0;
    }

    public int attack() {
        Random random = new Random();
        int modifiedAttack = strength + random.nextInt(6); // 基础攻击 + 随机0-5

        for (StatusEffect effect : statusEffects) {
            modifiedAttack += effect.getAttackModifier(); // 根据状态修改攻击力
        }

        return Math.max(0, modifiedAttack); // 保证攻击力不为负数
    }

    public int getDefense() {
        int modifiedDefense = defense;
        for (StatusEffect effect : statusEffects) {
            modifiedDefense += effect.getDefenseModifier(); // 根据状态修改防御力
        }
        return Math.max(0, modifiedDefense); // 保证防御力不为负数
    }

    public void applyStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
        System.out.println(name + " is affected by " + effect.getName() + "!");
    }

    public void updateStatusEffects() {
        List<StatusEffect> expiredEffects = new ArrayList<>();

        for (StatusEffect effect : statusEffects) {
            if (effect.getDamagePerTurn() > 0) {
                takeDamage(effect.getDamagePerTurn(),effect.getName());
                System.out.println(name + " takes " + effect.getDamagePerTurn() + " damage from " + effect.getName() + "!");
            }else{
                //受伤的情况
                reduce(effect.getAttackModifier(),effect.getDefenseModifier());
            }

            effect.reduceDuration();
            if (effect.isExpired()) {
                expiredEffects.add(effect);
            }
        }

        statusEffects.removeAll(expiredEffects);
        for (StatusEffect effect : expiredEffects) {
            System.out.println(name + " is no longer affected by " + effect.getName() + "!");
        }
    }
    public void heal(int healAmount) {
        healthPoints += healAmount;
        if (healthPoints > maxHealthPoints) {
            healthPoints = maxHealthPoints; // 确保生命值不会超出上限
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public void setStatusEffects(List<StatusEffect> statusEffects) {
        this.statusEffects = statusEffects;
    }

    @Override
    public String toString() {
        return name + " (Race: " + race +
                ", Class: " + characterClass +
                ", HP: " + healthPoints + "/" + maxHealthPoints +
                ", Strength: " + strength +
                ", Defense: " + defense +
                ", Speed: " + speed +
                ", Defending: " + (defending ? "Yes" : "No") + ")";
    }

}
