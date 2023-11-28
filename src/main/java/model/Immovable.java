package model;

import util.Position;
import view.FirefighterGrid;

public interface Immovable {
    Position position();
    void paint(FirefighterGrid grid);
}
