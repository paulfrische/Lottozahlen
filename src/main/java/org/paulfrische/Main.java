package org.paulfrische;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList badNumbers = new ArrayList<Short>();
        badNumbers.add((short)3);
        badNumbers.add((short)8);
        badNumbers.add((short)34);
        badNumbers.add((short)7);
        badNumbers.add((short)42);
        badNumbers.add((short)24);
        Game game = new ClassicGame(badNumbers);
        for (short num : game.generateNumbers()) {
            System.out.print(num + " ");
        }
    }
}