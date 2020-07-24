package com.gol.models;

public enum CellState {
  ALIVE, DEAD;

  public boolean isAlive(){
    return this == ALIVE;
  }

  public boolean isDead() {
    return this == DEAD;
  }
}
