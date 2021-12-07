package com.bbjski.aoc.y2021;

import com.bbjski.aoc.utils.Arithmetic;

import java.util.Arrays;
import java.util.Scanner;

public class Day07 {

    public static void main(String[] args) {

        System.out.println(">>> Enter horizontal positions:");

        Scanner input = new Scanner(System.in);

        int[] horizontalPositions = null;
        while (input.hasNextLine()){
            horizontalPositions =
              Arrays
                .stream(input.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray(); ;
        }

        int maxPos = Arithmetic.maxInArray(horizontalPositions);
        long sum1 = 0;
        long sum2 = 0;

        if (maxPos > -1) {
            for (int fuelPos = 0; fuelPos <= maxPos; fuelPos++) {
                long fuelSum1 = 0;
                long fuelSum2 = 0;
                for (int position : horizontalPositions) {
                    fuelSum1 += Math.abs(position - fuelPos);

                    for (int distance = 1; distance <= Math.abs(position - fuelPos); distance++) {
                        fuelSum2 += distance;
                    }
                }
                if (fuelPos == 0) {
                    sum1 = fuelSum1;
                    sum2 = fuelSum2;
                } else {
                    if (fuelSum1 < sum1) {
                        sum1 = fuelSum1;
                    }
                    if (fuelSum2 < sum2) {
                        sum2 = fuelSum2;
                    }
                }
            }
        }

        System.out.println(">>> [part 1]: " + sum1);
        System.out.println(">>> [part 2]: " + sum2);
    }

}
