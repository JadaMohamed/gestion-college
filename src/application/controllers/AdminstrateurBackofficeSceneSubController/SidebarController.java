package application.controllers.AdminstrateurBackofficeSceneSubController;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController {

    private AdminstrateurBackofficeSceneController mainController;

    public SidebarController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {

    }

    @FXML
    public void handleSideBarButtonAction(ActionEvent event) {
        if (event.getSource() == mainController.getAccueilButton()) {
            mainController.getAccueilPane().toFront();
            toggleStyleClass(mainController.getAccueilButton());
        } else if (event.getSource() == mainController.getSallesButton()) {
            mainController.getSallesPane().toFront();
            toggleStyleClass(mainController.getSallesButton());
        } else if (event.getSource() == mainController.getClassesButton()) {
            mainController.getClassesPane().toFront();
            toggleStyleClass(mainController.getClassesButton());
        } else if (event.getSource() == mainController.getParametresButton()) {
            mainController.getParametresPane().toFront();
            toggleStyleClass(mainController.getParametresButton());
        } else if (event.getSource() == mainController.getDeconnecterButton()) {
            LoginService.seDeconnecter(mainController.getDeconnecterButton());
        } else if (event.getSource() == mainController.getAffectationButton()) {
            mainController.getAffectationPane().toFront();
            toggleStyleClass(mainController.getAffectationButton());
        } else if (event.getSource() == mainController.getAbsencesButton()) {
            mainController.getAbsencesPane().toFront();
            toggleStyleClass(mainController.getAbsencesButton());
        } else if (event.getSource() == mainController.getProfesseursButton()) {
            mainController.getProfesseursPane().toFront();
            toggleStyleClass(mainController.getProfesseursButton());
        }
    }

    public void toggleStyleClass(Button event) {
        Button[] buttons = {
                mainController.getAccueilButton(),
                mainController.getSallesButton(),
                mainController.getClassesButton(),
                mainController.getParametresButton(),
                mainController.getAffectationButton(),
                mainController.getAbsencesButton(),
                mainController.getProfesseursButton()
        };

        for (Button button : buttons) {
            if (button == event) {
                button.getStyleClass().add("active");
            } else {
                button.getStyleClass().removeAll("active");
            }
        }
    }
}
