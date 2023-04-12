package org.paulfrische;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("numbers.json");
        Gson gson = new Gson();
        List<Short> badNumbers = new ArrayList();
        if (!file.exists()) {
            List<String> arguments = new ArrayList<>();
            if (args.length > 0){
                arguments = new ArrayList<>(List.of(args));
            } else {
                System.out.println("Enter your numbers (enter q to finish entering numbers)");
                Scanner scanner = new Scanner(System.in);
                for (int i = 0; i < 6; i++) {
                    System.out.print("[" + i + "] number: ");
                    String input = scanner.nextLine();
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
        }
        Game game = new ClassicGame(badNumbers);
        for (short num : game.generateNumbers()) {
            System.out.print(num + " ");
        }
    }
}