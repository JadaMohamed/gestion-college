package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.SQLException;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.SeanceService;

public class SallesPaneController {
    private AdminstrateurBackofficeSceneController mainController;

    public SallesPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        updateSallesOccupesLabels();
    }

    public void updateSallesOccupesLabels() {
        try {
            int[] nombreSallesOccupes = SeanceService.getNombreSallesOccupes();
            mainController.setNombreLabOccupesText(String.valueOf(2 - nombreSallesOccupes[0]));
            mainController.setSalleDisponiblesText(String.valueOf(17 - nombreSallesOccupes[1]));
            mainController.setNombreSalleSportOccupesText(String.valueOf(3 - nombreSallesOccupes[2]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
