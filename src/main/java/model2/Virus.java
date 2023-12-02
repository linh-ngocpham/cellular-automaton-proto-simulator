package model2;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.ArrayList;
import java.util.List;

public class Virus implements Movable {

    private final Position position;
    private Color color = Color.RED;

    int fireMvt = 0;

    Virus(Position position){
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

        if (immovable instanceof Vaccinated){
            Boolean x;
            if (fireCanSpread() == false) x = true;
            else x = false;
        }

        //getting a neighbor of an immovable object and movable object
        //if neiiofmov is null and neeiofimmov is null or neiiofimmov is a rock
        // check rock situation
        //if rock has been hit already, fire is spread

        for (Position neighborPosition  : neighborsOfExtinguisher){
            neighbourOfImmovable = board.getImmovableByPosition(neighborPosition);
            neighbourofMovable = (Movable) board.getMovableByPosition(neighborPosition);
            if (neighbourofMovable == null &&
                    (neighbourOfImmovable ==null || neighbourOfImmovable instanceof Vaccinated)){
                if (immovable instanceof Vaccinated){
                    if (fireCanSpread()){ //is true hihi
                        board.movableList().add(new Virus(neighborPosition));
                        result.add(neighborPosition);
                    }
                }

                else {
                    board.movableList().add(new Virus(neighborPosition));
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
        grid.paintCircle(position().row(), position().column(), color);
    }
}


