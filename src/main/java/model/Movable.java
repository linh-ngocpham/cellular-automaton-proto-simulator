package model;

import util.Position;
import view.FirefighterGrid;

import java.util.List;

public interface Movable {
    Position position();

    List<Position> update(FirefighterBoard board);

    void paint(FirefighterGrid grid);
}
