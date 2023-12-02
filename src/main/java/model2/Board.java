package model2;

import util.Position;

import java.util.List;

/**
 * This interface represents a generic board for modeling various state-based systems.
 *
 * @param <S> The type of state represented on the board.
 */
public interface Board {


  /**
   * Get the number of rows in the board.
   *
   * @return The number of rows in the board.
   */
  int rowCount();

  /**
   * Get the number of columns in the board.
   *
   * @return The number of columns in the board.
   */
  int columnCount();

  /**
   * Update the board to its next generation or state. This method may modify the
   * internal state of the board and return a list of positions that have changed
   * during the update.
   *
   * @return A list of positions that have changed during the update.
   */
  List<Position> updateToNextGeneration();

  /**
   * Reset the board to its initial state.
   */
  void reset();

  /**
   * Get the current step number or generation of the board.
   *
   * @return The current step number or generation.
   */
  int stepNumber();

  Immovable getImmovableByPosition(Position position);
  Movable getMovableByPosition(Position position);
  List<Movable> movableList();
  //void debug();
}
