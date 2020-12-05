package com.bbjski.aoc.model;

public class BoardingPass {

  public static final int MAX_ROW_NUM = 127;
  public static final int MAX_COLUMN_NUM = 7;

  String code;
  int id;
  int row;
  int column;

  public BoardingPass(String code) {
    this.code = code;
    decode(code);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  private void decode(String code) {
    char[] steps = code.toCharArray();

    // calculating the row
    int startRow = 0;
    int endRow = MAX_ROW_NUM;
    for (int trRow = 0; trRow < 7; trRow++) {
      int half = (endRow - startRow)/2;

      if (steps[trRow] == 'F') {
        endRow = startRow + half;
      }

      if (steps[trRow] == 'B'){
        startRow = startRow + half + 1;
      }
    }
    if (startRow == endRow) {
      this.row = startRow;
    }


    // calculating the column
    int startColumn = 0;
    int endColumn = MAX_COLUMN_NUM;
    for (int trColumn = 7; trColumn < 10; trColumn++) {
      int half = (endColumn - startColumn)/2;

      if (steps[trColumn] == 'L') {
        endColumn = startColumn + half;
      }

      if (steps[trColumn] == 'R'){
        startColumn = startColumn + half + 1;
      }
    }
    if (startColumn == endColumn) {
      this.column = startColumn;
    }

    // calculate the id
    this.id = this.row * 8 + this.column;
  }
}
