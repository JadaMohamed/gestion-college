package application.controllers;

import java.util.Vector;

import application.model.Classe;
import application.model.Etudiant;
import application.repositories.EtudiantRepository;
import application.services.ClasseService;
import application.services.EtudiantService;
import application.utilities.PushAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModifierEtudiantSceneController {

    private Stage stage; // Reference to the stage of this modal

    private Etudiant activeEtudiant;

    // Method to set the stage of this modal
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return modifierEtudiantRootPane.getScene();
    }

    @FXML
    private Pane modifierEtudiantRootPane;

    @FXML
    private Button modifierButton, annulerButton;

    @FXML
    private ComboBox<Classe> classesComboBox;

    @FXML
    private ComboBox<String> sexeComboBox;

    @FXML
    private DatePicker dateNaissanceDatePicker;

    @FXML
    private ImageView photo;

    @FXML
    private HBox headerHBox;

    @FXML
    private TextField emailParentsTextField, emailTextField, nomTextField, prenomTextField, telephoneParentsTextField,
            telephoneTextField, cneTextField;

    @FXML
    public void handleModifierButtonOnAction(ActionEvent event) {
        if (emailTextField.getText().isBlank() || nomTextField.getText().isBlank()
                || prenomTextField.getText().isBlank() || emailParentsTextField.getText().isBlank()
                || telephoneTextField.getText().isBlank() || telephoneParentsTextField.getText().isBlank()
                || cneTextField.getText().isBlank()) {
            PushAlert.showAlert("Modification échoué", "Veuillez remplir le formulaire", AlertType.INFORMATION,
                    getStage());
        } else {

            Etudiant e = new Etudiant();
            e.setId(activeEtudiant.getId());
            e.setCne(cneTextField.getText());
            e.setNom(nomTextField.getText());
            e.setPrenom(prenomTextField.getText());
            e.setEmail(emailTextField.getText());
            e.setTelephone(telephoneTextField.getText());
            e.setTelephoneParent(telephoneParentsTextField.getText());
            e.setEmailParent(emailParentsTextField.getText());
            e.setDateNaissance(java.sql.Date.valueOf(dateNaissanceDatePicker.getValue()));
            e.setClasse(new Classe());
            e.getClasse().setId(classesComboBox.getSelectionModel().getSelectedItem().getId());
            e.setSexe(sexeComboBox.getSelectionModel().getSelectedItem().substring(0, 1));

            // Insert Student to the database
            EtudiantRepository.updateEtudiant(e);

            PushAlert.showAlert("Modification succès",
                    "La modification des informations de l'étudiant avec cne : " + activeEtudiant.getCne()
                            + " a été faite avec succès",
                    AlertType.INFORMATION,
                    getStage());
        }
    }

    @FXML
    public void handleAnnulerButtonOnAction(ActionEvent event) {
        // Close the stage when cancel button is clicked
        this.activeEtudiant = EtudiantService.getEtudiantById(activeEtudiant.getId());
        fillClassesComboBox();
        fillSexeComboBox();
        fillTextFields();

    }

    @FXML
    public void initialize(Stage stage, int idEtudiant) {
        this.stage = stage;

        this.activeEtudiant = EtudiantService.getEtudiantById(idEtudiant);
        fillClassesComboBox();
        fillSexeComboBox();
        fillTextFields();

        ImageView p = new ImageView();
        try {
            p.setImage(new Image(getClass().getResourceAsStream(activeEtudiant.getPhotoURL())));
            System.out.println("null");
            // Set size of the image view
            p.setFitWidth(24);
            p.setFitHeight(24);

            headerHBox.getChildren().add(p);
        } catch (Exception e) {
            p.setImage(
                    new Image(getClass().getResourceAsStream("/resources/images/profiles/default.png")));
            System.out.println(e);
        }
    }

    public void fillClassesComboBox() {

        Vector<Classe> allClasses = ClasseService.getAllClasses();
        classesComboBox.getItems().clear();
        classesComboBox.getItems().addAll(allClasses);
        for (Classe classe : classesComboBox.getItems()) {
            if (classe.getId() == activeEtudiant.getClasse().getId()) {
                classesComboBox.getSelectionModel().select(classe);
                break;
            }
        }
    }

    public void fillSexeComboBox() {
        sexeComboBox.getItems().clear();
        sexeComboBox.getItems().addAll("Male", "Female");
        if (activeEtudiant.getSexe().equals("M")) {
            sexeComboBox.getSelectionModel().select(0);
        } else {
            sexeComboBox.getSelectionModel().select(1);
        }
    }

    public void fillTextFields() {
        emailParentsTextField.setText(activeEtudiant.getEmailParent());
        emailTextField.setText(activeEtudiant.getEmail());
        nomTextField.setText(activeEtudiant.getNom());
        prenomTextField.setText(activeEtudiant.getPrenom());
        telephoneParentsTextField.setText(activeEtudiant.getTelephoneParent());
        telephoneTextField.setText(activeEtudiant.getTelephone());
        cneTextField.setText(activeEtudiant.getCne());
        dateNaissanceDatePicker.setValue(activeEtudiant.getDateNaissance().toLocalDate());

    }
}
