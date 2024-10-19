import java.util.Scanner;

public class TurnBasedRPG {
    private Team teamA;
    private Team teamB;
    private Scanner scanner;

    public TurnBasedRPG() {
        teamA = new Team();
        teamB = new Team();
        scanner = new Scanner(System.in);
    }


    public void setup() {
        teamA.addCharacter(new Character("Bingus", 30, 8, 4));
        teamA.addCharacter(new Character("Cheeseball", 35, 7, 5));
        teamB.addCharacter(new Character("Floppa", 35, 9, 2));
        teamB.addCharacter(new Character("Gato", 40, 8, 3));
    }

    public void battle() {
        Team currentAttacker = teamA;
        Team currentDefender = teamB;

        while (teamA.isAlive() && teamB.isAlive()) {
            Character attacker = currentAttacker.getNextAliveCharacter();
            Character defender = currentDefender.getNextAliveCharacter();

            if (attacker != null && defender != null) {
                System.out.println(attacker.getName() + "'s turn! Choose an action:");
                System.out.println("1. Attack " + defender.getName());
                System.out.println("2. Defend " + attacker.getName());
                System.out.println("3. View Status");

                int choice = scanner.nextInt();
                if (choice == 1) {
                    int damage = attacker.attack();
                    int actualDamage = Math.max(0, damage - defender.getDefense());
                    defender.takeDamage(actualDamage);

                    System.out.println(attacker.getName() + " attacked " + defender.getName() + "!");
                    System.out.println(defender.getName() + " took " + actualDamage + " points of damage!");

                    if (!defender.isAlive()) {
                        System.out.println(defender.getName() + " has been defeated!");
                    }
                } else if (choice == 2) {
                    // defense variable - next attack
                  //  int defend = defender.defend();
                  System.out.println(defender.getName() + " is preparing defenses");

                    
                } else if (choice == 3) {
                    System.out.println("Current Status:");
                    displayStatus(currentAttacker);
                    displayStatus(currentDefender);
                    continue; // Skip the turn if viewing status
                } else {
                    System.out.println("Invalid choice, please try again.");
                    continue; // Re-prompt the action
                }

                // Swap teams
                Team temp = currentAttacker;
                currentAttacker = currentDefender;
                currentDefender = temp;

                System.out.println();
            }
        }

        if (teamA.isAlive()) {
            System.out.println("Team A wins!");
        } else {
            System.out.println("Team B wins!");
        }
    }

    private void displayStatus(Team team) {
        for (Character character : team.getCharacters()) {
            System.out.println(character);
        }
    }
}