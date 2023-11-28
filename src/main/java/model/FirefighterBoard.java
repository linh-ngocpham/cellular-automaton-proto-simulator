package model;

import util.Position;

import java.util.*;


public class FirefighterBoard implements Board {
  private final int columnCount;
  private final int rowCount;
  private final int initialFireCount;
  private final int initialFirefighterCount;
  private final int initialCloudCount;
  private final int initialMountainCount;
  private final int initialRoadCount;
  private final int initialRockCount;
  private List<Item> itemList;
  private List<Immovable> boxList;


  private int step = 0;
  private final Random randomGenerator = new Random();

  public FirefighterBoard(int columnCount, int rowCount, int initialFireCount, int initialFirefighterCount, int initialCloudCount, int initialMotorFirefighterCount,
                          int initialMountainCount, int initialRoadCount, int initialRockCount) {
    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.itemList = new ArrayList<>();
    this.immovableList = new ArrayList<>();

    this.initialFireCount = initialFireCount;
    this.initialFirefighterCount = initialFirefighterCount;
    this.initialMountainCount = initialMountainCount;
    this.initialRoadCount = initialRoadCount;
    this.initialRockCount = initialRockCount;
    initializeImmovable();


  }

  public void initializeElements() {
    firefighterPositions = new ArrayList<>();
    firePositions = new HashSet<>();
    for (int index = 0; index < initialFireCount; index++)
      firePositions.add(randomPosition());
    for (int index = 0; index < initialFirefighterCount; index++)
      firefighterPositions.add(randomPosition());
  }

  private Position randomPosition() {
    return new Position(randomGenerator.nextInt(rowCount), randomGenerator.nextInt(columnCount));
  }

  @Override
  public List<ModelElement> getState(Position position) {
    List<ModelElement> result = new ArrayList<>();
    for(Position firefighterPosition : firefighterPositions)
      if (firefighterPosition.equals(position))
        result.add(ModelElement.FIREFIGHTER);
    if(firePositions.contains(position))
      result.add(ModelElement.FIRE);
    return result;
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

  private List<Position> updateFires() {
    List<Position> modifiedPositions = new ArrayList<>();
    if (step % 2 == 0) {
      List<Position> newFirePositions = new ArrayList<>();
      for (Position fire : firePositions) {
        newFirePositions.addAll(neighbors(fire));
      }
      firePositions.addAll(newFirePositions);
      modifiedPositions.addAll(newFirePositions);
    }
    return modifiedPositions;

  }

  @Override
  public int stepNumber() {
    return step;
  }

  private List<Position> updateFirefighters() {
    List<Position> modifiedPosition = new ArrayList<>();
    List<Position> firefighterNewPositions = new ArrayList<>();
    for (Position firefighterPosition : firefighterPositions) {
      Position newFirefighterPosition = neighborClosestToFire(firefighterPosition);
      firefighterNewPositions.add(newFirefighterPosition);
      extinguish(newFirefighterPosition);
      modifiedPosition.add(firefighterPosition);
      modifiedPosition.add(newFirefighterPosition);
      List<Position> neighborFirePositions = neighbors(newFirefighterPosition).stream()
              .filter(firePositions::contains).toList();
      for(Position firePosition : neighborFirePositions)
        extinguish(firePosition);
      modifiedPosition.addAll(neighborFirePositions);
    }
    firefighterPositions = firefighterNewPositions;
    return modifiedPosition;
  }

  @Override
  public void reset() {
    step = 0;
    initializeElements();
  }

  private void extinguish(Position position) {
    firePositions.remove(position);
  }

  private List<Position> neighbors(Position position) {
    List<Position> list = new ArrayList<>();
    if (position.row() > 0) list.add(new Position(position.row() - 1, position.column()));
    if (position.column() > 0) list.add(new Position(position.row(), position.column() - 1));
    if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.column()));
    if (position.column() < columnCount - 1) list.add(new Position(position.row(), position.column() + 1));
    return list;
  }

  private Position neighborClosestToFire(Position position) {
    Set<Position> seen = new HashSet<>();
    HashMap<Position, Position> firstMove = new HashMap<>();
    Queue<Position> toVisit = new LinkedList<>(neighbors(position));
    for (Position initialMove : toVisit)
      firstMove.put(initialMove, initialMove);
    while (!toVisit.isEmpty()) {
      Position current = toVisit.poll();
      if (firePositions.contains(current))
        return firstMove.get(current);
      for (Position adjacent : neighbors(current)) {
        if (seen.contains(adjacent)) continue;
        toVisit.add(adjacent);
        seen.add(adjacent);
        firstMove.put(adjacent, firstMove.get(current));
      }
    }
    return position;
  }

  @Override
  public void setState(List<ModelElement> state, Position position) {
    firePositions.remove(position);
    for (;;) {
      if (!firefighterPositions.remove(position)) break;
    }
    for(ModelElement element : state){
      switch (element){
        case FIRE -> firePositions.add(position);
        case FIREFIGHTER -> firefighterPositions.add(position);
      }
    }
  }
}