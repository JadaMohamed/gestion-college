package application;

// import application.controllers.BackofficeSceneController;
import application.controllers.LoginSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/interfaces/LoginScene.fxml"));
        Parent root = loader.load();

        // Get the controller
        LoginSceneController controller = loader.getController();
        // Initialize the controller
        controller.initialize();

        // Set the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
