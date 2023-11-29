package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

public class Road implements Immovable{
    Position position;
    Color color = Color.BLACK;
    public Road(Position position){
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
