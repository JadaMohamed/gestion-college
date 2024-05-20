package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.io.IOException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.controllers.AjouterEnseignantSceneSceneController;
import application.controllers.ConsulterSeanceSceneController;
import application.controllers.ModifierEnseignantSceneController;
import application.repositories.EnseignantRepository;
import application.services.EnseignantService;
import application.utilities.ButtonClickHandler;
import application.utilities.CustomCellFactory;
import application.utilities.CustomDeleteEnseignantButton;
import application.utilities.CustomEditEnseignantButton;
import application.utilities.CustomEnseignantCoursCell;
import application.utilities.CustomStatusCell;
import application.utilities.CustomVoirEnseignantButton;
import application.utilities.EmploiSubjectCard;
import application.utilities.PushAlert;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EnsignantsPaneController {
        private AdminstrateurBackofficeSceneController mainController;

        public EnsignantsPaneController(AdminstrateurBackofficeSceneController mainController) {
                this.mainController = mainController;

        }

        public void initialize() {
                fillProfesseursTableView();
        }

        ButtonClickHandler<Map<String, String>> deleteEnseignantClickHandler = rowData -> {
                Stage currentStage = (Stage) mainController.getScene().getWindow();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Confirm Deletion");
                alert.setContentText("Les séances attribuées à cet enseignant seront également supprimées");

                // Show and wait for the user's response
                alert.initOwner(currentStage);
                alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                                // User clicked OK, perform delete action
                                EnseignantRepository.supprimerEnseignant(Integer.parseInt(rowData.get("enseignantId")));

                                PushAlert.showAlert("Succès",
                                                "L'enseignant a été supprimé avec succès",
                                                AlertType.INFORMATION,
                                                currentStage);
                                fillProfesseursTableView();
                        }
                });

        };

        ButtonClickHandler<Map<String, String>> editEnseignantClickHandler = rowData -> {

                Stage currentStage = (Stage) mainController.getScene().getWindow();

                try {
                        // Load the FXML file for your modal form
                        FXMLLoader loader = new FXMLLoader(getClass()
                                        .getResource("../../../resources/interfaces/ModifierEnseignantScene.fxml"));
                        Parent root = loader.load();

                        ModifierEnseignantSceneController controller = loader.getController();
                        controller.initialize(currentStage, Integer.parseInt(rowData.get("enseignantId")));
                        // Create a new stage
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Modifier Enseignant");
                        stage.setScene(new Scene(root));
                        // Get screen dimensions
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                        // Center the stage on the screen
                        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                        // Show the stage
                        stage.showAndWait();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }

                fillProfesseursTableView();
        };

        ButtonClickHandler<Map<String, String>> voirEnseignantClickHandler = rowData -> {
                mainController.getActiveEnseignantNomText().setText(rowData.get("enseignantFullName"));
                mainController.getActiveEnseignantNomLabel().setText(rowData.get("enseignantFullName"));
                mainController.getActiveEnseignantTotalSeancesLabel().setText(rowData.get("enseignantTotalSeances"));
                if (Integer.parseInt(rowData.get("statut")) == 1) {

                        mainController.getActiveEnseignantStatutIcon().setImage(
                                        new Image(getClass().getResourceAsStream(
                                                        "/resources/images/icons/Badge_En_cours.png")));
                } else {
                        mainController.getActiveEnseignantStatutIcon().setImage(
                                        new Image(getClass().getResourceAsStream(
                                                        "/resources/images/icons/Badge_Hors_cours.png")));
                }

                fillEmploisDeTempsTableView(rowData.get("enseignantId"));
                mainController.getActiveEnseignantPane().toFront();
        };

        public void fillProfesseursTableView() {
                ObservableList<Map<String, String>> data = FXCollections
                                .observableArrayList();
                data.addAll(EnseignantService.getAllEnseignantsWithEngoingSeances());
                mainController.getProfesseursProfColumn()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getProfesseursProfColumn().setCellFactory(new CustomCellFactory());
                mainController.getProfesseursTelephoneColumn()
                                .setCellValueFactory(
                                                cellData -> new ReadOnlyObjectWrapper<>(
                                                                cellData.getValue().get("enseignantTelephone")));
                mainController.getProfesseursNbrCoursColumn()
                                .setCellValueFactory(
                                                cellData -> new ReadOnlyObjectWrapper<>(
                                                                cellData.getValue().get("enseignantTotalSeances")));
                mainController.getProfesseursNaissanceColumn()
                                .setCellValueFactory(
                                                cellData -> new ReadOnlyObjectWrapper<>(
                                                                cellData.getValue().get("enseignantDateNaissance")));
                mainController.getProfesseursStatutColumn().setCellValueFactory(
                                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("statut")));
                mainController.getProfesseursStatutColumn().setCellFactory(new CustomStatusCell());
                mainController.getProfesseursCoursEnCoursColumn()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getProfesseursCoursEnCoursColumn().setCellFactory(new CustomEnseignantCoursCell());

                mainController.getProfesseursDeleteActionColumn()
                                .setCellFactory(new CustomDeleteEnseignantButton(deleteEnseignantClickHandler));
                mainController.getProfesseursEditActionColumn()
                                .setCellFactory(new CustomEditEnseignantButton(editEnseignantClickHandler));
                mainController.getProfesseursVoirActionColumn()
                                .setCellFactory(new CustomVoirEnseignantButton(voirEnseignantClickHandler));
                // Set the concatenated string to the tempText
                mainController.getProfesseursTableView().setItems(data);
        }

        ButtonClickHandler<Map<String, String>> editSeanceClickHandler = rowData -> {

                Stage currentStage = (Stage) mainController.getScene().getWindow();
                try {
                        // Load the FXML file for your modal form
                        FXMLLoader loader = new FXMLLoader(
                                        getClass().getResource(
                                                        "../../../resources/interfaces/ConsulterSeanceScene.fxml"));
                        Parent root = loader.load();

                        ConsulterSeanceSceneController controller = loader.getController();
                        controller.initialize(currentStage, Integer.parseInt(rowData.get("activeSeanceId")));
                        // Create a new stage
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Modifier étudiant");
                        stage.setScene(new Scene(root));
                        // Get screen dimensions
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                        // Center the stage on the screen
                        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                        // Show the stage
                        stage.showAndWait();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
                mainController.fillListEtudiantsTableView("");
        };

        public void fillEmploisDeTempsTableView(String idEnseignant) {
                ObservableList<Map<String, String>> data = FXCollections
                                .observableArrayList();
                data.addAll(EnseignantService.getEmploiDeTemps(idEnseignant));
                
                mainController.getEnseignantEmploiJourColumn().setCellValueFactory(
                                cellData -> new ReadOnlyObjectWrapper<>(
                                                cellData.getValue().get("Day").substring(0, 3)));
                // 8-10
                mainController.getEnseignantEmploi8_10Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi8_10Column()
                                .setCellFactory(new EmploiSubjectCard("8_10", editSeanceClickHandler));
                // 10-12
                mainController.getEnseignantEmploi10_12Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi10_12Column()
                                .setCellFactory(new EmploiSubjectCard("10_12", editSeanceClickHandler));
                // 14-16
                mainController.getEnseignantEmploi14_16Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi14_16Column()
                                .setCellFactory(new EmploiSubjectCard("14_16", editSeanceClickHandler));
                // 16-18
                mainController.getEnseignantEmploi16_18Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi16_18Column()
                                .setCellFactory(new EmploiSubjectCard("16_18", editSeanceClickHandler));
                // Set the concatenated string to the tempText
                mainController.getEnseignantEmploiTableView().setItems(data);
        }

        public void ajouterEnseignant() {
                try {
                        // Load the FXML file for your modal form
                        FXMLLoader loader = new FXMLLoader(
                                        getClass().getResource(
                                                        "../../../resources/interfaces/AjouterEnseignantScene.fxml"));
                        Parent root = loader.load();

                        AjouterEnseignantSceneSceneController controller = loader.getController();
                        controller.initialize((Stage) mainController.getScene().getWindow());
                        // Create a new stage
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Ajouter étudiant");
                        stage.setScene(new Scene(root));
                        // Get screen dimensions
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

                        // Center the stage on the screen
                        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                        // Show the stage
                        stage.showAndWait();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }
}
