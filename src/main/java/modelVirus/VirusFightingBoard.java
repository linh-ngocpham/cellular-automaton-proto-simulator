package modelVirus;

import model.Board;
import model.Immovable;
import model.Movable;
import util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirusFightingBoard implements Board {

    private int step = 0;
    private final int columnCount;
    private final int rowCount;
   // private final int initialVirusCount;
    private final List<Movable> movableList;
    private final List<Immovable> immovableList;
    int hospitalCount;
    int initalVaccinatedCount;
    private final Random randomGenerator = new Random();



    public VirusFightingBoard(int columnCount, int rowCount,
                              int hospitalCount, int initalVaccinatedCount){
        this.columnCount = columnCount;
        this.rowCount = rowCount;
        this.movableList = new ArrayList<>();
        this.immovableList = new ArrayList<>();

        this.hospitalCount = hospitalCount;
        this.initalVaccinatedCount = initalVaccinatedCount;

    }

    /*public void initializeMovable() {
        for (int i = 0; i < initialVirusCount; i++) {
            movableList.add(new Virus(randomEmptyPosition()));
        }
    }
     */


    public void initializeImmovable() {
        for (int i = 0; i < hospitalCount; i++) {
            immovableList.add(new Hospital(randomEmptyPosition()));
        }
        for (int i = 0; i < initalVaccinatedCount; i++) {
            immovableList.add(new Vaccinated(randomEmptyPosition()));
        }
    }


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

    @Override
    public List<Position> updateToNextGeneration() {
        return null;
    }

    @Override
    public void reset() {
        step = 0;
        immovableList.clear();
        initializeImmovable();
    }

    @Override
    public int stepNumber() {
        return step;
    }

    public Immovable getImmovableByPosition(Position position) {
        for (Immovable immovable : immovableList) {
            if (immovable.position().equals(position)) {
                return immovable;
            }
        }
        return null;
    }

    public Movable getMovableByPosition(Position position) {
        for (Movable movable : movableList) {
            if (movable.position().equals(position)) {
                return movable;
            }
        }
        return null;
    }

    @Override
    public List<Movable> movableList() {
        return movableList;
    }

    @Override
    public void debug() {

    }


}
