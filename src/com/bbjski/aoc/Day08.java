package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {

    public static void main(String[] args) {

        System.out.println(">>> Enter commands:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> commands = new ArrayList<>();
        while (input.hasNextLine()){
            commands.add(input.nextLine());
        }

        // part 1
        long accumulator = 0;
        int index = 0;
        boolean[] executed = new boolean[commands.size()];
        while (index >= 0 && index < commands.size()) {
            if (executed[index]) {
                break;
            }
            executed[index] = true;
            String command = commands.get(index);
            String instruction = command.split(" ")[0].trim().toLowerCase();
            int value = Integer.valueOf(command.split(" ")[1].trim());
            switch (instruction) {
                case "nop":
                    index++;
                    break;
                case "acc":
                    accumulator += value;
                    index++;
                    break;
                case "jmp":
                    index += value;
                    break;
                default:
                    break;
            }
        }


        // part 2
        long accumulator2 = 0;
        int lineIndex = 0;
        for (String line : commands) {
            if (lineIndex > 0 && (line.startsWith("nop") || line.startsWith("jmp"))) {
                accumulator2 = 0;
                index = 0;
                executed = new boolean[commands.size()];
                boolean infLoop = false;
                while (index >= 0 && index < commands.size()) {
                    if (executed[index]) {
                        infLoop = true;
                        break;
                    }
                    executed[index] = true;
                    String command = commands.get(index);
                    String instruction = command.split(" ")[0].trim().toLowerCase();
                    if (index == lineIndex) {
                        if (instruction.equalsIgnoreCase("nop")) {
                            instruction = "jmp";
                        } else if (instruction.equalsIgnoreCase("jmp")) {
                            instruction = "nop";
                        }
                    }

                    int value = Integer.valueOf(command.split(" ")[1].trim());
                    switch (instruction) {
                        case "nop":
                            index++;
                            break;
                        case "acc":
                            accumulator2 += value;
                            index++;
                            break;
                        case "jmp":
                            index += value;
                            break;
                        default:
                            break;
                    }
                }

                if (!infLoop) {
                    break;
                }
            }
            lineIndex++;
        }

        System.out.println(">>> [part 1] accumulator: " + accumulator);
        System.out.println(">>> [part 2] accumulator2: " + accumulator2);
    }
}
