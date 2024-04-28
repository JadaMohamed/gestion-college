package application.controllers;

import java.io.IOException;

import application.repositories.AdministrateurRepository;
import application.repositories.LoginRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginSceneController {

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMssg;

    @FXML
    public void initialize() {
        if (passwordInput != null) {
            passwordInput.setOnKeyPressed(this::handleEnterKeyPressed);
        }
    }

    private void handleEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButtonAction(new ActionEvent());
        }
    }

    @FXML
    public void loginButtonAction(ActionEvent e) {
        if (!emailInput.getText().isBlank() && !passwordInput.getText().isBlank()) {
            validateLogin();
        } else {
            errorMssg.setText("Please Enter your Email and Password!");
        }
    }

    private void validateLogin() {
        try {
            int count = LoginRepository.validateLogin(emailInput.getText(), passwordInput.getText());
            if (count == 1) {
                int adminId = AdministrateurRepository.getAdminId(emailInput.getText());
                openHomePage(adminId);
            } else {
                errorMssg.setText("Email or Password are wrong. Please try again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openHomePage(int adminId) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../../resources/interfaces/AdministrateurBackofficeScene.fxml"));
            Parent root = loader.load();

            AdminstrateurBackofficeSceneController administrateueBackofficeController = loader.getController();
            administrateueBackofficeController.initialize(adminId);

            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crestwood college");

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
