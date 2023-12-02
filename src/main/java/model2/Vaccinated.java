package model2;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

public class Vaccinated implements Immovable{
    Position position;
    Color color = Color.YELLOW;
    public Vaccinated(Position position){
        this.position = position;
    }

    @Override
    public Position position() {
        return this.position;
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position.row(), position.column(), color);

    }
}
