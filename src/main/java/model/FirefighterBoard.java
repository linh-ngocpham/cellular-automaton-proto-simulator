package model;

import util.Position;

import java.util.*;


public class FirefighterBoard implements Board {
  private final int columnCount;
  private final int rowCount;
  private final int initialFireCount;
  private final int initialFirefighterCount;

  private final int initialMountainCount;
  private final int initialRoadCount;
  private final int initialRockCount;

  private List<Immovable> immovablesList;


  private int step = 0;
  private final Random randomGenerator = new Random();

  public FirefighterBoard(int columnCount, int rowCount, int initialFireCount, int initialFirefighterCount,
                          int initialMountainCount, int initialRoadCount, int initialRockCount) {
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    this.immovablesList = new ArrayList<>();

    this.initialFireCount = initialFireCount;
    this.initialFirefighterCount = initialFirefighterCount;
    this.initialMountainCount = initialMountainCount;
    this.initialRoadCount = initialRoadCount;
    this.initialRockCount = initialRockCount;
    initializeImmovable();


  }
  public void initializeImmovable() {
    for (int i = 0; i < initialMountainCount; i++){
      immovablesList.add(new Mountain(randomEmptyPosition()));
    }
    for (int i = 0; i < initialRoadCount; i++){
      immovablesList.add(new Road(randomEmptyPosition()));
    }
    for (int i = 0; i < initialRockCount; i++){
      immovablesList.add(new Rock(randomEmptyPosition()));
    }
  }






  private Position randomPosition() {
    return new Position(randomGenerator.nextInt(rowCount), randomGenerator.nextInt(columnCount));
  }





  @Override
  public int rowCount() {
    return rowCount;
  }

  @Override
  public int columnCount() {
    return columnCount;
  }

  public List<Position> updateToNextGeneration() {
    List<Position> modifiedPositions = updateFirefighters();
    modifiedPositions.addAll(updateFires());
    step++;
    return modifiedPositions;
  }
  public List<Immovable> immovablesList() { return immovablesList; }
  public Immovable getImmovableByPosition(Position position) {
    for (Immovable immovable : immovablesList) {
      if (immovable.position().equals(position)) {
        return immovable;
      }
    }
    return null;
  }




  @Override
  public int stepNumber() {
    return step;
  }



  @Override
  public void reset() {
    step = 0;

  }



  private List<Position> neighbors(Position position) {
    List<Position> list = new ArrayList<>();
    if (position.row() > 0) list.add(new Position(position.row() - 1, position.column()));
    if (position.column() > 0) list.add(new Position(position.row(), position.column() - 1));
    if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.column()));
    if (position.column() < columnCount - 1) list.add(new Position(position.row(), position.column() + 1));
    return list;
  }





}