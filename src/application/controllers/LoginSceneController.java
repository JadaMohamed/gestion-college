package application.controllers;

import java.io.IOException;

import application.repositories.LoginRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
                int adminId = LoginRepository.getAdminId(emailInput.getText());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/interfaces/BackofficeScene.fxml"));
            Parent root = loader.load();
            
            BackofficeSceneController backofficeController = loader.getController();
            backofficeController.initialize(adminId);
    
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crestwood college");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
