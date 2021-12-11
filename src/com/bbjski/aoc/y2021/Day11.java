package com.bbjski.aoc.y2021;

import java.util.*;

public class Day11 {

    public static void main(String[] args) {

        System.out.println(">>> Enter grid:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();

        while (input.hasNextLine()){
            String line = input.nextLine();
            lines.add(line);
        }


        int[][] octopuses = new int[lines.size()][lines.get(0).length()];
        for (int row = 0; row < octopuses.length; row++) {
            for (int col = 0; col < octopuses[row].length; col++) {
                octopuses[row][col] = Integer.parseInt(lines.get(row).charAt(col) + "");
            }
        }

        int sum1 = 0;
        int step2 = -1;

        int step = 1;
        while (step2 == -1) {
            // part 1 of the step - Increasing
            for (int row = 0; row < octopuses.length; row++) {
                for (int col = 0; col < octopuses[row].length; col++) {
                    octopuses[row][col]++;
                }
            }
            int test = octopuses[0][0];
            // part 2 of the step - Flashing
            ArrayList<String> breadcrumbs = new ArrayList<>();
            for (int row = 0; row < octopuses.length; row++) {
                for (int col = 0; col < octopuses[row].length; col++) {
                    if (octopuses[row][col] > 9) {
                        flash(octopuses, row, col, breadcrumbs);
                    }
                }
            }

            // part 3 of the step - Resetting
            boolean allFlashes = true;
            for (int row = 0; row < octopuses.length; row++) {
                for (int col = 0; col < octopuses[row].length; col++) {
                    if (octopuses[row][col] > 9) {
                        octopuses[row][col] = 0;
                        sum1 += (step > 100 ? 0 : 1);
                    }
                    allFlashes = allFlashes && (octopuses[row][col] == 0);
                }
            }

            step2 = step2 == -1 && allFlashes ? step : -1;

            step++;
        }

        System.out.println(">>> [part 1]: " + sum1);
        System.out.println(">>> [part 2]: " + step2);
    }

    public static void flash(int[][] octopuses, int row, int col, ArrayList<String> breadcrumbs) {
        if (breadcrumbs.contains(row + "," + col)) {
            return;
        }

        if (octopuses[row][col] > 9) {
            breadcrumbs.add(row + "," + col);
        } else {
            return;
        }

        if (row > 0
          && col > 0) {
            octopuses[row - 1][col - 1]++;
            flash(octopuses, row - 1, col - 1, breadcrumbs);
        }

        if (row > 0) {
            octopuses[row - 1][col]++;
            flash(octopuses, row - 1, col, breadcrumbs);
        }

        if (row > 0
          && col < octopuses[row].length - 1) {
            octopuses[row - 1][col + 1]++;
            flash(octopuses, row - 1, col + 1, breadcrumbs);
        }

        if (col < octopuses[row].length - 1) {
            octopuses[row][col + 1]++;
            flash(octopuses, row, col + 1, breadcrumbs);
        }

        if (row < octopuses.length - 1
          && col < octopuses[row].length - 1) {
            octopuses[row + 1][col + 1]++;
            flash(octopuses, row + 1, col + 1, breadcrumbs);
        }

        if (row < octopuses.length - 1) {
            octopuses[row + 1][col]++;
            flash(octopuses, row + 1, col, breadcrumbs);
        }

        if (row < octopuses.length - 1
          && col > 0) {
            octopuses[row + 1][col - 1]++;
            flash(octopuses, row + 1, col - 1, breadcrumbs);
        }

        if (col > 0) {
            octopuses[row][col - 1]++;
            flash(octopuses, row, col - 1, breadcrumbs);
        }
    }
}
