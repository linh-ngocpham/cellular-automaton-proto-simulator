package firefighter.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FirefighterBoardTest {
  @Test
  void testColumnCount(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, 1, 3);
    assertThat(board.columnCount()).isEqualTo(20);
  }
  @Test
  void testRowCount(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, 1, 3);
    assertThat(board.rowCount()).isEqualTo(10);
  }
  @Test
  void testStepNumber(){
    Board<List<ModelElement>> board = new FirefighterBoard(20, 10, 1, 3);
    for(int index = 0; index < 10; index++){
      assertThat(board.stepNumber()).isEqualTo(index);
      board.updateToNextGeneration();
    }
    assertThat(board.stepNumber()).isEqualTo(10);
  }
}
