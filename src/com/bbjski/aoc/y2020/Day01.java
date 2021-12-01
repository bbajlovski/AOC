package com.bbjski.aoc.y2020;

import java.util.ArrayList;
import java.util.Scanner;

public class Day01 {

    public static void main(String[] args) {
        System.out.println(">>> Enter expense report:");

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> expensesList = new ArrayList<>();
        while (input.hasNextLine()){
            expensesList.add(Integer.parseInt(input.nextLine()));
        }


        Integer[] expenses = expensesList.toArray(new Integer[expensesList.size()]);

        int productOfTwo = -1;
        int productOfThree = -1;
        for (int index1 = 0; productOfTwo < 0 && index1 < expenses.length; index1 ++) {
            for (int index2 = 0; productOfTwo < 0 && index2 < expenses.length; index2 ++) {

                // part 1
                if (index1 != index2) {
                    productOfTwo = expenses[index1] + expenses[index2] == 2020 ? expenses[index1] * expenses[index2] : -1;
                }

                // part 2
                for (int index3 = 0; productOfThree < 0 && index3 < expenses.length; index3 ++) {
                    if (index1 != index2 && index1 != index3 && index2 != index3) {
                        productOfThree =
                          expenses[index1] + expenses[index2] + expenses[index3] == 2020 ?
                            expenses[index1] * expenses[index2] * expenses[index3] :
                            -1;
                    }
                }
            }
        }

        System.out.println(">>> [part 1] product: " + productOfTwo);
        System.out.println(">>> [part 2] product: " + productOfThree);
    }
}
