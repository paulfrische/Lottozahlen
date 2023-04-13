package org.paulfrische.Games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Eurojackpot implements Game {
    private final ArrayList<Short> badNumbers;
    private final Random random;

    public Eurojackpot(List<Short> badNumbers) {
        this.badNumbers = (ArrayList<Short>) badNumbers;
        random = new Random();
    }

    @Override
    public List<Short> generateNumbers() {
        List<Short> numbers = new ArrayList<>(List.of(badNumbers.get(0), badNumbers.get(0), badNumbers.get(0), badNumbers.get(0), badNumbers.get(0), badNumbers.get(0), badNumbers.get(0)));
        boolean valid = false;
        while (!valid) {
            for (int i = 0; i < 5; i++) {
                numbers.set(i, (short) random.nextInt(50));
            }

            numbers.set(5, (short)random.nextInt(10));
            numbers.set(6, (short)random.nextInt(10));

            // check for bad numbers
            valid = true;
            for (short number : badNumbers) {
                if (numbers.contains(number)) {
                    valid = false;
                    break;
                }
            }

            // check for duplicates
            for (int i = 0; i < numbers.size(); i++) {
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (numbers.get(i) == numbers.get(j)) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        return numbers;
    }

    @Override
    public void printNumbers() {
        List<Short> numbers = generateNumbers();
        List<Short> normalNumbers = new ArrayList<>(numbers.subList(0, 5));
        Collections.sort(normalNumbers);
        for (int i = 0; i < 5; i++) {
            System.out.print(normalNumbers.get(i) + " ");
        }
        List<Short> superNumbers = new ArrayList<>(numbers.subList(5, 7));
        Collections.sort(superNumbers);
        System.out.println("\033[33;1m" + superNumbers.get(0) + " " + superNumbers.get(1) + "\033[0m");
    }
    @Override
    public List<Short> getBadNumbers() {
        return badNumbers;
    }
}
