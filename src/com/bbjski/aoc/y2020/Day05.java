package com.bbjski.aoc.y2020;

import com.bbjski.aoc.model.BoardingPass;

import java.util.Scanner;

public class Day05 {

    public static void main(String[] args) {

        System.out.println(">>> Enter boarding passes:");

        Scanner input = new Scanner(System.in);
        BoardingPass[][] allPasses = new BoardingPass[128][8];
        int maxId = -1;
        while (input.hasNextLine()){
            String line = input.nextLine();
            BoardingPass pass = new BoardingPass(line);
            maxId = Math.max(maxId, pass.getId());

            allPasses[pass.getRow()][pass.getColumn()] = pass;
        }

        int mySeatId = -1;
        for (int row = 1; row < 127; row ++) {
            for (int column = 0; column < 8; column++) {
                if (allPasses[row][column] == null) {
                    boolean validToCheck = true;
                    for (int col = 0; col < 8; col++) {
                        validToCheck = validToCheck && allPasses[row - 1][col] != null && allPasses[row + 1][col] != null;
                    }
                    mySeatId = validToCheck ? (row * 8 + column) : mySeatId;
                }
            }
        }

        System.out.println(">>> [part 1] max ID: " + maxId);
        System.out.println(">>> [part 2] my seat ID: " + mySeatId);
    }
}
