package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cloud extends Extinguish implements Movable {
    public Cloud(Position position) {
        super(position);
        this.color = Color.DARKSLATEGRAY;
    }


    @Override
    List<Position> move(FirefighterBoard board) {
        List<Position> result = new ArrayList<>();
        List<Position> cloudNeighbours = board.neighbors(this.position);
        List<Position> copyofCloudNeighbours = new ArrayList<>();

        for (Position p : cloudNeighbours) {
            if (board.getMovableByPosition(p) == null)
                copyofCloudNeighbours.add(p);
        }

        if (copyofCloudNeighbours.isEmpty()){
            return new ArrayList<Position>();
        }

        Random randomCloud = new Random();
        result.add(position);
        position = copyofCloudNeighbours.get(randomCloud.nextInt(copyofCloudNeighbours.size()));
        result.add(position);
        return result;



    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position().row(), position().column(), color);
    }
}
