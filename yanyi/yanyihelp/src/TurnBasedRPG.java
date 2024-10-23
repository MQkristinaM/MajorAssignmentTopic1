import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        teamA.addCharacter(new Character("Bingus", "Sphynx", "Rouge", 30, 8, 4, 6));
        teamA.addCharacter(new Character("Cheeseball", "Ginger", "Wizard", 35, 7, 5, 5));
        teamB.addCharacter(new Character("Floppa", "Munchkin", "Warrior", 35, 9, 2, 7));
        teamB.addCharacter(new Character("Gato", "Caracal", "Thief", 40, 8, 3, 4));
    }

    public void battle() {
        // 获取所有角色并按速度排序，确保速度快的角色先出手
        List<Character> allCharacters = teamA.getSortedBySpeed();
        allCharacters.addAll(teamB.getSortedBySpeed());

        while (teamA.isAlive() && teamB.isAlive()) {
            for (Character character : allCharacters) {
                if (!character.isAlive()) {
                    continue; // 跳过死亡角色
                }

                // 根据角色所在队伍选择敌方队伍
                Team currentTeam = teamA.getCharacters().contains(character) ? teamA : teamB;
                Team opposingTeam = currentTeam == teamA ? teamB : teamA;

                Character defender = opposingTeam.getNextAliveCharacter();
                if (defender == null) break;

                // 处理状态效果
                character.updateStatusEffects();
                if (!character.isAlive()) {
                    continue; // 如果角色在应用状态效果后死亡，跳过
                }

                boolean actionChosen = false;

                // 让玩家选择有效操作，直到选择攻击、防御或治疗
                while (!actionChosen) {
                    System.out.println(character.getName() + "'s turn! Choose an action:");
                    System.out.println("1. Attack " + defender.getName());
                    System.out.println("2. Defend");
                    System.out.println("3. Heal");
                    System.out.println("4. View Status");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            // 攻击对手
                            int damage = character.attack();
                            int actualDamage = Math.max(0, damage - defender.getDefense());
                            defender.takeDamage(actualDamage);

                            System.out.println(character.getName() + " attacked " + defender.getName() + "!");
                            System.out.println(defender.getName() + " took " + actualDamage + " points of damage!");

                            // 检查对手是否被击败
                            if (!defender.isAlive()) {
                                System.out.println(defender.getName() + " has been defeated!");
                            } else {
                                // 有一定几率给对手施加状态效果：中毒或受伤
                                if (random.nextInt(100) < 30) { // 30% 概率中毒
                                    StatusEffect poison = new StatusEffect("Poison", 3, 1, 0, 0);
                                    defender.applyStatusEffect(poison);
                                    System.out.println(defender.getName() + " is now poisoned!");
                                }
                                if (random.nextInt(100) < 20) { // 20% 概率受伤
                                    StatusEffect wounded = new StatusEffect("Wounded", 3, 0, -2, -2);
                                    defender.applyStatusEffect(wounded);
                                    System.out.println(defender.getName() + " is now wounded, reducing attack and defense!");
                                }
                            }
                            actionChosen = true; // 攻击结束，选择了有效操作
                            break;

                        case 2:
                            // 角色进行防御
                            character.setDefending(true);
                            System.out.println(character.getName() + " is defending and will take less damage next turn!");
                            actionChosen = true; // 防御结束，选择了有效操作
                            break;

                        case 3:
                            // 恢复生命值
                            int healAmount = random.nextInt(6) + 5; // 随机恢复 5 到 10 点生命值
                            character.heal(healAmount);
                            System.out.println(character.getName() + " heals for " + healAmount + " points of health!");
                            actionChosen = true; // 治疗结束，选择了有效操作
                            break;

                        case 4:
                            // 查看当前角色状态，不结束回合
                            System.out.println(character);
                            for (StatusEffect effect : character.getStatusEffects()) {
                                System.out.println(effect);
                            }
                            // 不设置 actionChosen 为 true，因为我们希望角色重新选择操作
                            break;

                        default:
                            System.out.println("Invalid choice! Please select again.");
                    }
                }

                // 回合结束，重置防御状态
                character.setDefending(false);
            }

            // 每回合结束后，检查是否还有队伍存活
            if (!teamA.isAlive()) {
                System.out.println("Team B wins!");
                break;
            } else if (!teamB.isAlive()) {
                System.out.println("Team A wins!");
                break;
            }
        }
    }
}
