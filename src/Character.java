import java.util.Random;

public class Character {
    private String name;
    private int healthPoints;
    private int strength;
    private int defense;

    public Character(String name, int healthPoints, int strength, int defense) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints < 0) this.healthPoints = 0;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public int attack() {
        Random random = new Random();
        return strength + random.nextInt(6); // Random damage between 0-5
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return name + " (HP: " + healthPoints + ")";
    }
}