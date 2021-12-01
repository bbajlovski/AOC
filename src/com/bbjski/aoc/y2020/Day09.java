package com.bbjski.aoc.y2020;

import java.util.ArrayList;
import java.util.Scanner;

public class Day09 {

    public static void main(String[] args) {

        System.out.println(">>> Enter numbers:");

        Scanner input = new Scanner(System.in);

        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Boolean> usedNumber = new ArrayList<>();

        int counter = 0;

        long weakness = -1;
        long encryptionWeakness = -1;

        while (input.hasNextLine()){
            numbers.add(Long.valueOf(input.nextLine()));
            usedNumber.add(false);
            if (counter >= 25) {
                boolean found = false;

                for (int idx = counter - 25; idx < counter && !found; idx++) {
                    for (int idxx = idx + 1; idxx < counter && !found; idxx++) {
                        if (numbers.get(idx) + numbers.get(idxx) == numbers.get(numbers.size() - 1)) {
                            found = true;
                        }
                    }
                }

                if (!found) {
                    weakness = numbers.get(numbers.size() - 1);
                    long sum;
                    long min = -1;
                    long max = -1;

                    for (int idx = 0; idx < counter; idx++) {
                        sum = numbers.get(idx);
                        min = numbers.get(idx);
                        max = numbers.get(idx);
                        boolean exit = false;
                        for (int idxx = idx + 1; idxx < counter; idxx++) {
                            sum += numbers.get(idxx);
                            min = numbers.get(idxx) < min ? numbers.get(idxx) : min;
                            max = numbers.get(idxx) > max ? numbers.get(idxx) : max;
                            if (sum == numbers.get(numbers.size() - 1)) {
                                exit = true;
                                break;
                            }
                        }
                        if (exit) {
                            break;
                        }
                    }

                    encryptionWeakness = min + max;
                    break;
                }
            }

            counter++;
        }


        System.out.println(">>> [part 1] weakness: " + weakness);
        System.out.println(">>> [part 2] encryption weakness: " + encryptionWeakness);
    }
}
