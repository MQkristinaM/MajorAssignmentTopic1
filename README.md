
## Group 5 work allocation:
- Liam 33% | core code structure start of project
- Emily 20% | enhanced classes and methods (defense), game text design
- Kristina 22% | compliling project files, game text design, uml diagram
- Yanyi (Candice) 25% | extra classes and methods (heal, defense), uml diagram


## How to run:

  ##  `Game.java`
  Run project through this file, it will prompt the debugger which displays the main game project. The use of scanner will allow the player to input commands.
  Current playable characters and current hp levels

  Each turn the player(s) will choose what action to proceed with.

  1. "Attack" opposition
  2. "Defend" current character
  3. "Heal" current character
  4. View current character's team

  when attacking, depending on the character's base strength will result in a randomised attack count.
  when defending, attacks from the opposing character will result in halfed damage count.
  when healing, will result in a randomised heal count.
  
  the game is complete when a team's respective characters reach 0 hp. The winning team wins the game.


## Other project files:

  ##  `TurnBasedRPG.java`
  This is the primary file responsible for managing the flow of the turn-based battle system. It handles the game loop, ensuring that each character takes their turn in sequence. The file displays the current state of the battle, including the active characters and their respective stats such as health points, attack power, and defense ratings. It also manages user 
  input, allowing players to select actions for their characters, such as attacking, using items, or defending. Additionally, this file might implement the logic for determining the outcome of each action, including calculating damage dealt and updating character states accordingly.

  ##  `Character.java`
  This file defines the Character class, which encapsulates all the attributes and behaviors of the characters in the game. It includes methods for initializing character stats, such as health, attack, and defense, as well as methods for executing actions like attacking an opponent, defending, and using items. The class may also handle status effects (e.g., poison, 
  stun) and provide functionality to check the character's current state. Overall, it serves as the blueprint for creating and managing individual characters within the game.

  ##  `Team.java`
  The Team file is dedicated to managing a collection of characters, representing a team in the battle. It includes methods for adding and removing characters from the team, as well as retrieving information about team members, such as their collective health and status. This file may also implement strategic functionalities, like coordinating team actions, 
  determining the order of turns, and facilitating communication between characters during battle. The Team class is essential for organizing the characters into cohesive groups, allowing for more complex team-based strategies in combat.




