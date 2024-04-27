package application.controllers;

import application.controllers.BackofficeSubControllers.AccueilPaneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class BackofficeSceneController {

    @FXML
    private AccueilPaneController accueilPaneController;

    @FXML
    private Button absencesButton, accueilButton, classesButton, deconnecterButton, sallesButton, parametresButton;

    @FXML
    private Pane absencesPane, accueilPane, classesPane, sallesPane, parametresPane;

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

    public void initialize() {

    }

}