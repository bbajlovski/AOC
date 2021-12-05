package com.bbjski.aoc.y2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Day05 {

    public static void main(String[] args) {

        System.out.println(">>> Enter pipes:");

        Scanner input = new Scanner(System.in);
        ArrayList<int[]> pipes = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;
        while (input.hasNextLine()){
            String pipe = input.nextLine();
            int[] onePipe = new int[4];
            String[] coordinates = pipe.split("->");
            String[] from = coordinates[0].trim().split(",");
            String[] to = coordinates[1].trim().split(",");

            onePipe[0] = Integer.parseInt(from[0].trim());
            maxX = Math.max(maxX, onePipe[0]);

            onePipe[1] = Integer.parseInt(from[1].trim());
            maxY = Math.max(maxY, onePipe[1]);

            onePipe[2] = Integer.parseInt(to[0].trim());
            maxX = Math.max(maxX, onePipe[2]);

            onePipe[3] = Integer.parseInt(to[1].trim());
            maxY = Math.max(maxY, onePipe[3]);

            pipes.add(onePipe);
        }

        int[][] vents = new int[maxX+1][maxY+1];
        for (int[] pipe : pipes) {
            addHvPipe(pipe, vents);
        }
        int countHvOverlaps = countOverlaps(vents);


        for (int[] pipe : pipes) {
            addDiagonalPipe(pipe, vents);
        }
        int countAllOverlaps = countOverlaps(vents);

        System.out.println(">>> [part 1]: " + countHvOverlaps);
        System.out.println(">>> [part 2]: " + countAllOverlaps);
    }

    public static void addHvPipe(int[] pipe, int[][] vents) {
        if (pipe != null && pipe.length == 4 && vents != null){
            int fromX = Math.min(pipe[0], pipe[2]);
            int toX = Math.max(pipe[0], pipe[2]);

            int fromY = Math.min(pipe[1], pipe[3]);
            int toY = Math.max(pipe[1], pipe[3]);

            if (fromX == toX || fromY == toY) {
                for (int x = fromX; x <= toX; x++) {
                    for (int y = fromY; y <= toY; y++) {
                        vents[x][y]++;
                    }
                }
            }
        }
    }

    public static void addDiagonalPipe(int[] pipe, int[][] vents) {
        if (
          pipe != null && pipe.length == 4 && vents != null
            && !(pipe[0] == pipe[2] || pipe[1] == pipe[3])
        ){
            int incrX = (pipe[2] - pipe[0]) / Math.abs(pipe[0] - pipe[2]);
            int incrY = (pipe[3] - pipe[1]) / Math.abs(pipe[1] - pipe[3]);
            int x = pipe[0];
            int y = pipe[1];
            while (x != (pipe[2] + incrX) && y != (pipe[3] + incrY)) {
                vents[x][y]++;
                x += incrX;
                y += incrY;
            }
        }
    }

    public static int countOverlaps(int[][] vents) {
        int count = 0;
        for (int x = 0; x < vents.length; x++) {
            for (int y = 0; y < vents[x].length; y++) {
                count += (vents[x][y] > 1 ? 1 : 0);
            }
        }
        return count;
    }
}
