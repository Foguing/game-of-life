package com.gol.resolvers;

import com.gol.models.Position;
import java.util.Arrays;
import java.util.List;

public class BottomRightCornerNeighborsPositionResolver implements NeighborsPositionResolver {

  @Override
  public List<Position> neighborsPositions(Position currentPosition) {
    return Arrays.asList(
        new Position(currentPosition.xPreviousCoordinate(), currentPosition.yPreviousCoordinate()),
        new Position(currentPosition.xPreviousCoordinate(), currentPosition.getYCoordinate()),
        new Position(currentPosition.getXCoordinate(), currentPosition.yPreviousCoordinate())
    );
  }
}
