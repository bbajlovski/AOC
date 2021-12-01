package com.bbjski.aoc.y2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Day01 {

    public static void main(String[] args) {

        System.out.println(">>> Enter measurments:");

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> measurmentsList = new ArrayList<>();
        while (input.hasNextLine()){
            measurmentsList.add(Integer.parseInt(input.nextLine()));
        }


        Integer[] measurements = measurmentsList.toArray(new Integer[measurmentsList.size()]);

        int numOfIncreases = 0;
        int numOfWindowIncreases = 0;
        for (int index = 1; index < measurements.length; index++) {
            // part 1
            if (measurements[index] > measurements[index - 1]) {
                numOfIncreases++;
            }

            // part 2
            if (index >= 3){
                int window1 = measurements[index - 3] + measurements[index - 2] + measurements[index - 1];
                int window2 = measurements[index - 2] + measurements[index - 1] + measurements[index];
                if (window2 > window1) {
                    numOfWindowIncreases++;
                }
            }
        }

        System.out.println(">>> [part 1]: " + numOfIncreases);
        System.out.println(">>> [part 2]: " + numOfWindowIncreases);
    }

}
