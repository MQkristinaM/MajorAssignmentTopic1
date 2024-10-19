import java.util.ArrayList;
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
        for (Character character : characters) {
            if (character.isAlive()) {
                return character;
            }
        }
        return null;
    }

    public List<Character> getCharacters() {
        return characters;
    }
}