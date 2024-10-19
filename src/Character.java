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
        Random randoma = new Random();
        return strength + randoma.nextInt(6); // Random damage between 0-5
    }

    public int getDefense() {
        return defense;
    }

    // public int defending( int i){
    //     // Random randomd = new Random();
    //     // return randomd(i);
    // }

    @Override
    public String toString() {
        return name + " (HP: " + healthPoints + ")";
    }
}