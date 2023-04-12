package org.paulfrische;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Scanner stdin = new Scanner(System.in);
    private static InputUtil input = new InputUtil(stdin);
    private static Logger logger = Logger.getLogger("org.paulfrische");
    public static void main(String[] args) throws Exception {
        List<Short> badNumbers = getBadNumbersCached(args);
        boolean run = true;
        while (run) {
            Game game = new ClassicGame(badNumbers);
            for (short num : game.generateNumbers()) {
                System.out.print(num + " ");
            }
            System.out.println("");
            run = input.binaryQuestion("Do you want to generate more numbers?");
            if (run) {
                badNumbers = getBadNumbers();
            }
        }
        stdin.close();
    }

    public static List<Short> getBadNumbersCached(String[] args) throws Exception {
        logger.fine("getting bad numbers (cached)");
        File file = new File("numbers.json");
        Gson gson = new Gson();
        List<Short> badNumbers = new ArrayList();
        boolean reuse = false;

        if (file.exists() && args.length == 0) {
            reuse = input.binaryQuestion("There are old numbers, do you want to reuse them?");
        }

        if (!reuse) {
            badNumbers = getBadNumbers();
            String data = gson.toJson(badNumbers);
            file.createNewFile();
            FileWriter writer = new FileWriter(file.getName());
            writer.write(data);
            writer.close();
        } else {
            Scanner reader = new Scanner(file);
            String data = reader.nextLine();
            TypeToken<ArrayList<Short>> shortList = new TypeToken<>(){};
            badNumbers = gson.fromJson(data, shortList);
            reader.close();
        }
        return badNumbers;
    }

    public static List<Short> getBadNumbers() throws GameException {
        logger.fine("getting bad numbers");
        List<Short> badNumbers = new ArrayList();

        List<String> arguments = new ArrayList<>();

        System.out.println("Enter your numbers (enter q to finish entering numbers)");
        boolean leave = false;
        for (int i = 0; i < 6; i++) {
            if (leave)
                break;
            boolean done = false;
            while (!done) {
                try {
                    System.out.print("[" + i + "] number: ");
                    String input = stdin.nextLine();
                    if (input.equals("q")) {
                        leave = true;
                        break;
                    }
                    ArgumentParser.singleNumber(input, 49);
                    done = true;
                    arguments.add(input);
                } catch (GameException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ArgumentParser parser = new ArgumentParser(arguments);
        badNumbers = parser.numbers(6, 49);
        return badNumbers;
    }
}