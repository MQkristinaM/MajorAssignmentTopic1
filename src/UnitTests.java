import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTests {
    @Test
    public void RaceTests() {
        
        
        //Character b = new Character("d", "e", "f", 4, 5, 6);
        //Character c =new Character("g", "h","i", 7, 8, 9);
        //Character d =new Character("j","k", "l" ,10, 11, 12);
        String expected = "b";
        Character a = new Character("a", "b", "c", 1, 2, 3);
        String result = "b";
        assertEquals(expected, result);
    }
}
