package application.utilities;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PushAlert {

    public static void showAlert(String title, String content, Alert.AlertType alertType, Stage primaryStage) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.initOwner(primaryStage);
        alert.showAndWait();
    }
}
