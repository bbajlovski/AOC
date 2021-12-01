package com.bbjski.aoc.y2020;

import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

    public static void main(String[] args) {

        System.out.println(">>> Enter tree map:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> rowsList = new ArrayList<>();
        while (input.hasNextLine()){
            rowsList.add(input.nextLine());
        }

        ArrayList<int[]> params = new ArrayList<>();
        params.add(new int[] {1, 1, 0});
        params.add(new int[] {3, 1, 0});
        params.add(new int[] {5, 1, 0});
        params.add(new int[] {7, 1, 0});
        params.add(new int[] {1, 2, 0});

        long product = 1;
        for (int[] param : params) {
            int row = 0;
            for (String trees : rowsList) {
                if (row != 0 && row % param[1] == 0) {
                    int pos = ((row/param[1]) * param[0]) % trees.length();
                    param[2] = trees.charAt(pos) == '#' ? param[2] + 1 : param[2];
                }
                row++;
            }
            product *= param[2];
        }

        System.out.println(">>> [part 1] trees: " + params.get(1)[2]);
        System.out.println(">>> [part 2] slopes multiplied: " + product);
    }
}
