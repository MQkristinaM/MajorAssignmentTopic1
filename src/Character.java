import java.util.Random;

public class Character {
    private String name;
    private String race;
    private String characterClass;
    private int healthPoints;
    private int maxHealthPoints;
    private int strength;
    private int defense;
    private boolean defending;

    public Character(String name, String race, String characterClass, int healthPoints, int strength, int defense) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
        this.defending = false;
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

    public void takeDamage(int damage) {
        if (defending) {
            damage -= defense;
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
        Random randoma = new Random();
        return strength + randoma.nextInt(6); // Random damage between 0-5
    }

    public int getDefense() {
        return defense;
    }

    public void heal(int healAmount) {
        healthPoints += healAmount;
        if (healthPoints > maxHealthPoints) {
                healthPoints = maxHealthPoints;
                System.out.println(" ");
                System.out.println(name + " is already at max health!");
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

    @Override
    public String toString() {
        return name + " (Race: " + race +
                ", Class: " + characterClass +
                ", HP: " + healthPoints + "/" + maxHealthPoints +
                ", Strength: " + strength +
                ", Defense: " + defense +
                ", Defending: " + (defending ? "Yes" : "No") + ")";
    }
}