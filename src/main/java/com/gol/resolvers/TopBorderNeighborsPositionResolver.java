package com.gol.resolvers;

import com.gol.models.Position;
import java.util.Arrays;
import java.util.List;

public class TopBorderNeighborsPositionResolver implements NeighborsPositionResolver {

  @Override
  public List<Position> neighborsPositions(Position currentPosition) {
    return Arrays.asList(
        new Position(currentPosition.getXCoordinate(), currentPosition.yPreviousCoordinate()),
        new Position(currentPosition.getXCoordinate(), currentPosition.yNextCoordinate()),
        new Position(currentPosition.xNextCoordinate(), currentPosition.yNextCoordinate()),
        new Position(currentPosition.xNextCoordinate(), currentPosition.getYCoordinate()),
        new Position(currentPosition.xNextCoordinate(), currentPosition.yPreviousCoordinate())
    );
  }
}
