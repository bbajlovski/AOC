package com.bbjski.aoc.y2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Day02 {

    public static void main(String[] args) {

        System.out.println(">>> Enter commands:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> commands = new ArrayList<>();
        while (input.hasNextLine()){
            commands.add(input.nextLine());
        }


        int depth1 = 0;
        int horizon = 0;

        int depth2 = 0;
        int aim = 0;

        for (int index = 0; index < commands.size(); index++) {

            String[] command = commands.get(index).split(" ");
            String course = command[0].toLowerCase().trim();
            int value = Integer.parseInt(command[1].trim());

            switch (course) {
                case "forward": {
                    horizon += value;
                    depth2 += aim * value;
                    break;
                }
                case "up": {
                    depth1 -= value;
                    aim -= value;
                    break;
                }
                case "down": {
                    depth1 += value;
                    aim += value;
                    break;
                }
            }
        }
        int product1 = depth1 * horizon;
        int product2 = depth2 * horizon;

        System.out.println(">>> [part 1]: " + product1);
        System.out.println(">>> [part 2]: " + product2);
    }

}
