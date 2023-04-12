package org.paulfrische;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassicGame implements Game {
    private ArrayList<Short> badNumbers;
    private Random random;

    public ClassicGame(ArrayList<Short> badNumbers) {
        this.badNumbers = badNumbers;
        random = new Random();
    }

    @Override
    public ArrayList<Short> generateNumbers() {
        ArrayList<Short> numbers = (ArrayList<Short>) badNumbers.clone();
        boolean valid = false;
        while (!valid) {
            for (int i = 0; i < 6; i++) {
                numbers.set(i, (short) random.nextInt(50));
            }

            valid = true;
            for (short number: badNumbers) {
                if (numbers.contains(number)) {
                    valid = false;
                }
            }
        }

        return numbers;
    }

    @Override
    public ArrayList<Short> getBadNumbers() {
        return badNumbers;
    }
}
