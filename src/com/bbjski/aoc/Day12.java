package com.bbjski.aoc;

import java.util.Scanner;

public class Day12 extends Thread {

    private static int xPos = 0;
    private static int yPos = 0;
    // E - east, N - north, W - west, S - south
    private static char direction = 'E';

    private static int xPos2 = 0;
    private static int yPos2 = 0;
    // E - east, N - north, W - west, S - south
    private static char direction2 = 'E';
    private static int xWaypoint = 10;
    private static int yWaypoint = 1;

    public static void main(String[] args) throws Exception {

        System.out.println(">>> Enter moves:");

        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()){
            String move = input.nextLine();
            char dirMove = move.charAt(0);
            int length = Integer.valueOf(move.substring(1));

            calculatePosition(dirMove, length);
            calculatePosition2(dirMove, length);
        }


        System.out.println(">>> [part 1] manhattan: " + (Math.abs(xPos) + Math.abs(yPos)));
        System.out.println(">>> [part 2] manhattan: " + (Math.abs(xPos2) + Math.abs(yPos2)));
    }

    private static void calculatePosition(char dirMove, int length) {
        char[] props = pickDirection(dirMove, direction, length);

        length = dirMove == 'L' || dirMove == 'R' ? 0 : length;
        dirMove = props[0];
        direction = props[1];

        switch (dirMove) {
            case 'E':
                xPos += length;
                break;
            case 'N':
                yPos += length;
                break;
            case 'W':
                xPos -= length;
                break;
            case 'S':
                yPos -= length;
                break;
        }
    }

    private static void calculatePosition2(char dirMove, int length) {

        if (dirMove == 'F') {
            xPos2 += xWaypoint * length;
            yPos2 += yWaypoint * length;
        } else {
            moveWaypoint(dirMove, length);
        }
    }

    private static char[] pickDirection(char move, char direction, int angle) {
        char[] finalProps = new char[]{move, direction};

        if (move == 'F') {
            finalProps[0] = direction;
            finalProps[1] = direction;
        }

        int nLoops = angle / 90;
        if (move == 'L') {
            for (int count = 0; count < nLoops; count++) {
                if (direction == 'E') {
                    direction = 'N';
                    continue;
                }
                if (direction == 'N') {
                    direction = 'W';
                    continue;
                }
                if (direction == 'W') {
                    direction = 'S';
                    continue;
                }
                if (direction == 'S') {
                    direction = 'E';
                    continue;
                }
            }
            finalProps[0] = direction;
            finalProps[1] = direction;
        }

        if (move == 'R') {
            for (int count = 0; count < nLoops; count++) {
                if (direction == 'E') {
                    direction = 'S';
                    continue;
                }
                if (direction == 'S') {
                    direction = 'W';
                    continue;
                }
                if (direction == 'W') {
                    direction = 'N';
                    continue;
                }
                if (direction == 'N') {
                    direction = 'E';
                    continue;
                }
            }
            finalProps[0] = direction;
            finalProps[1] = direction;
        }

        return finalProps;
    }

    private static void moveWaypoint(char dirMove, int length) {

        int nLoops = length / 90;
        switch (dirMove) {
            case 'E':
                xWaypoint += length;
                break;
            case 'N':
                yWaypoint += length;
                break;
            case 'W':
                xWaypoint -= length;
                break;
            case 'S':
                yWaypoint -= length;
                break;
            case 'L':
                for (int count = 0; count < nLoops; count++) {
                    int xNew = (-1) * yWaypoint;
                    int yNew = xWaypoint;

                    xWaypoint = xNew;
                    yWaypoint = yNew;
                }
                break;
            case 'R':
                for (int count = 0; count < nLoops; count++) {
                    int xNew = yWaypoint;
                    int yNew = (-1) * xWaypoint;

                    xWaypoint = xNew;
                    yWaypoint = yNew;
                }
                break;
        }
    }
}


