package model2;

import util.Position;

import java.util.*;


public class FirefighterBoard implements Board {

  int distance = Integer.MAX_VALUE;
  private final int columnCount;
  private final int rowCount;
  private final int initialHospitalCount;
  private final int initialVirusCount;
  private final int initialDoctorCount;
  private  final int initialVaccinatedCount;
  private final List<Movable> movableList;
  private final List<Immovable> immovableList;
  private int step = 0;
  private final Random randomGenerator = new Random();



  public FirefighterBoard(int columnCount, int rowCount,int initialVirusCount, int initialDoctorCount,
                          int initialHospitalCount, int initialVaccinatedCount) {
    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.movableList = new ArrayList<>();
    this.immovableList = new ArrayList<>();

    this.initialVirusCount = initialVirusCount;
    this.initialDoctorCount = initialDoctorCount;

    this.initialHospitalCount = initialHospitalCount;
    this.initialVaccinatedCount = initialVaccinatedCount;

    initializeImmovable();
    initializeMovable();
  }


  public void initializeImmovable() {
    for (int i = 0; i < initialHospitalCount; i++){
      immovableList.add(new Hospital(randomEmptyPosition()));
    }
    for (int i = 0; i < initialVaccinatedCount; i++){
      immovableList.add(new Vaccinated(randomEmptyPosition()));
    }
  }

  public void initializeMovable() {
    for (int i = 0; i < initialVirusCount; i++) {
      movableList.add(new Virus(randomEmptyPosition()));
    }
    for (int i = 0; i < initialDoctorCount; i++) {
      movableList.add(new Doctor(randomEmptyPosition()));
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

    //  update for extinguishers
    for (Movable movable: currentMovableList) {
      if (!(movable instanceof Virus)) result.addAll(movable.update(this));
    }

    currentMovableList = new ArrayList<Movable>(movableList);
    // update for fire spreading
    if (step % 2 == 1){ //if step is odd -control the frequency of fire spread
      for (Movable movable: currentMovableList) {
        if (movable instanceof Virus) result.addAll(movable.update(this));
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

  public List<Position> neighbors(Position position) {
    List<Position> list = new ArrayList<>();
    if (position.row() > 0) list.add(new Position(position.row() - 1, position.column()));
    if (position.column() > 0) list.add(new Position(position.row(), position.column() - 1));
    if (position.row() < rowCount - 1) list.add(new Position(position.row() + 1, position.column()));
    if (position.column() < columnCount - 1) list.add(new Position(position.row(), position.column() + 1));
    return list;
  }


}