package application.services;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginService {

    public static void seDeconnecter(Button deconnecterButton) {
        try {
            // Afficher la scène de connexion
            FXMLLoader loader = new FXMLLoader(
                    LoginService.class.getResource("../../resources/interfaces/LoginScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) deconnecterButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");

            // Get screen dimensions
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Center the stage on the screen
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
