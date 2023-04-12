package org.paulfrische;

import java.util.ArrayList;
import java.util.List;


public class ArgumentParser {
    private String[] args;
    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public List<Short> numbers(int count) throws Exception {
        List<Short> nums = new ArrayList<>();

        for (String arg : args) {
            try {
                short number = Short.parseShort(arg);
                nums.add(number);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid number ´" + arg + "´");
            }
        }

        while (nums.size() < count) {
            nums.add((short) 0);
        }

        if (nums.size() > count) {
            throw new Exception("Too many numbers. Max: " + count + " Given: " + nums.size());
        }

        return nums;
    }
}
