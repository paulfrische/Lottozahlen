package org.paulfrische;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> arguments = new ArrayList<>();
        if (args.length > 0){
             arguments = new ArrayList<>(List.of(args));
        } else {
            System.out.println("Enter your numbers (enter q to finish entering numbers)");
            Scanner scanner = new Scanner(System.in);
            List<String> numbers = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                System.out.print("[" + i + "] number: ");
                String input = scanner.nextLine();
                if (input.equals("q"))
                    break;
                numbers.add(input);
            }
        }
        ArgumentParser parser = new ArgumentParser(arguments);
        List badNumbers = parser.numbers(6);
        Game game = new ClassicGame(badNumbers);
        for (short num : game.generateNumbers()) {
            System.out.print(num + " ");
        }
    }
}