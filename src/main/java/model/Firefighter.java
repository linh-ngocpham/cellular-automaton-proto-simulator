package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.*;

public class Firefighter extends Extinguish  implements Movable{

    public Firefighter(Position position) {
        super(position);
        this.color = Color.BLUE;
    }

    @Override
    public List<Position> move(FirefighterBoard board) {
        List<Position> result = new ArrayList<>();
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>();
        for (Position initialMove : board.neighbors(position)) {
            if (board.getMovableByPosition(initialMove) == null && !(board.getImmovableByPosition(initialMove) instanceof Mountain)) {
                firstMove.put(initialMove, initialMove);
                toVisit.add(initialMove);
            }
            if (board.getMovableByPosition(initialMove) instanceof Fire) return new ArrayList<Position>();
        }
        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            if (board.getMovableByPosition(current) instanceof Fire) {
                result.add(position);
                position = firstMove.get(current);
                result.add(position);
                return result;
            }
            for (Position adjacent : board.neighbors(current)) {
                if (seen.contains(adjacent) || (board.getMovableByPosition(adjacent) != null && !(board.getMovableByPosition(adjacent) instanceof Fire)) || board.getImmovableByPosition(adjacent) instanceof Mountain) continue;
                toVisit.add(adjacent);
                seen.add(adjacent);
                firstMove.put(adjacent, firstMove.get(current));
            }
        }
        return new ArrayList<Position>();

    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position().row(), position().column(), color);
    }
}
