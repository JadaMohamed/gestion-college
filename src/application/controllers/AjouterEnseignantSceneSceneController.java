package application.controllers;

import application.model.Enseignant;
import application.repositories.EnseignantRepository;
import application.utilities.PushAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AjouterEnseignantSceneSceneController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return ajouterEenseignantRootPane.getScene();
    }

    @FXML
    private Pane ajouterEenseignantRootPane;

    @FXML
    private Button ajouterButton, annulerButton;

    @FXML
    private ComboBox<String> sexeComboBox;

    @FXML
    private DatePicker dateNaissanceDatePicker;

    @FXML
    private TextField emailTextField, nomTextField, prenomTextField,
            telephoneTextField;

    @FXML
    public void handleAjouterButtonOnAction(ActionEvent event) {
        if (emailTextField.getText().isBlank() || nomTextField.getText().isBlank()
                || telephoneTextField.getText().isBlank()) {
            PushAlert.showAlert("Ajout échoué", "Veuillez remplir le formulaire", AlertType.INFORMATION,
                    getStage());
        } else {
            Enseignant enseignant = new Enseignant();
            enseignant.setDateNaissance(java.sql.Date.valueOf(dateNaissanceDatePicker.getValue()));
            enseignant.setEmail(emailTextField.getText());
            enseignant.setNom(nomTextField.getText());
            enseignant.setPrenom(prenomTextField.getText());
            enseignant.setSexe(sexeComboBox.getSelectionModel().getSelectedItem().substring(0, 1));
            enseignant.setTelephone(telephoneTextField.getText());
            // Insert Student to the database
            EnseignantRepository.ajouterEnseignant(enseignant);
            PushAlert.showAlert("Ajout succès",
                    "L'ajout de l'enseignant avec email : " + enseignant.getEmail() + " a été faite avec succès",
                    AlertType.INFORMATION,
                    getStage());
            clearForm();
        }
    }

    @FXML
    public void handleAnnulerButtonOnAction(ActionEvent event) {
        // Close the stage when cancel button is clicked
        clearForm();

    }

    @FXML
    public void initialize(Stage stage) {
        this.stage = stage;

        fillSexeComboBox();

        stage.setOnCloseRequest(e -> {
            // Clear the items in sexeComboBox when the modal is closed
            clearForm();
        });
    }

    public void fillSexeComboBox() {
        sexeComboBox.getItems().clear();
        sexeComboBox.getItems().addAll("Male", "Female");
        sexeComboBox.getSelectionModel().select(0);
    }

    public void clearForm() {
        emailTextField.setText("");
        nomTextField.setText("");
        prenomTextField.setText("");
        telephoneTextField.setText("");
        dateNaissanceDatePicker.setValue(null);
    }
}
