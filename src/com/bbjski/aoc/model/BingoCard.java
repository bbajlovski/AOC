package com.bbjski.aoc.model;

public class BingoCard {

  public static final int CARD_SIZE = 5;

  private int[][] numbers = new int[CARD_SIZE][CARD_SIZE];
  private int[][] marks = new int[CARD_SIZE][CARD_SIZE];
  private int row = -1;
  private int winningNumber = -1;
  private int winningRow = -1;
  private int winningColumn = -1;

  public void addLine(String line) {
    if (line != null && line.length() > 0) {
      row++;
      if (row < CARD_SIZE) {
        line = line.replaceAll("( )+", " ");
        String[] newNumbers = line.split(" ");
        for (int column = 0; column < CARD_SIZE; column++) {
          numbers[row][column] = Integer.parseInt(newNumbers[column].trim());
        }
      }
    }
  }

  public boolean isEmptyCard() {
    return row < 0;
  }

  public boolean markNumber(int number) {
    boolean bingo = false;

    // mark the card
    for (int rowIndex = 0; rowIndex < CARD_SIZE; rowIndex++) {
      for (int colIndex = 0; colIndex < CARD_SIZE; colIndex++) {
        if (numbers[rowIndex][colIndex] == number) {
          marks[rowIndex][colIndex] = 1;
        }
      }
    }

    // check bingo in rows
    for (int rowIndex = 0; rowIndex < CARD_SIZE; rowIndex++) {
      int rowSum = 0;
      for (int colIndex = 0; colIndex < CARD_SIZE; colIndex++) {
        rowSum += marks[rowIndex][colIndex];
      }
      if (rowSum == 5) {
        winningNumber = number;
        winningRow = rowIndex;
        bingo = true;
        break;
      }
    }

    // stop if bingo is found
    if (bingo) return bingo;

    // check bingo in columns
    for (int colIndex = 0; colIndex < CARD_SIZE; colIndex++) {
      int colSum = 0;
      for (int rowIndex = 0; rowIndex < CARD_SIZE; rowIndex++) {
        colSum += marks[rowIndex][colIndex];
      }
      if (colSum == 5) {
        winningNumber = number;
        winningColumn = colIndex;
        bingo = true;
        break;
      }
    }

    // exit anyway
    return bingo;
  }

  public int getWinningNumber() {
    return winningNumber;
  }

  public int getUnmarkedSum() {
    int sum = 0;

    for (int rowIndex = 0; rowIndex < CARD_SIZE; rowIndex++) {
      for (int colIndex = 0; colIndex < CARD_SIZE; colIndex++) {
        sum = sum + (marks[rowIndex][colIndex] == 0 ? numbers[rowIndex][colIndex] : 0);
      }
    }

    return sum;
  }
}
