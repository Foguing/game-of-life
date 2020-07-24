package com.gol.models;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gol.utils.GameOfLifeFieldUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameOfLifeFieldTest {

  private static final int X_SIZE = 2;
  private static final int Y_SIZE = 2;

  @DisplayName("initial GameField does not have any cells and should remain the same over generations")
  @Test
  public void initialFieldCanNotHaveNextGeneration() {
    FieldSize fieldSize = new FieldSize(X_SIZE, Y_SIZE);
    GameOfLifeField initialGameField = new GameOfLifeField(fieldSize);

    GameOfLifeField nextGameField = initialGameField.nextGeneration();

    assertEquals(initialGameField, nextGameField);
  }

  @DisplayName("A dead cell with three alive neighbors should be alive again in the next generation")
  @Test
  public void deadCellWithThreeAliveNeighborsShouldResurrect(){
    FieldSize fieldSize = new FieldSize(X_SIZE, Y_SIZE);
    GameOfLifeField initialGameField = new GameOfLifeField(fieldSize);
    initialGameField.setCells(deadCellWithThreeAliveNeighbors());

    GameOfLifeField nextGameField = initialGameField.nextGeneration();

    Cell resurrectedCell = nextGameField.getCells()[0][0];
    assertEquals(CellState.ALIVE, resurrectedCell.getCellState());
  }

  /**
   *
   * @return a map like this:
   *    T A
   *    A A
   */
  private Cell[][] deadCellWithThreeAliveNeighbors(){
    Cell[][] cells = new Cell[X_SIZE][Y_SIZE];
    cells[0][0] = new Cell(CellState.DEAD);
    cells[0][1] = new Cell(CellState.ALIVE);
    cells[1][0] = new Cell(CellState.ALIVE);
    cells[1][1] = new Cell(CellState.ALIVE);
    return cells;
  }

}
