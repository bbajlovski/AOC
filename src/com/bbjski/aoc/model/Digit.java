package com.bbjski.aoc.model;


import java.util.*;
import java.util.stream.Collectors;

public class Digit {

  private ArrayList<Character>    ZERO    = new ArrayList<>();
  private ArrayList<Character>    ONE     = new ArrayList<>();
  private ArrayList<Character>    TWO     = new ArrayList<>();
  private ArrayList<Character>    THREE   = new ArrayList<>();
  private ArrayList<Character>    FOUR    = new ArrayList<>();
  private ArrayList<Character>    FIVE    = new ArrayList<>();
  private ArrayList<Character>    SIX     = new ArrayList<>();
  private ArrayList<Character>    SEVEN   = new ArrayList<>();
  private ArrayList<Character>    EIGHT   = new ArrayList<>();
  private ArrayList<Character>    NINE    = new ArrayList<>();
  /*********************
   *
   *       ***pos1***
   *      *          *
   *      *          *
   *     pos2       pos3
   *      *          *
   *      *          *
   *       ***pos4***
   *      *          *
   *      *          *
   *     pos5       pos6
   *      *          *
   *      *          *
   *       ***pos7***
   *
   *********************/

  private char[] segments;

  public void training(String[] patterns) {

    ArrayList<String> allPatterns =
      new ArrayList<>(Arrays.asList(patterns));
    Collections.sort(allPatterns, Comparator.comparing(String::length));

    String last = allPatterns.get(allPatterns.size() - 1);
    if (last.length() == 7) {
      allPatterns.remove(allPatterns.size() - 1);
      allPatterns.add(0, last);
    }

    for (String segments : allPatterns) {

      switch (segments.length()) {

        // 8
        case 7: {
          for (Character c : segments.toCharArray()) {
            addIfNotExist(c, EIGHT);
          }
          break;
        }

        // 4
        case 4: {
          for (Character c : segments.toCharArray()) {
            addIfNotExist(c, FOUR);

            addIfNotExist(c, EIGHT);
            addIfNotExist(c, NINE);
          }
          break;
        }

        // 7
        case 3: {
          for (Character c : segments.toCharArray()) {
            addIfNotExist(c, SEVEN);

            addIfNotExist(c, ZERO);
            addIfNotExist(c, THREE);
            addIfNotExist(c, EIGHT);
            addIfNotExist(c, NINE);
          }
          break;
        }

        // 1
        case 2: {
          for (Character c : segments.toCharArray()) {
            addIfNotExist(c, ONE);

            addIfNotExist(c, ZERO);
            addIfNotExist(c, THREE);
            addIfNotExist(c, FOUR);
            addIfNotExist(c, SEVEN);
            addIfNotExist(c, EIGHT);
            addIfNotExist(c, NINE);
          }
          break;
        }

        case 5: {
          if (numberOfCommonWires(segments.toCharArray(), FOUR) == 3) {
            if (numberOfCommonWires(segments.toCharArray(), SEVEN) == 3) {
              // 3
              for (Character c : segments.toCharArray()) {
                addIfNotExist(c, THREE);
              }
            } else {
              // 5
              for (Character c : segments.toCharArray()) {
                addIfNotExist(c, FIVE);
              }
            }
          }

          if (numberOfCommonWires(segments.toCharArray(), FOUR) == 2) {
            // 2
            for (Character c : segments.toCharArray()) {
              addIfNotExist(c, TWO);
            }
          }

          break;
        }

        case 6: {
          int commonFour = numberOfCommonWires(segments.toCharArray(), FOUR);
          if ( commonFour == 4) {
            // 9
            for (Character c : segments.toCharArray()) {
              addIfNotExist(c, NINE);
            }
          }

          if (commonFour == 3) {
            if (numberOfCommonWires(segments.toCharArray(), SEVEN) == 3) {
              // 0
              for (Character c : segments.toCharArray()) {
                addIfNotExist(c, ZERO);
              }
            } else {
              // 6
              for (Character c : segments.toCharArray()) {
                addIfNotExist(c, SIX);
              }
            }
          }

          break;
        }
      }
    }
  }

  private void addIfNotExist(Character c, ArrayList<Character> list) {
    if (!list.contains(c)) {
      list.add(c);
    }
  }

  private int numberOfCommonWires(char[] segment, ArrayList<Character> number) {
    int common = 0;

    for (Character sWire : segment) {
      boolean found = false;
      for (Character nWire : number) {
        found = found || (sWire == nWire);
        if (found) {
          break;
        }
      }
      common += found ? 1 : 0;
    }

    return common;
  }

  public int getDigit(String segments) {

    int digit = -1;

    if (containAllWires(EIGHT, segments.toCharArray())) {
      return 8;
    }

    if (containAllWires(NINE, segments.toCharArray())) {
      return 9;
    }

    if (containAllWires(SIX, segments.toCharArray())) {
      return 6;
    }

    if (containAllWires(ZERO, segments.toCharArray())) {
      return 0;
    }

    if (containAllWires(FIVE, segments.toCharArray())) {
      return 5;
    }

    if (containAllWires(THREE, segments.toCharArray())) {
      return 3;
    }

    if (containAllWires(TWO, segments.toCharArray())) {
      return 2;
    }

    if (containAllWires(FOUR, segments.toCharArray())) {
      return 4;
    }

    if (containAllWires(SEVEN, segments.toCharArray())) {
      return 7;
    }

    if (containAllWires(ONE, segments.toCharArray())) {
      return 1;
    }

    return digit;
  }

  private boolean containAllWires(ArrayList<Character> wires, char[] segments) {
    boolean contain = wires != null && wires.size() > 0;

    for (Character wire : wires) {
      boolean wireFound = false;
      for (char segment : segments) {
        wireFound = wireFound || (wire == segment);
      }
      contain = contain && wireFound;
      if (!contain) {
        break;
      }
    }

    return contain;
  }

  public int getDigitByCount(String segments) {
    int digit = -1;

    if (segments.length() == 7) {
      return 8;
    }

    if (segments.length() == 4) {
      return 4;
    }

    if (segments.length() == 3) {
      return 7;
    }

    if (segments.length() == 2) {
      return 1;
    }

    return digit;
  }

}
