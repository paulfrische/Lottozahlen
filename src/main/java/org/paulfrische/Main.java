package org.paulfrische;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.paulfrische.Games.ClassicGame;
import org.paulfrische.Games.Eurojackpot;
import org.paulfrische.Games.Game;
import org.paulfrische.Util.BadNumberUtil;
import org.paulfrische.Util.InputUtil;

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
        String gameType = input.choice("Game type:", new String[]{"Classic", "Eurojackpot"});
        Game game;
        while (run) {
            switch (gameType) {
                case "Eurojackpot":
                    game = new Eurojackpot(badNumbers);
                    break;
                default:
                    game = new ClassicGame(badNumbers);
            }
            List<Short> numbers = game.generateNumbers();
            for (short num : numbers) {
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