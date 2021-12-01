package com.bbjski.aoc.y2020;

import java.util.Scanner;

public class Day06 {

    public static void main(String[] args) {

        System.out.println(">>> Enter answers:");

        Scanner input = new Scanner(System.in);

        String answers = "";
        String allAnswers = "";
        int checksum = 0;
        int allYesChecksum = 0;
        int groupCount = 0;
        while (input.hasNextLine()){
            String line = input.nextLine();
            if (line == null || line.length() == 0) {
                checksum += answers.length();
                for (char letter : answers.toCharArray()) {
                    allYesChecksum += countChar(allAnswers, letter) == groupCount ? 1 : 0;
                }

                answers = "";
                allAnswers = "";
                groupCount = 0;
            } else {
                char[] atomicAnswers = line.toCharArray();
                for (char answer : atomicAnswers) {
                    answers = answers.indexOf(answer) >= 0 ? answers : (answers + answer);
                    allAnswers += answer;
                }
                groupCount++;
            }
        }
        if (answers != null && answers.length() > 0) {
            checksum += answers.length();
            for (char letter : answers.toCharArray()) {
                int answersCount = countChar(allAnswers, letter);
                allYesChecksum = answersCount == groupCount ? allYesChecksum + 1 : allYesChecksum;
            }
        }

        System.out.println(">>> [part 1] answers checksum: " + checksum);
        System.out.println(">>> [part 2] all yes checksum: " + allYesChecksum);
    }


    public static int countChar(String text, char c) {
        int count = 0;

        for(int i=0; i < text.length(); i++) {
            if(text.charAt(i) == c) {
                count++;
            }
        }

        return count;
    }
}
