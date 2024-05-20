package application.controllers;

import application.model.Enseignant;
import application.repositories.EnseignantRepository;
import application.services.EnseignantService;
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

public class ModifierEnseignantSceneController {

    private Stage stage;
    private Enseignant activeEnseignant;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return modifierEnseignantRootPane.getScene();
    }

    @FXML
    private Pane modifierEnseignantRootPane;

    @FXML
    private Button modifierButton, annulerButton;

    @FXML
    private ComboBox<String> sexeComboBox;

    @FXML
    private DatePicker dateNaissanceDatePicker;

    @FXML
    private ImageView photo;

    @FXML
    private HBox headerHBox;

    @FXML
    private TextField emailTextField, nomTextField, prenomTextField,
            telephoneTextField;

    @FXML
    public void handleModifierButtonOnAction(ActionEvent event) {
        if (emailTextField.getText().isBlank() || nomTextField.getText().isBlank()
                || prenomTextField.getText().isBlank()
                || telephoneTextField.getText().isBlank()) {
            PushAlert.showAlert("Modification échoué", "Veuillez remplir le formulaire", AlertType.INFORMATION,
                    getStage());
        } else {

            Enseignant e = new Enseignant();
            e.setId(activeEnseignant.getId());
            e.setNom(nomTextField.getText());
            e.setPrenom(prenomTextField.getText());
            e.setEmail(emailTextField.getText());
            e.setTelephone(telephoneTextField.getText());
            e.setDateNaissance(java.sql.Date.valueOf(dateNaissanceDatePicker.getValue()));
            e.setSexe(sexeComboBox.getSelectionModel().getSelectedItem().substring(0, 1));

            // Insert Student to the database
            EnseignantRepository.modifierEnseignant(e);

            PushAlert.showAlert("Modification succès",
                    "La modification des informations de l'enseignant avec email : " + activeEnseignant.getEmail()
                            + " a été faite avec succès",
                    AlertType.INFORMATION,
                    getStage());
        }
    }

    @FXML
    public void handleAnnulerButtonOnAction(ActionEvent event) {
        // Close the stage when cancel button is clicked
        this.activeEnseignant = EnseignantService.getEnseignantById(activeEnseignant.getId());
        fillSexeComboBox();
        fillTextFields();
    }

    @FXML
    public void initialize(Stage stage, int idEnseignant) {
        this.stage = stage;

        this.activeEnseignant = EnseignantService.getEnseignantById(idEnseignant);
        fillSexeComboBox();
        fillTextFields();

        ImageView p = new ImageView();
        try {
            p.setImage(new Image(getClass().getResourceAsStream(activeEnseignant.getPhotoURL())));
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

    public void fillSexeComboBox() {
        sexeComboBox.getItems().clear();
        sexeComboBox.getItems().addAll("Male", "Female");
        if (activeEnseignant.getSexe().equals("M")) {
            sexeComboBox.getSelectionModel().select(0);
        } else {
            sexeComboBox.getSelectionModel().select(1);
        }
    }

    public void fillTextFields() {

        emailTextField.setText(activeEnseignant.getEmail());
        nomTextField.setText(activeEnseignant.getNom());
        prenomTextField.setText(activeEnseignant.getPrenom());
        telephoneTextField.setText(activeEnseignant.getTelephone());
        dateNaissanceDatePicker.setValue(activeEnseignant.getDateNaissance().toLocalDate());

    }
}
