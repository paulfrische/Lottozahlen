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

public class Main {
    private static final Scanner stdin = new Scanner(System.in);
    private static final InputUtil input = new InputUtil(stdin);
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final BadNumberUtil badNumberUtil = new BadNumberUtil(input, stdin);
    public static void main(String[] args) throws Exception {
        logger.debug("start application");
        List<Short> badNumbers = badNumberUtil.getBadNumbersCached(args);
        boolean run = true;
        String gameType = input.choice("Game type:", new String[]{"Classic", "Eurojackpot"});
        logger.debug("Game Type: " + gameType);
        Game game;
        while (run) {
            if (gameType.equals("Eurojackpot")) {
                game = new Eurojackpot(badNumbers);
            } else {
                game = new ClassicGame(badNumbers);
            }
            game.printNumbers();
            run = input.binaryQuestion("Do you want to generate more numbers?");
            if (run) {
                badNumbers = badNumberUtil.getBadNumbers();
                logger.info("bad numbers " + badNumbers.toString());
            }
        }
        stdin.close();
    }

}