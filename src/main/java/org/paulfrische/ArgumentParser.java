package org.paulfrische;

import java.util.ArrayList;
import java.util.List;


public class ArgumentParser {
    private List<String> args;
    public ArgumentParser(List<String> args) {
        this.args = args;
    }

    public List<Short> numbers(int count, int max) throws GameException {
        List<Short> nums = new ArrayList<>();

        for (String arg : args) {
            try {
                short number = Short.parseShort(arg);
                if (number > max || number < 0) {
                    throw new GameException("number ´" + number + "´ is out of bounds");
                }
                nums.add(number);
            } catch (NumberFormatException e) {
                throw new GameException("Invalid number ´" + arg + "´");
            }
        }

        while (nums.size() < count) {
            nums.add((short) 0);
        }

        if (nums.size() > count) {
            throw new GameException("Too many numbers. Max: " + count + " Given: " + nums.size());
        }

        return nums;
    }
}
