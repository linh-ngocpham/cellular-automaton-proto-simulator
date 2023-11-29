package Virus1;
import javafx.scene.paint.Color;
import model.FirefighterBoard;
import model.Movable;
import util.Position;
import view.FirefighterGrid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Virus implements Movable {
    Position position;
    Color color = Color.BLACK;
    int virusMove = 0;
    public Virus(Position position){
        this.position = position;
    }
    @Override
    public Position position() {
        return this.position;
    }

    @Override
    public List<Position> update(FirefighterBoard board) {
        ArrayList<Position> result = new ArrayList<>();
        List<Position> neighborsOfExtinguisher = board.neighbors(position);
        Movable neighbourofMovable;
        for (Position neighborPosition  : neighborsOfExtinguisher){
            neighbourofMovable = board.getMovableByPosition(neighborPosition);
            if (neighbourofMovable == null){
                if (virusCanSpread()){
                        board.movableList().add(new Virus(neighborPosition));
                        result.add(neighborPosition);

                }

                else {
                    board.movableList().add(new Virus(neighborPosition));
                    result.add(neighborPosition);
                }
            }
        }
        return result;
    }
    boolean virusCanSpread(){
        virusMove++;
        if (virusMove == 2){
            virusMove = 0;
            return true;
        }
        return false;
    }

    @Override
    public void paint(FirefighterGrid grid) {grid.paintSquare(position().row(), position().column(), color);

    }
}
