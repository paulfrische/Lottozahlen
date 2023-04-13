package org.paulfrische.Util;

import java.util.Scanner;

public class InputUtil {
    private Scanner stdin;
    public InputUtil(Scanner stdin) {
        this.stdin = stdin;
    }
    public boolean binaryQuestion(String prompt) {
        System.out.print(prompt + "\n[Y/n] ");
        String input = stdin.nextLine();
        if (input.toLowerCase() == "y" || input == "") {
            return true;
        }
        return false;
    }

    public String choice(String prompt, String[] options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println("[" + i + "] " + options[i]);
        }
        String input = stdin.nextLine();
        int index = Integer.parseInt(input);
        return options[index];
    }
}
