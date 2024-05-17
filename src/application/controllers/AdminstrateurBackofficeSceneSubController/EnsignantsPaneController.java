package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.EnseignantService;
import application.utilities.ButtonClickHandler;
import application.utilities.CustomCellFactory;
import application.utilities.CustomDeleteEnseignantButton;
import application.utilities.CustomEditEnseignantButton;
import application.utilities.CustomEnseignantCoursCell;
import application.utilities.CustomStatusCell;
import application.utilities.CustomVoirEnseignantButton;
import application.utilities.ET0810;
import application.utilities.ET1012;
import application.utilities.ET1416;
import application.utilities.ET1618;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class EnsignantsPaneController {
        private AdminstrateurBackofficeSceneController mainController;

        public EnsignantsPaneController(AdminstrateurBackofficeSceneController mainController) {
                this.mainController = mainController;

        }

        public void initialize() {
                fillProfesseursTableView();
        }

        ButtonClickHandler<Map<String, String>> deleteEnseignantClickHandler = rowData -> {
        };

        ButtonClickHandler<Map<String, String>> editEnseignantClickHandler = rowData -> {
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
                mainController.getEnseignantEmploi8_10Column().setCellFactory(new ET0810());
                // 10-12
                mainController.getEnseignantEmploi10_12Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi10_12Column().setCellFactory(new ET1012());
                // 14-16
                mainController.getEnseignantEmploi14_16Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi14_16Column().setCellFactory(new ET1416());
                // 16-18
                mainController.getEnseignantEmploi16_18Column()
                                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
                mainController.getEnseignantEmploi16_18Column().setCellFactory(new ET1618());
                // Set the concatenated string to the tempText
                mainController.getEnseignantEmploiTableView().setItems(data);
        }
}
