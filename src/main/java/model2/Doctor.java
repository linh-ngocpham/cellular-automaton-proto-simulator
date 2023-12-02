package model2;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.*;

public class Doctor extends Extinguish  implements Movable{

    public Doctor(Position position) {
        super(position);
        this.color = Color.BLUE;
    }

    @Override
    public List<Position> move(FirefighterBoard board) {
        List<Position> result = new ArrayList<>();
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>();



        // where ff can go to
        // if its empty and not a mointain, then go to that place
        for (Position initialMove : board.neighbors(position)) {
            if (board.getMovableByPosition(initialMove) == null && !(board.getImmovableByPosition(initialMove) instanceof Hospital)) {
                firstMove.put(initialMove, initialMove);
                toVisit.add(initialMove);
            }
            if (board.getMovableByPosition(initialMove) instanceof Virus)
                return new ArrayList<Position>();
        }

        // next place to go to
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (board.getMovableByPosition(current) instanceof Virus) {
                result.add(position);
                position = firstMove.get(current);
                result.add(position);
                return result;
            }


            for (Position adjacent : board.neighbors(current)) {
                /**
                 * cond 3 1-- have we seen it
                 * 2-- is it a movable? if so, is it anything but a fire
                 * 3-- is it a mountain ?
                 *
                 * if any of these are unsatisfied, we skip that adjacent
                 */
                if (seen.contains(adjacent) ||
                                (board.getMovableByPosition(adjacent) != null &&
                                !(board.getMovableByPosition(adjacent) instanceof Virus)) ||
                                board.getImmovableByPosition(adjacent) instanceof Hospital)
                    continue;
                toVisit.add(adjacent);
                seen.add(adjacent);
                firstMove.put(adjacent, firstMove.get(current));
            }
        }
        return new ArrayList<Position>();
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintCircle(position().row(), position().column(), color);
    }
}
