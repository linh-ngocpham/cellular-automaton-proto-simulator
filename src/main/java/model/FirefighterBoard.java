package model;

import util.Position;

import java.util.*;


public class FirefighterBoard implements Board {
  int distance = Integer.MAX_VALUE;
  private final int columnCount;
  private final int rowCount;
  private final int initialFireCount;
  private final int initialFirefighterCount;
  private final int initialCloudCount;
  private final int initialMountainCount;
  private final int initialRoadCount;
  private final int initialRockCount;
  private List<Movable> movableList;
  private List<Immovable> immovableList;

  private List<Position> combinedlistpositions;
  private int step = 0;
  private final Random randomGenerator = new Random();



  public FirefighterBoard(int columnCount, int rowCount, int initialFireCount,
                          int initialMountainCount, int initialRoadCount, int initialRockCount,
                          int initialFirefighterCount, int initialCloudCount) {
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    this.movableList = new ArrayList<>();
    this.immovableList = new ArrayList<>();

    this.initialFireCount = initialFireCount;
    this.initialFirefighterCount = initialFirefighterCount;
    this.initialCloudCount = initialCloudCount;
    this.initialMountainCount = initialMountainCount;
    this.initialRoadCount = initialRoadCount;
    this.initialRockCount = initialRockCount;
    initializeImmovable();
    initializeMovable();
  }


  public void initializeImmovable() {
    for (int i = 0; i < initialMountainCount; i++){
      immovableList.add(new Mountain(randomEmptyPosition()));
    }
    for (int i = 0; i < initialRoadCount; i++){
      immovableList.add(new Road(randomEmptyPosition()));
    }
    for (int i = 0; i < initialRockCount; i++){
      immovableList.add(new Rock(randomEmptyPosition()));
    }
  }

  public void initializeMovable() {
    for (int i = 0; i < initialFireCount; i++) {
      movableList.add(new Fire(randomEmptyPosition()));
    }
    for (int i = 0; i < initialFirefighterCount; i++) {
      movableList.add(new Firefighter(randomEmptyPosition()));
    }
    for (int i = 0; i < initialCloudCount; i++) {
      movableList.add(new Cloud(randomEmptyPosition()));
    }
  }



  public Immovable getImmovableByPosition(Position position) {
    for (Immovable immovable : immovableList) {
      if (immovable.position().equals(position)) {
        return immovable;
      }
    }
    return null;
  }

  public List<Immovable> immovableList()
  {
    return immovableList; }








  private Position randomPosition() {
    return new Position(randomGenerator.nextInt(rowCount), randomGenerator.nextInt(columnCount));
  }

  private Position randomEmptyPosition() {
    Position result;
    for(;;){
      result = randomPosition();
      if (getMovableByPosition(result) == null && getImmovableByPosition(result) == null) return result;
    }
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

    List<Position> result = new ArrayList<Position>();
    List<Movable> currentMovableList = new ArrayList<Movable>(movableList);

    for (Movable movable: currentMovableList) {
      if (!(movable instanceof Fire)) result.addAll(movable.update(this));
    }

    currentMovableList = new ArrayList<Movable>(movableList);
    if (step % 2 == 1){
      for (Movable movable: currentMovableList) {
        if (movable instanceof Fire) result.addAll(movable.update(this));
      }
    }
    step++;
    return result;
  }

  public List<Movable> movableList()
  {
    return movableList; }


  public Movable getMovableByPosition(Position position) {
    for (Movable movable : movableList) {
      if (movable.position().equals(position)) {
        return movable;
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
    movableList.clear();
    immovableList.clear();
    initializeMovable();
    initializeImmovable();
  }

  List<Position> neighbors(Position position) {
    List<Position> list = new ArrayList<>();
    if (position.row() > 0) list.add(new Position(position.row() - 1, position.column()));
    if (position.column() > 0) list.add(new Position(position.row(), position.column() - 1));
    if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.column()));
    if (position.column() < columnCount - 1) list.add(new Position(position.row(), position.column() + 1));
    return list;
  }


  public void debug(){
    Position position;

    System.out.println(movableList.toString());

    for (int row = 0; row < rowCount; row++){
      for (int column = 0; column < columnCount; column++){

        position = new Position(row,column);
        if (getMovableByPosition(position) == null){
          System.out.print("0");
        } else if (getMovableByPosition(position) instanceof Fire) {
          System.out.print("1 ");
        } else if (getMovableByPosition(position) instanceof Firefighter) {
          System.out.print("2");
        }
        else if (getMovableByPosition(position) instanceof Cloud) {
          System.out.print("3");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

}