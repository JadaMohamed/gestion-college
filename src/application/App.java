package application;

import application.controllers.TestNiveauGridController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/interfaces/TestNiveauGridScene.fxml"));
        Parent root = loader.load();

        // Get the controller
        TestNiveauGridController controller = loader.getController();
        // Initialize the controller
        controller.initialize();

        // Set the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
