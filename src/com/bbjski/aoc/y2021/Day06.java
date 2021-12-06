package com.bbjski.aoc.y2021;

import java.util.Scanner;

public class Day06 {

    public static void main(String[] args) {

        System.out.println(">>> Enter initial state:");

        Scanner input = new Scanner(System.in);
        String lanternsState = null;
        while (input.hasNextLine()){
            lanternsState = input.nextLine();
        }

        long[] lanternsCounter = new long[9];

        for (String lantern : lanternsState.split(",")) {
            lanternsCounter[Integer.parseInt(lantern)]++;
        }

        long sum80 = 0;
        long sum256 = 0;
        for (int day = 0; day < 256; day++) {
            long[] newCounter = new long[9];
            newCounter[8] = lanternsCounter[0];
            newCounter[7] = lanternsCounter[8];
            newCounter[6] = lanternsCounter[0] + lanternsCounter[7];
            newCounter[5] = lanternsCounter[6];
            newCounter[4] = lanternsCounter[5];
            newCounter[3] = lanternsCounter[4];
            newCounter[2] = lanternsCounter[3];
            newCounter[1] = lanternsCounter[2];
            newCounter[0] = lanternsCounter[1];

            lanternsCounter = newCounter;

            if (day == 79) {
                for (long lantern : lanternsCounter) {
                    sum80 += lantern;
                }
            }

            if (day == 255) {
                for (long lantern : lanternsCounter) {
                    sum256 += lantern;
                }
            }
        }

        System.out.println(">>> [part 1]: " + sum80);
        System.out.println(">>> [part 2]: " + sum256);
    }

}
