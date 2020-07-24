package com.gol;

import com.gol.models.FieldSize;
import com.gol.models.GameOfLifeField;

public class GameOfLife {

  public static void main(String[] args) {
    FieldSize fieldSize = new FieldSize(6, 6);
    GameOfLifeField gameOfLifeField = new GameOfLifeField(fieldSize);
    System.out.println("Generation 1");
    System.out.println(gameOfLifeField);
    int iteration = 2;
    do{
      System.out.println(String.format("Generation %d" , iteration));
      System.out.println(gameOfLifeField.nextGeneration());
      iteration++;
    }while (iteration < 11);
  }
}
