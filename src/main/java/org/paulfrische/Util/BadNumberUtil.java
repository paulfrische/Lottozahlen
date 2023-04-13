package org.paulfrische.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.paulfrische.ArgumentParser;
import org.paulfrische.GameException;
import org.paulfrische.Main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadNumberUtil {
    private InputUtil input;
    private Scanner stdin;
    private Logger logger = LogManager.getLogger(Main.class);
    public BadNumberUtil(InputUtil input, Scanner stdin) {
        this.input = input;
        this.stdin = stdin;
    }
    public List<Short> getBadNumbersCached(String[] args) throws Exception {
        logger.info("getting bad numbers (cached)");
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

    public List<Short> getBadNumbers() throws GameException {
        logger.info("getting bad numbers");
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
                    logger.warn(e.toString());
                    System.out.println(e.getMessage());
                }
            }
        }
        ArgumentParser parser = new ArgumentParser(arguments);
        badNumbers = parser.numbers(6, 49);
        return badNumbers;
    }
}
