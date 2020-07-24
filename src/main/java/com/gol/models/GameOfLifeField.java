package com.gol.models;

import static com.gol.utils.GameOfLifeFieldUtils.resolveNextCellState;

import com.gol.utils.GameOfLifeFieldUtils;
import lombok.Getter;
import lombok.Setter;

public class GameOfLifeField {

  @Getter
  private final FieldSize fieldSize;

  @Getter
  @Setter
  private Cell[][] cells;

  public GameOfLifeField(FieldSize fieldSize) {
    this.fieldSize = fieldSize;
    this.cells = GameOfLifeFieldUtils.randomCells(fieldSize);
  }

  public GameOfLifeField nextGeneration(){
    int xAxisSize = fieldSize.getXAxisSize();
    int yAxisSize = fieldSize.getYAxisSize();
    Cell[][] nextGenerationCells = new Cell[xAxisSize][yAxisSize];
    for(int x = 0; x < xAxisSize; x++){
      for(int y = 0; y < yAxisSize; y++){
        Position currentPosition = new Position(x, y);
        CellState nextCellState = resolveNextCellState(this, currentPosition);
        nextGenerationCells[x][y] = new Cell(nextCellState);
      }
    }
    this.setCells(nextGenerationCells);
    return this;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    int ySize = cells[0].length;
    for (Cell[] cell : cells) {
      for (int y = 0; y < ySize; y++) {
        String state = cell[y].getCellState().isAlive() ? " L " : " T ";
        stringBuilder.append(state);
      }
      stringBuilder.append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }
}
