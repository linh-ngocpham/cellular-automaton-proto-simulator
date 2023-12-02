package app;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SimulatorApplication extends javafx.application.Application {
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final String APP_NAME = "Firefighter simulator";
  private static final int ROW_COUNT = 20;
  private static final int COLUMN_COUNT = 20;
  private static final int BOX_WIDTH = 50;
  private static final int BOX_HEIGHT = 50;


  public static final int INITIAL_FIRE_COUNT = 3;
  public static final int INITIAL_FIREFIGHTER_COUNT = 6;

  public static final int INITIAL_MOUNTAINCOUNT = 3;

  public static final int INITIAL_ROAD_COUNT = 1;

  public static final int INITIAL_ROCK_COUNT = 2;

  public static final int INITIAL_CLOUD_COUNT = 2;
  public static final int INITIAL_MOTORFIREFIGHTER_COUNT = 2;



/*
  public static final int INITIAL_HOSPITAL_COUNT = 2;
  public static final int INITIAL_VACCINATED_COUNT = 6;
*/
  private Stage primaryStage;
  private Parent view;
  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    initializePrimaryStage(primaryStage);
    initializeView();
    showScene();
  }

  private void initializeView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    URL location = SimulatorApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loader.load();
    Controller controller = loader.getController();
    controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,
            INITIAL_FIRE_COUNT, INITIAL_MOUNTAINCOUNT, INITIAL_ROAD_COUNT, INITIAL_ROCK_COUNT,
            INITIAL_FIREFIGHTER_COUNT, INITIAL_CLOUD_COUNT,INITIAL_MOTORFIREFIGHTER_COUNT);


    /*controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,
            INITIAL_HOSPITAL_COUNT, INITIAL_VACCINATED_COUNT
            )*/
  }
  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}


/***
 * 
 * pour lancer model2
 */

/*
package app;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SimulatorApplication extends javafx.application.Application {
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final String APP_NAME = "Firefighter simulator";
  private static final int ROW_COUNT = 20;
  private static final int COLUMN_COUNT = 20;
  private static final int BOX_WIDTH = 50;
  private static final int BOX_HEIGHT = 50;

  public static final int INITIAL_HOSPITAL_COUNT = 2;

  public static final int INITIAL_DOCTOR_COUNT = 10;
  public static final int INITIAL_VACCINATED_COUNT = 6;
  public static final int INITIAL_VIRUS_COUNT = 6;




/*
  public static final int INITIAL_FIRE_COUNT = 3;
  public static final int INITIAL_FIREFIGHTER_COUNT = 6;

  public static final int INITIAL_MOTORFIREFIGHTER_COUNT = 6;

  public static final int INITIAL_MOUNTAINCOUNT = 3;

  public static final int INITIAL_ROAD_COUNT = 1;

  public static final int INITIAL_ROCK_COUNT = 2;
    public static final int INITIAL_CLOUD_COUNT = 2;


 

  private Stage primaryStage;
  private Parent view;
  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    initializePrimaryStage(primaryStage);
    initializeView();
    showScene();
  }

  private void initializeView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    URL location = SimulatorApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loader.load();
    Controller controller = loader.getController();
/*
    controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,
            INITIAL_FIRE_COUNT, INITIAL_MOUNTAINCOUNT, INITIAL_ROAD_COUNT, INITIAL_ROCK_COUNT,
            INITIAL_FIREFIGHTER_COUNT, INITIAL_CLOUD_COUNT);




    controller.initialize(BOX_WIDTH, BOX_HEIGHT, COLUMN_COUNT, ROW_COUNT,
            INITIAL_VIRUS_COUNT,INITIAL_DOCTOR_COUNT,
            INITIAL_HOSPITAL_COUNT, INITIAL_VACCINATED_COUNT

            );


  }
  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

*/
