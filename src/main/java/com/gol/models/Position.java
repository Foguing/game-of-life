package com.gol.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
  private int xCoordinate;
  private int yCoordinate;

  public int xPreviousCoordinate(){
    return xCoordinate - 1;
  }

  public int xNextCoordinate(){
    return xCoordinate + 1;
  }

  public int yPreviousCoordinate(){
    return yCoordinate - 1;
  }

  public int yNextCoordinate(){
    return yCoordinate + 1;
  }

}
