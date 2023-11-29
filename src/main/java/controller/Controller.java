package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;
import model.Board;
import model.FirefighterBoard;
import model.Movable;
import modelVirus.VirusFightingBoard;
import util.Position;
import view.Grid;
import view.ViewElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Controller {

  public static final int PERIOD_IN_MILLISECONDS = 50;
  @FXML
  public Button restartButton;
  @FXML
  public Button oneStepButton;
  @FXML
  public Label generationNumberLabel;
  @FXML
  private ToggleButton pauseToggleButton;
  @FXML
  private ToggleButton playToggleButton;
  @FXML
  private Grid grid;
  private Timeline timeline;
  private Board board;

  @FXML
  private void initialize() {
    initializePlayAndPauseToggleButtons();
    initializeTimeline();
  }

  private void initializePlayAndPauseToggleButtons() {
    ToggleGroup toggleGroup = new PersistentToggleGroup();
    toggleGroup.getToggles().addAll(playToggleButton, pauseToggleButton);
    pauseToggleButton.setSelected(true);
  }


  private void setModel(FirefighterBoard firefighterBoard) {
    this.board = requireNonNull(firefighterBoard, "firefighter.model is null");
  }



  /*private void setModel(VirusFightingBoard firefighterBoard) {
    this.board = requireNonNull(firefighterBoard, "virusfighting.model is null");
  }*/

  private void updateBoard(){

    List<Position> updatedPositions = board.updateToNextGeneration();
    List<Position> updatedClearSquares = new ArrayList<>();
    List<Movable> updatedSquares = new ArrayList<>();
    for(Position updatedPosition : updatedPositions){
      Movable squareState = board.getMovableByPosition(updatedPosition);
      updatedClearSquares.add(updatedPosition);
      if (squareState != null){
        updatedSquares.add(squareState);
      }
    }
    grid.repaint(updatedSquares, updatedClearSquares, board);
    updateGenerationLabel(board.stepNumber());

    board.debug();
  }

  private void repaintGrid(){
    grid.initialize(board);
    updateGenerationLabel(board.stepNumber());
  }




  private void initializeTimeline() {
    Duration duration = new Duration(Controller.PERIOD_IN_MILLISECONDS);
    EventHandler<ActionEvent> eventHandler =
            event -> updateBoard();
    KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
    timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Animation.INDEFINITE);
  }

  public void play() {
    timeline.play();
  }

  public void pause() {
    timeline.pause();
  }

  public void pauseToggleButtonAction() {
    this.pause();
  }

  public void playToggleButtonAction() {
    this.play();
  }

  public void restartButtonAction() {
    this.pause();
    board.reset();
    pauseToggleButton.setSelected(true);
    repaintGrid();
  }




  public void initialize(int squareWidth, int squareHeight, int columnCount, int rowCount,

                         int initialFireCount,  int initialMountainCount, int initialRoadCount, int initialRockCount,
                         int initialFireFighterCount, int initialCloudCount,int initialMotorFireFighterCount) {
    grid.setDimensions(columnCount, rowCount, squareWidth, squareHeight);
    this.setModel(new FirefighterBoard(columnCount, rowCount,
                                       initialFireCount, initialMountainCount, initialRoadCount, initialRockCount,
                                        initialFireFighterCount, initialCloudCount,initialMotorFireFighterCount));
    repaintGrid();
  }



  /*public void initialize(int squareWidth, int squareHeight, int columnCount, int rowCount,
                         int hospitalCount, int initalVaccinatedCount) {
    grid.setDimensions(columnCount, rowCount, squareWidth, squareHeight);
    this.setModel(new VirusFightingBoard(columnCount, rowCount, hospitalCount, initalVaccinatedCount));
    repaintGrid();
  }
*/
  public void oneStepButtonAction() {
    this.pause();
    updateBoard();
  }

  private void updateGenerationLabel(int value){
    generationNumberLabel.setText(Integer.toString(value));
  }
}