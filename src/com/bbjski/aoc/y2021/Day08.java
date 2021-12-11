package com.bbjski.aoc.y2021;

import com.bbjski.aoc.model.Digit;

import java.util.Scanner;

public class Day08 {

    public static void main(String[] args) {

        System.out.println(">>> Enter signal patterns:");

        Scanner input = new Scanner(System.in);
        int counter1 = 0;
        int sum2 = 0;
        while (input.hasNextLine()){
            String line = input.nextLine();

            String[] outputPattern = line.split("\\|")[1].trim().split("\\s+");
            String[] inputPattern = line.split("\\|")[0].trim().split("\\s+");

            Digit digit = new Digit();
            digit.training(inputPattern);
            digit.training(outputPattern);

            String outputSequence = "";
            for (String wires : outputPattern) {
                int digitByCount = digit.getDigitByCount(wires);
                counter1 += (digitByCount > -1 ? 1 : 0);
                outputSequence = outputSequence + digit.getDigit(wires);
            }
            sum2 += Integer.parseInt(outputSequence);
        }

        System.out.println(">>> [part 1]: " + counter1);
        System.out.println(">>> [part 2]: " + sum2);
    }


}
