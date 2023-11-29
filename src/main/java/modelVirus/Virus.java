package modelVirus;

import javafx.scene.paint.Color;
import model.FirefighterBoard;
import model.Movable;
import util.Position;
import view.FirefighterGrid;

import java.util.List;

public class Virus implements Movable {
    private final Position position;
    private Color color = Color.RED;

    int virusSpread = 0;

    Virus(Position position){
        this.position = position;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public List<Position> update(FirefighterBoard board) {
        return null;
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position().row(), position().column(), color);
    }
}
