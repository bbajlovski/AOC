package com.bbjski.aoc.utils;

public class Arithmetic {

  public static int maxInArray(int[] numbers) {
    int max = 0;

    if (numbers == null || numbers.length == 0) {
      return -1;
    }
    for (int number : numbers) {
      max = Math.max(max, number);
    }

    return max;
  }
}
