package org.paulfrische;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ArgumentParser parser = new ArgumentParser(args);
        List badNumbers = parser.numbers(6);
        Game game = new ClassicGame(badNumbers);
        for (short num : game.generateNumbers()) {
            System.out.print(num + " ");
        }
    }
}