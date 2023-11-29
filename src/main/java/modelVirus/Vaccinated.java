package modelVirus;

import javafx.scene.paint.Color;
import model.Immovable;
import util.Position;
import view.FirefighterGrid;

public class Vaccinated implements Immovable {
    private final Position position;
    private Color color = Color.GREY;

    public Vaccinated(Position position) {
        this.position = position;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintCircle(position().row(), position().column(), color);
    }
}
