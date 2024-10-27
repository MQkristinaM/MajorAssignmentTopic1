import java.util.Scanner;
import java.util.Random;

public class TurnBasedRPG {
    private Team teamA;
    private Team teamB;
    private Scanner scanner;
    private Random random;

    public TurnBasedRPG() {
        teamA = new Team();
        teamB = new Team();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void setup() {
        teamA.addCharacter(new Character("Bingus", "Sphynx", "Rouge", 30, 8, 4));
        teamA.addCharacter(new Character("Cheeseball", "Ginger", "Wizard", 35, 7, 5));
        teamB.addCharacter(new Character("Floppa", "Caracal","Paladin", 35, 9, 2));
        teamB.addCharacter(new Character("Gato","Munchkin", "Thief" ,40, 8, 3));
    }

    public void battle() {
        Team currentAttacker = teamA;
        Team currentDefender = teamB;

        while (teamA.isAlive() && teamB.isAlive()) {
            Character attacker = currentAttacker.getNextAliveCharacter();
            Character defender = currentDefender.getNextAliveCharacter();

            if (attacker != null && defender != null) {
                System.out.println("------------------------------");
                System.out.print("Attacker  " + attacker.getName() + " " + attacker.getHealthPoints() + "hp [" );
                for(int i = 0 ; i < attacker.getHealthPoints(); i++){
                    System.out.print("■");
                }
                System.out.println("] ");
                System.out.println(" ");
                System.out.print("Defender  " + defender.getName() + " " + defender.getHealthPoints() + "hp [" );
                for(int i = 0 ; i < defender.getHealthPoints(); i++){
                    System.out.print("■");
                }
                System.out.println("] ");
                System.out.println(" ");
                System.out.println(attacker.getName() + "'s turn! Choose an action:");
                System.out.println("1. Attack " + defender.getName());
                System.out.println("2. Defend " + attacker.getName());
                System.out.println("3. Heal " + attacker.getName());
                System.out.println("4. View Status of team");

                int choice = scanner.nextInt();
                if (choice == 1) {
                    int damage = attacker.attack();
                    int actualDamage = Math.max(0, damage - defender.getDefense());
                    defender.takeDamage(actualDamage);

                    System.out.println(" ");
                    System.out.println(attacker.getName() + " attacked " + defender.getName() + "!");
                    System.out.println(defender.getName() + " took " + actualDamage + " points of damage!");

                    if (!defender.isAlive()) {
                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println("░ ▒ ▓" + defender.getName() + " has been defeated!" + "▓ ▒ ░");
                        System.out.println(" ");
                        System.out.println(" ");
                        if(currentDefender.getNextAliveCharacter() != null){
                        System.out.println("A new challenger has arrived!");
                        System.out.println(currentDefender.getNextAliveCharacter());
                        }
                    }
                } else if (choice == 2) {
                    attacker.setDefending(true);
                    System.out.println(" ");
                    System.out.println(attacker.getName() + " is defending and will take less damage next turn!");
                
                } else if (choice == 3) {
                    int healAmount = random.nextInt(6) + 5;
                    attacker.heal(healAmount);
                    System.out.println(" ");
                    System.out.println(attacker.getName() + " heals for " + healAmount + " points of health!");
            
                } else if (choice == 4) {
                    System.out.println(" ");
                    System.out.println("Current Status of team:");
                    System.out.println(" ");                    
                    displayStatus(currentAttacker);
                    System.out.println(" ");                    
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
            attacker.setDefending(false);
        }

        if (teamA.isAlive()) {
            System.out.println("░ ▒ ▓ Team A wins! ▓ ▒ ░");
            System.out.println(" "); 
        } else {
            System.out.println("░ ▒ ▓ Team B wins! ▓ ▒ ░");
            System.out.println(" "); 
        }
    }

    private void displayStatus(Team team) {
        for (Character character : team.getCharacters()) {
            System.out.println(character);
        }
    }
}