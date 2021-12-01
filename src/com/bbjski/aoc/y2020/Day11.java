package com.bbjski.aoc.y2020;

import java.util.*;

public class Day11 extends Thread {

    public static void main(String[] args) throws Exception {

        System.out.println(">>> Enter seats:");

        Scanner input = new Scanner(System.in);

        ArrayList<String> lines = new ArrayList<>();

        int nRows = 0;
        while (input.hasNextLine()){
            String line = input.nextLine();
            lines.add(line);
            nRows++;
        }
        int nCols = lines.get(0).length();

        char[][] seats = new char[nRows][];

        for (int row = 0; row < nRows; row++) {
            seats[row] = lines.get(row).toCharArray();
        }

        char[][] oldSeats = null;
        char[][] changedSeats = cloneArray(seats);

        int rulesCounter = 0;
        while (!equalArrays(oldSeats, changedSeats)) {
            oldSeats = cloneArray(changedSeats);
            changedSeats = applyRules1(changedSeats);
            rulesCounter++;
        }

        int occupied = countSeats(changedSeats, '#');

        oldSeats = null;
        changedSeats = cloneArray(seats);

        rulesCounter = 0;
        while (!equalArrays(oldSeats, changedSeats)) {
            oldSeats = cloneArray(changedSeats);
            changedSeats = applyRules2(changedSeats);
            rulesCounter++;
        }

        int occupied2 = countSeats(changedSeats, '#');

        System.out.println(">>> [part 1] occupied: " + occupied);
        System.out.println(">>> [part 2] occupied: " + occupied2);
    }

    private static char[][] applyRules1(char[][] seats) {
        char[][] changedSeats = cloneArray(seats);

        for(int rows = 0; rows < changedSeats.length; rows++) {
            for(int cols = 0; cols < changedSeats[rows].length; cols++) {
                switch (seats[rows][cols]) {
                    case 'L': {
                            boolean eligible = true;
                            if (rows > 0) {
                                if (cols > 0) {
                                    eligible = eligible && (seats[rows - 1][cols - 1] == 'L' || seats[rows - 1][cols - 1] == '.');
                                }

                                eligible = eligible && (seats[rows - 1][cols] == 'L' || seats[rows - 1][cols] == '.');

                                if (cols < seats[rows].length-1) {
                                    eligible = eligible && (seats[rows - 1][cols + 1] == 'L' || seats[rows - 1][cols + 1] == '.');
                                }
                            }
                            {
                                if (cols > 0) {
                                    eligible = eligible && (seats[rows][cols - 1] == 'L' || seats[rows][cols - 1] == '.');
                                }
                                if (cols < seats[rows].length-1) {
                                    eligible = eligible && (seats[rows][cols + 1] == 'L' || seats[rows][cols + 1] == '.');
                                }
                            }
                            if (rows < seats.length-1) {
                                if (cols > 0) {
                                    eligible = eligible && (seats[rows + 1][cols - 1] == 'L' || seats[rows + 1][cols - 1] == '.');
                                }

                                eligible = eligible && (seats[rows + 1][cols] == 'L' || seats[rows + 1][cols] == '.');

                                if (cols < seats[rows].length-1) {
                                    eligible = eligible && (seats[rows + 1][cols + 1] == 'L' || seats[rows + 1][cols + 1] == '.');
                                }
                            }

                            if (eligible) {
                                changedSeats[rows][cols] = '#';
                            }
                        }
                        break;
                    case '#':{
                            int countEligible = 0;
                            if (rows > 0) {
                                if (cols > 0 && seats[rows - 1][cols - 1] == '#') {
                                    countEligible++;
                                }

                                if (seats[rows - 1][cols] == '#') {
                                    countEligible++;
                                }

                                if (cols < seats[rows].length-1 && seats[rows - 1][cols + 1] == '#') {
                                    countEligible++;
                                }
                            }

                            {
                                if (cols > 0 && seats[rows][cols - 1] == '#'){
                                    countEligible++;
                                }
                                if (cols < seats[rows].length-1 && seats[rows][cols + 1] == '#') {
                                    countEligible++;
                                }
                            }

                            if (rows < seats.length-1) {
                                if (cols > 0 && seats[rows + 1][cols - 1] == '#') {
                                    countEligible++;
                                }

                                if (seats[rows + 1][cols] == '#') {
                                    countEligible++;
                                }

                                if (cols < seats[rows].length-1 && seats[rows + 1][cols + 1] == '#') {
                                    countEligible++;
                                }
                            }

                            if (countEligible >= 4) {
                                changedSeats[rows][cols] = 'L';
                            }
                        }
                        break;
                    case '.':
                    default:
                        break;
                }
            }
        }

        return changedSeats;
    }

