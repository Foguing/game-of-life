package com.gol.resolvers;

import com.gol.models.FieldSize;
import com.gol.models.Position;

public class NeighborsPositionResolverFactory {

  public NeighborsPositionResolver createResolver(FieldSize fieldSize, Position currentPosition){
    NeighborsPositionResolver neighborsPositionResolver;
    if (isBottomLeftCornerPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new BottomLeftCornerNeighborsPositionResolver();
    } else if (isTopLeftCornerPosition(currentPosition)) {
      neighborsPositionResolver = new TopLeftCornerNeighborsPositionResolver();
    } else if (isTopRightCornerPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new TopRightCornerNeighborsPositionResolver();
    } else if (isBottomRightCornerPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new BottomRightCornerNeighborsPositionResolver();
    } else if (isLeftBorderPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new LeftBorderNeighborsPositionResolver();
    } else if (isTopBorderPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new TopBorderNeighborsPositionResolver();
    } else if (isRightBorderPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new RightBorderNeighborsPositionResolver();
    } else if (isBottomBorderPosition(fieldSize, currentPosition)) {
      neighborsPositionResolver = new BottomBorderNeighborsPositionResolver();
    } else {
      neighborsPositionResolver = new DefaultNeighborsPositionResolver();
    }
    return neighborsPositionResolver;
  }

  private boolean isBottomLeftCornerPosition(FieldSize fieldSize, Position position) {
    return Boolean.logicalAnd(
        position.getXCoordinate() == (fieldSize.getXAxisSize() - 1),
        position.getYCoordinate() == 0
    );
  }

  private boolean isTopLeftCornerPosition(Position position) {
    return Boolean.logicalAnd(
        position.getXCoordinate() == 0,
        position.getYCoordinate() == 0
    );
  }

  private boolean isTopRightCornerPosition(FieldSize fieldSize, Position position) {
    return Boolean.logicalAnd(
        position.getXCoordinate() == 0,
        position.getYCoordinate() == (fieldSize.getYAxisSize() - 1)
    );
  }

  private boolean isBottomRightCornerPosition(FieldSize fieldSize, Position position) {
    return Boolean.logicalAnd(
        position.getXCoordinate() == (fieldSize.getXAxisSize() - 1),
        position.getYCoordinate() == (fieldSize.getYAxisSize() - 1)
    );
  }

  private boolean isLeftBorderPosition(FieldSize fieldSize, Position position) {
    boolean isLeftCornerPosition = Boolean.logicalOr(
        isTopLeftCornerPosition(position),
        isBottomLeftCornerPosition(fieldSize, position)
    );
    return position.getYCoordinate() == 0 && !isLeftCornerPosition;
  }

  private boolean isTopBorderPosition(FieldSize fieldSize, Position position) {
    boolean isTopCornerPosition = Boolean.logicalOr(
        isTopLeftCornerPosition(position),
        isTopRightCornerPosition(fieldSize, position)
    );
    return position.getXCoordinate() == 0 && !isTopCornerPosition;
  }

  private boolean isRightBorderPosition(FieldSize fieldSize, Position position) {
    boolean isRightCornerPosition = Boolean.logicalOr(
        isTopRightCornerPosition(fieldSize, position),
        isBottomRightCornerPosition(fieldSize, position)
    );
    return position.getYCoordinate() == (fieldSize.getYAxisSize() - 1) && !isRightCornerPosition;
  }

  private boolean isBottomBorderPosition(FieldSize fieldSize, Position position) {
    boolean isBottomCornerPosition = Boolean.logicalOr(
        isBottomLeftCornerPosition(fieldSize, position),
        isBottomRightCornerPosition(fieldSize, position)
    );
    return position.getXCoordinate() == (fieldSize.getXAxisSize() - 1) && !isBottomCornerPosition;
  }

}
