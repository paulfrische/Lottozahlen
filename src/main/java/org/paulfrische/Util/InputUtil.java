package org.paulfrische.Util;

import java.util.Scanner;

public class InputUtil {
    private Scanner stdin;
    public InputUtil(Scanner stdin) {
        this.stdin = stdin;
    }
    public boolean binaryQuestion(String prompt) {
        System.out.print(prompt + "\n[Y/n] ");
        String input = "";
        input = stdin.nextLine();
        if (input.toLowerCase() == "y" || input == "") {
            return true;
        }
        return false;
    }
}
