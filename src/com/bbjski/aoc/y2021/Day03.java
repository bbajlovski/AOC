package com.bbjski.aoc.y2021;

import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

    public static void main(String[] args) {

        System.out.println(">>> Enter diagnostic report:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()){
            lines.add(input.nextLine());
        }
        int nLines = lines.size();

        String gamma = "";
        String epsilon = "";
        String oxygene = "";
        String co2 = "";


        int[] nOnes = countOnes(lines);
        ArrayList<String> oxygeneLines = (ArrayList<String>) lines.clone();
        ArrayList<String> co2Lines = (ArrayList<String>) lines.clone();

        for (int pos = 0; pos < lines.get(0).length(); pos++) {

            if (nOnes[pos] >= nLines / 2) {
                gamma = gamma + "1";
                epsilon = epsilon + "0";
            } else {
                gamma = gamma + "0";
                epsilon = epsilon + "1";
            }

            if (oxygeneLines.size() > 1) {

                int[] nOxygeneOnes = countOnes(oxygeneLines);
                int nOxygenLines = oxygeneLines.size();
                char oxygenValue = nOxygeneOnes[pos] >= nOxygenLines - nOxygeneOnes[pos] ? '1' : '0';

                int index = 0;

                for (String line : oxygeneLines.toArray(new String[oxygeneLines.size()])) {
                    if (line.charAt(pos) != oxygenValue) {
                        oxygeneLines.remove(index);
                        if (oxygeneLines.size() == 1) {
                            break;
                        }
                    } else {
                        index++;
                    }
                }
            }

            if (co2Lines.size() > 1) {

                int[] nCO2Ones = countOnes(co2Lines);
                int nCO2Lines = co2Lines.size();
                char co2Value = nCO2Ones[pos] >= nCO2Lines - nCO2Ones[pos] ? '0' : '1';

                int index = 0;
                for (String line : co2Lines.toArray(new String[co2Lines.size()])) {
                    if (line.charAt(pos) != co2Value) {
                        co2Lines.remove(index);
                        if (co2Lines.size() == 1) {
                            break;
                        }
                    } else {
                        index++;
                    }
                }
            }
        }

        oxygene = oxygeneLines.get(0);
        co2 = co2Lines.get(0);

        int product1 = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
        int product2 = Integer.parseInt(oxygene, 2) * Integer.parseInt(co2, 2);

        System.out.println(">>> [part 1]: " + product1);
        System.out.println(">>> [part 2]: " + product2);
    }

    public static int[] countOnes(ArrayList<String> lines) {
        int[] nOnes;
        if (lines.size() >= 0) {
            nOnes = new int[lines.get(0).length()];
        } else {
            return null;
        }

        for (int index = 0; index < lines.size(); index++) {
            int pos = 0;
            for (char charBit : lines.get(index).toCharArray()) {
                if (charBit == '1') {
                    nOnes[pos]++;
                }
                pos++;
            }
        }

        return nOnes;
    }

}
