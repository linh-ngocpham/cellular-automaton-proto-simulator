package model2;

import javafx.scene.paint.Color;
import util.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Extinguish {

    /** stores all common properties of classes that can extinguish fire, notably
     *  FIREFIGHTER CLOUD MOTORFIGHTER
     */

        public Position position;
        Color color;

        public Extinguish(Position position){
            this.position = position;
        }

        public Position position() {
            return position;
        }

        public List<Position> update(FirefighterBoard board){
        ArrayList<Position> result = new ArrayList<Position>();
        result.addAll(move(board));
        result.addAll(extinguishFire(board));
        return result;
      }

        private List<Position> extinguishFire(FirefighterBoard board) {

            List<Position> result = new ArrayList<>();
            List<Movable> movableList = board.movableList();
            List<Position> neighborsList = board.neighbors(position); //neighbors is now private, why ?

            Movable neighborMovable;

            for (Position neighborPosition: neighborsList) {
                neighborMovable = (Movable) board.getMovableByPosition(neighborPosition);
                if (neighborMovable instanceof Virus){
                    movableList.remove(neighborMovable);
                    result.add(neighborPosition);
                }
            }
            return result;
        }

        abstract List<Position> move(FirefighterBoard board);

}
