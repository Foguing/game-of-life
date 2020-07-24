package com.gol.utils;


import static java.util.stream.Collectors.toList;

import com.gol.resolvers.NeighborsPositionResolver;
import com.gol.resolvers.NeighborsPositionResolverFactory;
import com.gol.models.Cell;
import com.gol.models.CellState;
import com.gol.models.FieldSize;
import com.gol.models.GameOfLifeField;
import com.gol.models.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameOfLifeFieldUtils {

  public static CellState resolveNextCellState(GameOfLifeField gameOfLifeField, Position currentPosition) {
    List<CellState> neighborCellsStates = neighborsCellsStates(gameOfLifeField, currentPosition);
    Cell currentCell = gameOfLifeField.getCells()[currentPosition.getXCoordinate()][currentPosition.getYCoordinate()];
    return resolveNextState(currentCell.getCellState(), neighborCellsStates);
  }

  private static List<CellState> neighborsCellsStates(GameOfLifeField gameOfLifeField, Position currentPosition) {
    List<Position> neighborsPositions = resolveNeighborsPositions(currentPosition, gameOfLifeField.getFieldSize());
    Cell[][] cells = gameOfLifeField.getCells();
    List<Cell> neighborCells = neighborsPositions.stream().map(
        position -> cells[position.getXCoordinate()][position.getYCoordinate()]
    ).collect(toList());

    return neighborCells.stream().map(Cell::getCellState).collect(toList());
  }

  private static List<Position> resolveNeighborsPositions(Position currentPosition, FieldSize fieldSize) {
    NeighborsPositionResolverFactory resolverFactory = new NeighborsPositionResolverFactory();
    NeighborsPositionResolver neighborsPositionResolver = resolverFactory.createResolver(fieldSize, currentPosition);
    return neighborsPositionResolver.neighborsPositions(currentPosition);
  }

  private static CellState resolveNextState(CellState currentState, List<CellState> neighborsStates) {
    CellState nextState;
    List<CellState> aliveNeighbors = neighborsStates.stream().filter(CellState::isAlive).collect(toList());
    if ((currentState.isDead()) && (aliveNeighbors.size() == 3)) {
      nextState = CellState.ALIVE;
    } else if (currentState.isAlive() && (aliveNeighbors.size() < 2)) {
      nextState = CellState.DEAD;
    } else if (currentState.isAlive() && (aliveNeighbors.size() == 2)
        || (currentState.isAlive()) && (aliveNeighbors.size() == 3)) {
      nextState = CellState.ALIVE;
    } else if (currentState.isAlive()) {
      nextState = CellState.DEAD;
    } else {
      nextState = currentState;
    }
    return nextState;
  }

  public static Cell[][] randomCells(FieldSize fieldSize) {
    int xSize = fieldSize.getXAxisSize();
    int ySize = fieldSize.getYAxisSize();
    Cell[][] cells = new Cell[xSize][ySize];
    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        cells[x][y] = new Cell(randomState());
      }
    }
    return cells;
  }

  private static CellState randomState() {
    List<CellState> cellStates = Arrays.asList(CellState.values());
    return cellStates.get(new Random().nextInt(cellStates.size()));
  }
}
