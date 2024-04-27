package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import application.database.SqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public void initialize() {
        // Ajouter un écouteur d'événements pour la touche "Entrée" sur le champ de mot de passe
        passwordInput.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                // Appeler une méthode de validation de connexion
                loginButtonAction(new ActionEvent());
            }
        });
    }
    public void loginButtonAction(ActionEvent e){
        if(emailInput.getText().isBlank() == false && passwordInput.getText().isBlank() == false){
            // errorMssg.setText("Processus de connexion en cours");
            validateLogin();
        } else{
            errorMssg.setText("Please Enter your Email and Password !");
        }
    }
    public void validateLogin() {
        try {
            java.sql.Connection connectDB = SqlConnection.getConnection();
            String verifyLogin = "SELECT count(1) FROM adsministrateur WHERE email = '" + emailInput.getText() + "' AND motDePass = '" + passwordInput.getText() + "' ";
            java.sql.Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                int count = queryResult.getInt(1);
                if (count == 1) {
                    // Connexion réussie
                    openHomePage();
                } else {
                    // Échec de connexion
                    errorMssg.setText("Email or Password are wrong. Please try again !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openHomePage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../../resources/interfaces/BackofficeScene.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow(); 
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
