package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.Scanner;

public class Day13 extends Thread {

    public static void main(String[] args) throws Exception {

        System.out.println(">>> Enter lines:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        long timestamp = Long.valueOf(lines.get(0));
        int tst = Integer.valueOf(String.valueOf(timestamp));
        String[] busses = lines.get(1)
          .replaceAll(",x","")
          .replaceAll("x,", "")
          .split(",");

        int[] busLines = new int[busses.length];
        int[] busMinutes = new int[busses.length];
        int counter = 0;
        for (String line : busses) {
            busLines[counter] = Integer.valueOf(line);
            busMinutes[counter] = tst % busLines[counter];
            counter++;
        }

        String[] fullBusses = lines.get(1).split(",");
        int[] fullBusLines = new int[fullBusses.length];
        int fullCounter = 0;
        for (String line : fullBusses) {
            if (line.equalsIgnoreCase("x")) {
                fullBusLines[fullCounter] = 0;
            } else {
                fullBusLines[fullCounter] = Integer.valueOf(line);
            }
            fullCounter++;
        }

        long product = getProduct(timestamp, busLines, busMinutes);
        long earliest = getEarliest(timestamp, fullBusLines);

        System.out.println(">>> [part 1] product: " + product);
        System.out.println(">>> [part 2] earliest: " + earliest);
    }

    private static long getProduct(long timestamp, int[] busLines, int[] busMinutes) {
        boolean wait = true;
        int busLine = -1;
        long timestampEnd = timestamp;
        while (wait) {
            for (int index = 0; index < busLines.length; index++) {
                if (busMinutes[index] == busLines[index] || busMinutes[index] == 0) {
                    busLine = busLines[index];
                    wait = false;
                    break;
                } else {
                    busMinutes[index] = busMinutes[index] + 1;
                }
            }
            if (!wait) {
                break;
            }
            timestampEnd++;
        }

        return busLine * (timestampEnd - timestamp);
    }

    private static long getEarliest(long timestamp, int[] busLines) {
        long memorizedTimestamp = timestamp;

        int max = busLines[0];
        int indexMax = 0;
        for (int index = 0; index < busLines.length; index++) {
            if (busLines[index] > max) {
                max = busLines[index];
                indexMax = index;
            }
        }


        memorizedTimestamp = timestamp - (timestamp % max) + max;

        while (true) {
            boolean streak = true;

            for (int index = 0; index < indexMax && streak; index++) {
                if (busLines[index] != 0) {
                    streak = streak && (((memorizedTimestamp - indexMax + index) % busLines[index]) == 0);
                }
            }

            for (int index = indexMax + 1 ; index < busLines.length && streak; index++) {
                if (busLines[index] != 0) {
                    streak = streak && (((memorizedTimestamp - indexMax + index) % busLines[index]) == 0);
                }
            }

            if (streak) {
                break;
            }

            memorizedTimestamp += max;
        }

        return memorizedTimestamp - indexMax;
    }
}


