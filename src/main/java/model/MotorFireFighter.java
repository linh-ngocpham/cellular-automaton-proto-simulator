package model;

import javafx.scene.paint.Color;
import util.Position;
import view.FirefighterGrid;

import java.util.List;

import java.util.*;

public class MotorFireFighter extends Extinguish implements Movable{
    public MotorFireFighter(Position position) {
        super(position);
        this.color = Color.PURPLE;
    }

    public List<Position> move(FirefighterBoard board) {
        List<Position> result = new ArrayList<>();
        Set<Position> seen = new HashSet<>();
        HashMap<Position, Position> firstMove = new HashMap<>();
        Queue<Position> toVisit = new LinkedList<>();
        for (Position initialMove : board.neighbors(position)) {
            if (board.getMovableByPosition(initialMove) == null && !(board.getImmovableByPosition(initialMove) instanceof Mountain)) {
                toVisit.add(initialMove);
            }
            if (board.getMovableByPosition(initialMove) instanceof Fire) return new ArrayList<Position>();
        }

        while (!toVisit.isEmpty()) {
            Position current = toVisit.poll();
            for (Position neighbors : board.neighbors(current)) {
                if (board.getMovableByPosition(neighbors) == null && !(board.getImmovableByPosition(neighbors) instanceof Mountain)) {
                    firstMove.put(neighbors, neighbors);
                }
                if (board.getMovableByPosition(neighbors) instanceof Fire) {
                    result.add(position);
                    position = current;
                    result.add(position);
                    return result;
                }
            }
        }

        toVisit.addAll(firstMove.values());

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

    public String toString(){
        return "Pompier position : [" + position.row() + ", " + position.column()+ "]";
    }

    @Override
    public void paint(FirefighterGrid grid) {
        grid.paintSquare(position().row(), position().column(), color);
    }
}

