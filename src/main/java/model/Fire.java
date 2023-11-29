package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fire implements Movable {

    private final Position position;
    private Color color = Color.RED;

    int fireMvt = 0;

    Fire(Position position){
        this.position = position;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public List<Position> update(FirefighterBoard board) {
        ArrayList<Position> result = new ArrayList<>();
        List<Position> neighborsOfExtinguisher = board.neighbors(position);

        Immovable neighbourOfImmovable;
        Movable neighbourofMovable;
        Immovable immovable = board.getImmovableByPosition(position);

        if (immovable instanceof Rock){
            Boolean x;
            if (fireCanSpread() == false) x = true;
            else x = false;
        }

        for (Position neighborPosition  : neighborsOfExtinguisher){
            neighbourOfImmovable = board.getImmovableByPosition(neighborPosition);
            neighbourofMovable = board.getMovableByPosition(neighborPosition);
            if (neighbourofMovable == null &&
                    (neighbourOfImmovable ==null || neighbourOfImmovable instanceof Rock)){
                if (immovable instanceof Rock){
                   if (fireCanSpread()){ //is true hihi 
                       board.movableList().add(new Fire(neighborPosition));
                       result.add(neighborPosition);
                   }
                }

                else {
                       board.movableList().add(new Fire(neighborPosition));
                       result.add(neighborPosition);
                }
            }
        }
        return result;
    }

    boolean fireCanSpread(){
        fireMvt++;
        if (fireMvt == 2){
            fireMvt = 0;
            return true;
        }
        return false;
    }


    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position().row(), position().column(), color);
    }
}
