package com.bbjski.aoc.y2021;

import java.util.*;

public class Day09 {

    public static void main(String[] args) {

        System.out.println(">>> Enter map:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> heightMap = new ArrayList<>();
        while (input.hasNextLine()){
            heightMap.add(input.nextLine());
        }


        int[][] heights = new int[heightMap.size()][heightMap.get(0).length()];
        for (int row = 0; row < heights.length; row++) {
            String rowLine = heightMap.get(row);
            for (int col = 0; col < heights[row].length; col++) {
                heights[row][col] = Integer.parseInt(rowLine.charAt(col) + "");
            }
        }

        int sum1 = 0;
        ArrayList<Integer> basinsSizes = new ArrayList<>();
        for (int row = 0; row < heights.length; row++) {
            for (int col = 0; col < heights[row].length; col++) {

                // up
                if (row > 0) {
                    if (heights[row][col] >= heights[row - 1][col]) {
                        continue;
                    }
                }

                // down
                if (row < heights.length - 1) {
                    if (heights[row][col] >= heights[row + 1][col]) {
                        continue;
                    }
                }

                // left
                if (col > 0) {
                    if (heights[row][col] >= heights[row][col - 1]) {
                        continue;
                    }
                }

                // right
                if (col < heights[row].length - 1) {
                    if (heights[row][col] >= heights[row][col + 1]) {
                        continue;
                    }
                }

                sum1 += (1 + heights[row][col]);

                ArrayList<Integer> basin = new ArrayList<>();
                ArrayList<String> breadcrumbs = new ArrayList<>();
                collectBasins(heights, row, col, basin, breadcrumbs);
                basinsSizes.add(basin.size());

            }
        }

        Collections.sort(basinsSizes, Comparator.comparing(Integer::intValue));
        Collections.reverse(basinsSizes);
        int product2 = basinsSizes.get(0) * basinsSizes.get(1) * basinsSizes.get(2);

        System.out.println(">>> [part 1]: " + sum1);
        System.out.println(">>> [part 2]: " + product2);
    }

    static void collectBasins(int heights[][], int currentRow, int currentCol, ArrayList<Integer> basin, ArrayList<String> breadcrumbs) {

        if (heights[currentRow][currentCol] == 9) {
            return;
        }

        basin.add(heights[currentRow][currentCol]);
        breadcrumbs.add(currentRow + "," + currentCol);

        // up
        if (currentRow > 0) {
            if (!breadcrumbs.contains((currentRow - 1) + "," + currentCol)) {
                collectBasins(heights, currentRow - 1, currentCol, basin, breadcrumbs);
            }
        }

        // down
        if (currentRow < heights.length - 1) {
            if (!breadcrumbs.contains((currentRow + 1) + "," + currentCol)) {
                collectBasins(heights, currentRow + 1, currentCol, basin, breadcrumbs);
            }
        }

        // left
        if (currentCol > 0) {
            if (!breadcrumbs.contains(currentRow + "," + (currentCol - 1))) {
                collectBasins(heights, currentRow, currentCol - 1, basin, breadcrumbs);
            }
        }

        // right
        if (currentCol < heights[currentRow].length - 1) {
            if (!breadcrumbs.contains(currentRow  + "," + (currentCol + 1))) {
                collectBasins(heights, currentRow, currentCol + 1, basin, breadcrumbs);
            }
        }
    }
}
