package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.Scanner;

public class Day02 {

    public static void main(String[] args) {

        System.out.println(">>> Enter passwords list:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> passwordsList = new ArrayList<>();
        while (input.hasNextLine()){
            passwordsList.add(input.nextLine());
        }


        String[] passwords = passwordsList.toArray(new String[passwordsList.size()]);

        int validCounter = 0;
        int validCounter2 = 0;
        for (int index1 = 0; index1 < passwords.length; index1 ++) {
            int min = Integer.valueOf(passwords[index1].substring(0,passwords[index1].indexOf("-")).trim());
            int max = Integer.valueOf(passwords[index1].substring(passwords[index1].indexOf("-")+1, passwords[index1].indexOf(" ")).trim());
            String letter = passwords[index1].substring(passwords[index1].indexOf(" ")+1, passwords[index1].indexOf(":")).trim();
            String password = passwords[index1].substring(passwords[index1].indexOf(":")+1).trim();

            // part 1
            int repeatCounter = 0;
            for (int charIdx = 0; charIdx < password.length(); charIdx ++) {
                if (password.charAt(charIdx) == letter.charAt(0)) {
                    repeatCounter++;
                }
            }
            validCounter = repeatCounter >= min && repeatCounter <= max ? validCounter + 1 : validCounter;

            // part 2
            validCounter2 =
              min > 0 && min <= password.length() &&
                max > 0 && max <= password.length() &&
                (
                  (
                    password.charAt(min-1) == letter.charAt(0) &&
                      password.charAt(max-1) != letter.charAt(0)
                  ) ||
                    (
                      password.charAt(min-1) != letter.charAt(0) &&
                        password.charAt(max-1) == letter.charAt(0)
                    )
                ) ?
                validCounter2 + 1 :
                validCounter2;
        }

        System.out.println(">>> [part 1] valid passwords: " + validCounter);
        System.out.println(">>> [part 2] valid passwords: " + validCounter2);
    }
}
