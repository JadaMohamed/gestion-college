package application.controllers;

import application.repositories.BackOfficeSceneRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BackofficeSceneController {

    @FXML
    private Button absencesButton, accueilButton, classesButton, deconnecterButton, sallesButton, parametresButton, annulerButton;

    @FXML
    private Pane absencesPane, accueilPane, classesPane, sallesPane, parametresPane;

    @FXML
    private TextField nomFieldInfos, prenomFieldInfos, numFieldInfos, emailFieldSecurity,oldPasswordSecurity,newPasswordSecurity;

    @FXML
    private DatePicker datePickerInfos;

    @FXML
    private Label errorPasswordUpdate;

    private int currentAdminId;

    @FXML
    void handleSideBarButtonAction(ActionEvent event) {

        if (event.getSource() == accueilButton) {
            accueilPane.toFront();
            toggleStyleClass(accueilButton);
        } else if (event.getSource() == sallesButton) {
            sallesPane.toFront();
            toggleStyleClass(sallesButton);
        } else if (event.getSource() == absencesButton) {
            absencesPane.toFront();
            toggleStyleClass(absencesButton);
        } else if (event.getSource() == classesButton) {
            classesPane.toFront();
            toggleStyleClass(classesButton);
        } else if (event.getSource() == parametresButton) {
            parametresPane.toFront();
            toggleStyleClass(parametresButton);
        }
    }

    public void toggleStyleClass(Button event) {
        if (event == accueilButton) {
            accueilButton.getStyleClass().add("active");
            sallesButton.getStyleClass().removeAll("active");
            absencesButton.getStyleClass().removeAll("active");
            classesButton.getStyleClass().removeAll("active");
            parametresButton.getStyleClass().removeAll("active");
        } else if (event == sallesButton) {
            sallesButton.getStyleClass().add("active");
            accueilButton.getStyleClass().removeAll("active");
            absencesButton.getStyleClass().removeAll("active");
            classesButton.getStyleClass().removeAll("active");
            parametresButton.getStyleClass().removeAll("active");
        } else if (event == absencesButton) {
            absencesButton.getStyleClass().add("active");
            accueilButton.getStyleClass().removeAll("active");
            sallesButton.getStyleClass().removeAll("active");
            classesButton.getStyleClass().removeAll("active");
            parametresButton.getStyleClass().removeAll("active");
        } else if (event == classesButton) {
            classesButton.getStyleClass().add("active");
            accueilButton.getStyleClass().removeAll("active");
            sallesButton.getStyleClass().removeAll("active");
            absencesButton.getStyleClass().removeAll("active");
            parametresButton.getStyleClass().removeAll("active");
        } else if (event == parametresButton) {
            parametresButton.getStyleClass().add("active");
            classesButton.getStyleClass().removeAll("active");
            accueilButton.getStyleClass().removeAll("active");
            sallesButton.getStyleClass().removeAll("active");
            absencesButton.getStyleClass().removeAll("active");
        }
    }

    public void initialize(int adminId) {
        this.currentAdminId = adminId;
        loadAdminData();
    }

    public void loadAdminData() {
        try {
            ResultSet rs = BackOfficeSceneRepository.getAdminData(currentAdminId);
            if (rs.next()) {
                nomFieldInfos.setText(rs.getString("nom"));
                prenomFieldInfos.setText(rs.getString("prenom"));
                numFieldInfos.setText(rs.getString("telephone"));
                datePickerInfos.setValue(rs.getDate("dateNaissance").toLocalDate());
                emailFieldSecurity.setText(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sauvegardeButtonInfos(ActionEvent event) {
        try {
            BackOfficeSceneRepository.updateAdminData(currentAdminId, nomFieldInfos.getText(), prenomFieldInfos.getText(), numFieldInfos.getText(), java.sql.Date.valueOf(datePickerInfos.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelChanges() {
        loadAdminData();
    }

    @FXML
    public void sauvegardeButtonSecurity(ActionEvent event) {
        try {
            ResultSet rs = BackOfficeSceneRepository.validatePassword(currentAdminId, oldPasswordSecurity.getText());
            if (rs.next()) {
                BackOfficeSceneRepository.updatePassword(currentAdminId, emailFieldSecurity.getText(), newPasswordSecurity.getText());
                errorPasswordUpdate.setText("Votre mot de passe a été bien changé !");
            } else {
                errorPasswordUpdate.setText("L'ancien motDePasse est Incorrect !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
