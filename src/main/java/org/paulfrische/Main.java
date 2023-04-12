package org.paulfrische;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner stdin = new Scanner(System.in);
    private static InputUtil input = new InputUtil(stdin);
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
        File file = new File("numbers.json");
        Gson gson = new Gson();
        List<Short> badNumbers = new ArrayList();
        boolean reuse = false;

        if (file.exists() && args.length == 0) {
            reuse = input.binaryQuestion("There are old numbers, do you want to reuse them?");
        }

        if (!reuse) {
            List<String> arguments = new ArrayList<>();
            if (args.length > 0){
                arguments = new ArrayList<>(List.of(args));
            } else {
                System.out.println("Enter your numbers (enter q to finish entering numbers)");
                for (int i = 0; i < 6; i++) {
                    System.out.print("[" + i + "] number: ");
                    String input = stdin.nextLine();
                    if (input.equals("q"))
                        break;
                    arguments.add(input);
                }
            }
            ArgumentParser parser = new ArgumentParser(arguments);
            badNumbers = parser.numbers(6);
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

    public static List<Short> getBadNumbers() throws Exception {
        List<Short> badNumbers = new ArrayList();

        List<String> arguments = new ArrayList<>();

            System.out.println("Enter your numbers (enter q to finish entering numbers)");
            for (int i = 0; i < 6; i++) {
                System.out.print("[" + i + "] number: ");
                String input = stdin.nextLine();
                if (input.equals("q"))
                    break;
                arguments.add(input);
            }
        ArgumentParser parser = new ArgumentParser(arguments);
        badNumbers = parser.numbers(6);
        return badNumbers;
    }
}