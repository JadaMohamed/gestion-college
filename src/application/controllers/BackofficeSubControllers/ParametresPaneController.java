package application.controllers.BackofficeSubControllers;

import application.controllers.BackofficeSceneController;
import application.services.AdministrateurService;
import application.utilities.PushAlert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ParametresPaneController {

    private BackofficeSceneController mainController;

    public ParametresPaneController(BackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {

    }

    public void annulerLesModifications() {
        this.loadAdminData();
    }

    public void loadAdminData() {
        AdministrateurService.loadAdminData(mainController.getCurrentAdminId(), mainController.getNomFieldParametres(),
                mainController.getPreomFieldParametres(),
                mainController.getTelephoneFieldParametres(),
                mainController.getDateBirthDatePickerParametres(),
                mainController.getEmailFieldParametres());
    }

    public void handleSauvegarderButtonSecurityParameters() {
        boolean status = AdministrateurService.updatePassword(mainController.getCurrentAdminId(),
                mainController.getOldPasswordParametres().getText(),
                mainController.getNewPasswordParametres().getText(),
                mainController.getEmailFieldParametres().getText());
        if (status) {
            Stage currentStage = (Stage) mainController.getScene().getWindow();
            PushAlert.showAlert("Password Updated", "Your password has been successfully changed!",
                    AlertType.INFORMATION,
                    currentStage);

        } else {
            Stage currentStage = (Stage) mainController.getScene().getWindow();
            PushAlert.showAlert("Password update failed", "Your old password is incorrect!",
                    AlertType.ERROR,
                    currentStage);
        }
    }

    public void handleSauvegarderButtonInfosParameters() {
        boolean status = AdministrateurService.updateAdminData(mainController.getCurrentAdminId(),
                mainController.getNomFieldParametres().getText(), mainController.getPreomFieldParametres().getText(),
                mainController.getTelephoneFieldParametres().getText(),
                java.sql.Date.valueOf(mainController.getDateBirthDatePickerParametres().getValue()));
        if (status) {
            Stage currentStage = (Stage) mainController.getScene().getWindow();
            PushAlert.showAlert("Personal infos Updated", "Your Personal infos has been successfully changed!",
                    AlertType.INFORMATION,
                    currentStage);

        } else {
            Stage currentStage = (Stage) mainController.getScene().getWindow();
            PushAlert.showAlert("Personal infos update failed", "Your Personal infos hasn't updated!",
                    AlertType.ERROR,
                    currentStage);
        }
    }

}
