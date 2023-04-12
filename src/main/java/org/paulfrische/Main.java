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
    private static BadNumberUtil badNumberUtil = new BadNumberUtil(input, stdin);
    public static void main(String[] args) throws Exception {
        List<Short> badNumbers = badNumberUtil.getBadNumbersCached(args);
        boolean run = true;
        while (run) {
            Game game = new ClassicGame(badNumbers);
            for (short num : game.generateNumbers()) {
                System.out.print(num + " ");
            }
            System.out.println("");
            run = input.binaryQuestion("Do you want to generate more numbers?");
            if (run) {
                badNumbers = badNumberUtil.getBadNumbers();
            }
        }
        stdin.close();
    }

}