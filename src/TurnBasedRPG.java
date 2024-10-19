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
        teamA.addCharacter(new Character("Bingus", 30, 8, 2));
        teamA.addCharacter(new Character("Cheeseball", 28, 7, 3));
        teamB.addCharacter(new Character("Bongo", 35, 10, 4));
        teamB.addCharacter(new Character("Gato", 40, 9, 5));
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
                System.out.println("2. View Status");

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