package com.bbjski.aoc.y2020;

import java.util.*;

public class Day10 extends Thread {

    public static Long variations = 0L;

    private static ArrayList<Integer> sortedAdapters = new ArrayList<>();

    private static HashMap<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) throws Exception {

        System.out.println(">>> Enter adapters:");

        Scanner input = new Scanner(System.in);

        ArrayList<Integer> adapters = new ArrayList<>();


        int min = -1;
        int max = -1;
        while (input.hasNextLine()){
            Integer value = Integer.valueOf(input.nextLine());
            adapters.add(value);
            sortedAdapters.add(value);
            min = min == -1 || min > value ? value : min;
            max = max == -1 || max < value ? value : max;

        }
        int jolt1Count = min == 1 ? 1 : 0;
        int jolt3Count = min == 3 ? 1 : 0;

        int nextValue = min;
        adapters.remove(adapters.indexOf(nextValue));
        while (adapters.size() > 0) {

            if (adapters.contains(nextValue + 1)) {
                nextValue += 1;
                adapters.remove(adapters.indexOf(nextValue));
                jolt1Count++;
            } else if (adapters.contains(nextValue + 2)) {
                adapters.remove(adapters.indexOf(nextValue));
                nextValue += 2;
            } else if (adapters.contains(nextValue + 3)) {
                nextValue += 3;
                adapters.remove(adapters.indexOf(nextValue));
                jolt3Count ++;
            } else {
                throw new Exception("No available adapter for the chain");
            }

        }
        jolt3Count++;

        sortedAdapters.add(0);
        sortedAdapters.add(max + 3);
        Collections.sort(sortedAdapters);

        Long start = Calendar.getInstance().getTimeInMillis();
        variations = countVariations(0);
        Long end = Calendar.getInstance().getTimeInMillis();


        System.out.println(">>> [part 1] product: " + jolt1Count * jolt3Count);
        System.out.println(">>> [part 2] variations: " + variations + ", in " + (end - start) + "ms");
    }

    private static long countVariations(int key) {

        if (key == sortedAdapters.size() -1){
            return 1;
        }

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        long result = 0L;
        for (int index = key + 1; index < sortedAdapters.size(); index++) {
            if (sortedAdapters.get(index) - sortedAdapters.get(key) <=3) {
                result += countVariations(index);
            }
        }
        memo.put(key, result);

        return result;
    }
}
