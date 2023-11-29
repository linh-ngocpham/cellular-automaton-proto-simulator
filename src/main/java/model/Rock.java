package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

public class Rock implements Immovable{
    Position position;
    Color color = Color.SADDLEBROWN;
    public Rock(Position position){
        this.position = position;
    }

    @Override
    public Position position() {
        return this.position;
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintCircle(position.row(), position.column(), color);

    }
}
