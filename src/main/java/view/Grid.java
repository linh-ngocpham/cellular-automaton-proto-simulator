package view;

import javafx.util.Pair;
import model.Item;
import util.Position;
import model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This interface represents a generic grid structure for displaying two-dimensional data.
 *
 * @param <E> The type of elements stored in the grid.
 */
public interface Grid {

  void repaint(List<Item> itemList, List<Position> clearList, Board board);

  /**
   * Set the dimensions of the grid to the specified number of columns, number of rows, square width,
   * and square height.
   *
   * @param columnCount The new number of columns in the grid.
   * @param rowCount The new number of rows in the grid.
   * @param squareWidth The width of each square within the grid.
   * @param squareHeight The height of each square within the grid.
   */
  void setDimensions(int columnCount, int rowCount, int squareWidth, int squareHeight);

  /**
   * Get the number of columns in the grid.
   *
   * @return The number of columns in the grid.
   */
  int columnCount();

  /**
   * Get the number of rows in the grid.
   *
   * @return The number of rows in the grid.
   */
  int rowCount();

  void initialize(Board board);
}
