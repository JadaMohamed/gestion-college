package application.controllers;

import java.util.Vector;

import application.model.Classe;
import application.model.Etudiant;
import application.repositories.EtudiantRepository;
import application.services.ClasseService;
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

public class AjouterEtudiantSceneSceneController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return ajouterEtudiantRootPane.getScene();
    }

    @FXML
    private Pane ajouterEtudiantRootPane;

    @FXML
    private Button ajouterButton, annulerButton;

    @FXML
    private ComboBox<Classe> classesComboBox;

    @FXML
    private ComboBox<String> sexeComboBox;

    @FXML
    private DatePicker dateNaissanceDatePicker;

    @FXML
    private TextField emailParentsTextField, emailTextField, nomTextField, prenomTextField, telephoneParentsTextField,
            telephoneTextField, cneTextField;

    @FXML
    public void handleAjouterButtonOnAction(ActionEvent event) {
        if (emailTextField.getText().isBlank() || nomTextField.getText().isBlank()
                || prenomTextField.getText().isBlank() || emailParentsTextField.getText().isBlank()
                || telephoneTextField.getText().isBlank() || telephoneParentsTextField.getText().isBlank()
                || cneTextField.getText().isBlank()) {
            PushAlert.showAlert("Ajout échoué", "Veuillez remplir le formulaire", AlertType.INFORMATION,
                    getStage());
        } else {
            Etudiant etudiant = new Etudiant();
            etudiant.setClasse(new Classe());
            etudiant.getClasse().setId(classesComboBox.getSelectionModel().getSelectedItem().getId());
            etudiant.setCne(cneTextField.getText());
            etudiant.setDateNaissance(java.sql.Date.valueOf(dateNaissanceDatePicker.getValue()));
            etudiant.setEmail(emailTextField.getText());
            etudiant.setEmailParent(emailParentsTextField.getText());
            etudiant.setNom(nomTextField.getText());
            etudiant.setPrenom(prenomTextField.getText());
            etudiant.setSexe(sexeComboBox.getSelectionModel().getSelectedItem().substring(0, 1));
            etudiant.setTelephone(telephoneTextField.getText());
            etudiant.setTelephoneParent(telephoneParentsTextField.getText());

            // Insert Student to the database
            EtudiantRepository.ajouterEtudiant(etudiant);
            PushAlert.showAlert("Ajout succès",
                    "L'ajout de l'étudiant avec cne : " + etudiant.getCne() + " a été faite avec succès",
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

        fillClassesComboBox();
        fillSexeComboBox();

        stage.setOnCloseRequest(e -> {
            // Clear the items in sexeComboBox when the modal is closed
            clearForm();
        });
    }

    public void fillClassesComboBox() {

        Vector<Classe> allClasses = ClasseService.getAllClasses();
        classesComboBox.getItems().clear();
        classesComboBox.getItems().addAll(allClasses);
        if (!allClasses.isEmpty()) {
            classesComboBox.getSelectionModel().select(0);
        }
    }

    public void fillSexeComboBox() {
        sexeComboBox.getItems().clear();
        sexeComboBox.getItems().addAll("Male", "Female");
        sexeComboBox.getSelectionModel().select(0);
    }

    public void clearForm() {

        emailParentsTextField.setText("");
        emailTextField.setText("");
        nomTextField.setText("");
        prenomTextField.setText("");
        telephoneParentsTextField.setText("");
        telephoneTextField.setText("");
        cneTextField.setText("");
        dateNaissanceDatePicker.setValue(null);
    }
}
