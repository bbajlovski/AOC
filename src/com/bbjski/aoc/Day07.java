package com.bbjski.aoc;

import java.util.*;

public class Day07 {

    public static void main(String[] args) {

        System.out.println(">>> Enter rules:");

        Scanner input = new Scanner(System.in);

        List<String> colorsContainGold = new ArrayList<>();
        HashMap<String, HashMap<String, Integer>> rules = new HashMap<>();
        while (input.hasNextLine()){
            String line = input.nextLine();
            String[] ruleDef = line.split("bags contain");
            String key = ruleDef[0].trim();
            String[] values = ruleDef[1].substring(0, ruleDef[1].length() - 1).split(",");

            HashMap<String, Integer> rule = new HashMap<>();
            for (String value : values) {
                if (!value.trim().startsWith("no")) {
                    String trimmedValue = value.trim();
                    String bagNum = trimmedValue.substring(0, trimmedValue.indexOf(" "));
                    int num = Integer.valueOf(bagNum);
                    String bagColor = trimmedValue.substring(trimmedValue.indexOf(" ") + 1, trimmedValue.indexOf(" bag")).trim();
                    rule.put(bagColor, num);
                    if (bagColor.equalsIgnoreCase("shiny gold")) {
                        if (!colorsContainGold.contains(key)) {
                            colorsContainGold.add(key);
                        }
                    }
                }
            }

            rules.put(key, rule);
        }

        boolean changes = true;

        while (changes) {
            changes = false;
            for (Map.Entry<String, HashMap<String, Integer>> rule : rules.entrySet()) {
                for (Map.Entry<String, Integer> value : rule.getValue().entrySet()) {
                    if (colorsContainGold.contains(value.getKey()) && !colorsContainGold.contains(rule.getKey())) {
                        colorsContainGold.add(rule.getKey());
                        changes = true;
                    }
                }
            }
        }

        System.out.println(">>> [part 1] number of bag colors: " + colorsContainGold.size());
        System.out.println(">>> [part 2] bags to care: " + countBags(rules.get("shiny gold"), rules));
    }

    private static int countBags(HashMap<String, Integer> bags, HashMap<String, HashMap<String, Integer>> allBags) {
        int total = 0;
        if (bags != null && !bags.isEmpty()) {
            for (Map.Entry<String, Integer> bag : bags.entrySet()) {
                total += bag.getValue() + bag.getValue() * countBags(allBags.get(bag.getKey()), allBags);
            }
        }
        return total;
    }
}
