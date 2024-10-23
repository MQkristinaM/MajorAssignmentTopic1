import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Team {
    private List<Character> characters;

    public Team() {
        characters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public boolean isAlive() {
        return characters.stream().anyMatch(Character::isAlive);
    }

    public Character getNextAliveCharacter() {
        return characters.stream().filter(Character::isAlive).findFirst().orElse(null);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    // 按照速度排序角色
    public List<Character> getSortedBySpeed() {
        characters.sort(Comparator.comparingInt(Character::getSpeed).reversed());
        return characters;
    }
}
