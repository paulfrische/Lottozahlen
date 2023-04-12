package org.paulfrische;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicGameTest {
    @Test
    void excludeBadNumbers() {
        ArrayList badNumbers = new ArrayList<Short>();
        badNumbers.add((short)3);
        badNumbers.add((short)8);
        badNumbers.add((short)34);
        badNumbers.add((short)7);
        badNumbers.add((short)42);
        badNumbers.add((short)24);
        Game game = new ClassicGame(badNumbers);

        boolean contains = false;
        for (short num : game.generateNumbers()) {
            if (badNumbers.contains(num)) {
                contains = true;
                break;
            }
        }

        assertFalse(contains);
    }
}