    private static char[][] applyRules2(char[][] seats) {
        char[][] changedSeats = cloneArray(seats);

        for(int rows = 0; rows < changedSeats.length; rows++) {
            for(int cols = 0; cols < changedSeats[rows].length; cols++) {
                switch (seats[rows][cols]) {
                    case 'L': {
                        boolean eligible = true;
                        for (int direction = 1; direction <= 8; direction++) {
                            eligible = eligible && (neighbourSeat(seats, rows, cols, direction)=='L' ||  neighbourSeat(seats, rows, cols, direction)=='.');
                        }

                        if (eligible) {
                            changedSeats[rows][cols] = '#';
                        }
                    }
                    break;
                    case '#':{
                        int countEligible = 0;
                        for (int direction = 1; direction <= 8; direction++) {
                            countEligible += neighbourSeat(seats, rows, cols, direction)=='#' ? 1 : 0;
                        }

                        if (countEligible >= 5) {
                            changedSeats[rows][cols] = 'L';
                        }
                    }
                    break;
                    case '.':
                    default:
                        break;
                }
            }
        }

        return changedSeats;
    }

    private static char[][] cloneArray(char[][] original) {
        char[][] clone = new char[original.length][original[0].length];

        for(int rows = 0; rows < original.length; rows++) {
            for(int cols = 0; cols < original[rows].length; cols++) {
                clone[rows][cols] = original[rows][cols];
            }
        }

        return clone;
    }

    private static boolean equalArrays(char[][] array1, char[][] array2) {
        boolean equal = array1 != null && array2 != null;

        equal = equal && array1.length == array2.length;

        for(int rows = 0; equal && rows < array1.length; rows++) {
            equal = equal && array1[rows].length == array2[rows].length;
            for(int cols = 0; equal && cols < array1[rows].length; cols++) {
                equal = equal && array1[rows][cols] == array2[rows][cols];
            }
        }

        return equal;
    }

    private static int countSeats(char[][] seats, char seatType) {
        int count = 0;
        for (char[] seat : seats) {
            for (char c : seat) {
                count += c == seatType ? 1 : 0;
            }
        }

        return count;
    }

    /*
     * Directions:
     *   1 - 2 - 3
     *   4 - x - 5
     *   6 - 7 - 8
     */
    private static char neighbourSeat(char[][] seats, int row, int col, int direction) {
        char seat = '.';

        switch (direction) {
            case 1:
                while (seat == '.' && row > 0 && col > 0) {
                    row--;
                    col--;
                    seat = seats[row][col];
                }
                break;
            case 2:
                while (seat == '.' && row > 0) {
                    row--;
                    seat = seats[row][col];
                }
                break;
            case 3:
                while (seat == '.' && row > 0 && col < seats[row].length - 1) {
                    row--;
                    col++;
                    seat = seats[row][col];
                }
                break;
            case 4:
                while (seat == '.' && col > 0) {
                    col--;
                    seat = seats[row][col];
                }
                break;
            case 5:
                while (seat == '.' && col < seats[row].length - 1) {
                    col++;
                    seat = seats[row][col];
                }
                break;
            case 6:
                while (seat == '.' && row < seats.length - 1 && col > 0) {
                    row++;
                    col--;
                    seat = seats[row][col];
                }
                break;
            case 7:
                while (seat == '.' && row < seats.length - 1) {
                    row++;
                    seat = seats[row][col];
                }
                break;
            case 8:
                while (seat == '.' && row < seats.length - 1 && col < seats[row].length - 1) {
                    row++;
                    col++;
                    seat = seats[row][col];
                }
                break;
            default:
                break;
        }

        return seat;
    }
}


