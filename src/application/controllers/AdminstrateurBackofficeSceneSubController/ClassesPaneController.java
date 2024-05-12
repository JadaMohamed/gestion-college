package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.io.IOException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.controllers.AjouterEtudiantSceneSceneController;
import application.controllers.ModifierEtudiantSceneController;
import application.model.Etudiant;
import application.repositories.ClasseRepository;
import application.repositories.EtudiantRepository;
import application.services.ClasseService;
import application.services.SeanceService;
import application.utilities.ButtonClickHandler;
import application.utilities.CustomCellFactory;
import application.utilities.CustomClasseCell;
import application.utilities.CustomClasseCellButton;
import application.utilities.CustomDeleteEtudiantButton;
import application.utilities.CustomEditEtudiantButton;
import application.utilities.CustomStatusCell;
import application.utilities.ET0810;
import application.utilities.ET1012;
import application.utilities.ET1416;
import application.utilities.ET1618;
import application.utilities.EtudiantContactCell;
import application.utilities.EtudiantContactParentsCell;
import application.utilities.EtudiantNomPhotoCell;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ClassesPaneController {
    private AdminstrateurBackofficeSceneController mainController;

    public ClassesPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;

    }

    public void initialize() {
        update_emeCoursEnCours();
    }

    public void update_emeCoursEnCours() {
        int[] nombreCoursParNiveau = SeanceService.getNombreCoursParNiveau();
        mainController.setNombre3emeEnCoursText(String.valueOf(nombreCoursParNiveau[3]));
        mainController.setNombre4emeEnCoursText(String.valueOf(nombreCoursParNiveau[2]));
        mainController.setNombre5emeEnCoursText(String.valueOf(nombreCoursParNiveau[1]));
        mainController.setNombre6emeEnCoursText(String.valueOf(nombreCoursParNiveau[0]));
    }

    public void fillEmploisDeTempsTableView(Map<String, String> rowData,
            TableColumn<Map<String, String>, String> classeEmploiJourColumn,
            TableView<Map<String, String>> classeEmploiTableView,
            TableColumn<Map<String, String>, Map<String, String>> classeEmploi8_10Column,
            TableColumn<Map<String, String>, Map<String, String>> classeEmploi10_12Column,
            TableColumn<Map<String, String>, Map<String, String>> classeEmploi14_16Column,
            TableColumn<Map<String, String>, Map<String, String>> classeEmploi16_18Column) {

        ObservableList<Map<String, String>> data = FXCollections
                .observableArrayList();
        data.addAll(ClasseService.getEmploiDeTemps(rowData.get("classeId")));
        classeEmploiJourColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("Day").substring(0, 3)));
        // 8-10
        classeEmploi8_10Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        classeEmploi8_10Column.setCellFactory(new ET0810());
        // 10-12
        classeEmploi10_12Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        classeEmploi10_12Column.setCellFactory(new ET1012());
        // 14-16
        classeEmploi14_16Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        classeEmploi14_16Column.setCellFactory(new ET1416());
        // 16-18
        classeEmploi16_18Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        classeEmploi16_18Column.setCellFactory(new ET1618());
        // Set the concatenated string to the tempText
        classeEmploiTableView.setItems(data);
    }

    public void setActiveClasseInformation(Map<String, String> rowData, Label activeClasseLabel,
            Label activeClasseNomLabel, Label activeClasseEffectif, ImageView activeClasseStatutIcon) {

        activeClasseLabel.setText(rowData.get("classeNom"));
        activeClasseNomLabel.setText(rowData.get("classeNom"));
        activeClasseEffectif.setText(rowData.get("effectif"));

        if (Integer.parseInt(rowData.get("status")) == 1) {

            activeClasseStatutIcon.setImage(
                    new Image(getClass().getResourceAsStream("/resources/images/icons/Badge_En_cours.png")));
        } else {
            activeClasseStatutIcon.setImage(
                    new Image(getClass().getResourceAsStream("/resources/images/icons/Badge_Hors_cours.png")));
        }
        StringBuilder builder = new StringBuilder();

        // Iterate over the entries of the rowData map
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            // Concatenate the key-value pair into the StringBuilder
            builder.append(entry.getKey()).append(": ").append(entry.getValue());
        }
    }

    // active list Etudiants Pane
    ButtonClickHandler<Etudiant> editEtudiantClickHandler = rowData -> {
        Stage currentStage = (Stage) mainController.getScene().getWindow();

        try {
            // Load the FXML file for your modal form
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../../../resources/interfaces/ModifierEtudiantScene.fxml"));
            Parent root = loader.load();

            ModifierEtudiantSceneController controller = loader.getController();
            controller.initialize(currentStage, rowData.getId());
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

    ButtonClickHandler<Etudiant> deleteEtudiantClickHandler = rowData -> {
        Stage currentStage = (Stage) mainController.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the student with ID " + rowData.getId() + "?");

        // Show and wait for the user's response
        alert.initOwner(currentStage);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, perform delete action
                EtudiantRepository.deleteEtudiantById(rowData.getId());
                ClasseRepository.updateClasseEffectif(mainController.getActiveClasse().getId(),
                        mainController.getActiveClasse().getEffectif() - 1);
                PushAlert.showAlert("Succès",
                        "L'élève a été supprimé avec succès",
                        AlertType.INFORMATION,
                        currentStage);
                mainController.fillListEtudiantsTableView("");
            }
        });
    };

    public void fillListEtudiantsTableView(int activeClasseId, String searchKey,
            TableView<Etudiant> listEtudiantsTableView,
            TableColumn<Etudiant, Etudiant> listEtudiantsEtudiantColumn,
            TableColumn<Etudiant, Etudiant> listEtudiantsContactColumn,
            TableColumn<Etudiant, Etudiant> listEtudiantsContactParentsColumn,
            TableColumn<Etudiant, String> listEtudiantsDateNaissanceColumn,
            TableColumn<Etudiant, String> listEtudiantsDeleteColumn,
            TableColumn<Etudiant, String> listEtudiantsEditColumn,
            TableColumn<Etudiant, String> listEtudiantsSexeColumn) {

        // get list etudianst from database by selected classe
        ObservableList<Etudiant> data = FXCollections.observableArrayList();
        if (searchKey.length() == 0 || searchKey == null || searchKey.isEmpty() || searchKey.equals("")) {
            data.addAll(ClasseService.getEtudiantsByClasseId(activeClasseId));
        } else {
            data.addAll(ClasseService.getEtudiantsByClasseId_search(activeClasseId, searchKey));
        }
        // set Etudiant's basic infos (photo + fullname + cne) cell value and cell
        // template
        listEtudiantsEtudiantColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        listEtudiantsEtudiantColumn.setCellFactory(new EtudiantNomPhotoCell());

        // set Etudiant's dateNaissance cell value
        listEtudiantsDateNaissanceColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().getDateNaissance().toString()));

        // set Etudiant's sexe cell value
        listEtudiantsSexeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().getSexe()));

        // set Etudiant's contact (email + phone) cell value and cell template
        listEtudiantsContactColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        listEtudiantsContactColumn.setCellFactory(new EtudiantContactCell());

        // set Etudiant's prents contact (email + phone) cell value and cell template
        listEtudiantsContactParentsColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        // set Edit etudiant button template and clickhandler
        listEtudiantsContactParentsColumn.setCellFactory(new EtudiantContactParentsCell());

        listEtudiantsEditColumn
                .setCellFactory(new CustomEditEtudiantButton(editEtudiantClickHandler));
        listEtudiantsDeleteColumn
                .setCellFactory(new CustomDeleteEtudiantButton(deleteEtudiantClickHandler));

        // push data to the tablview
        listEtudiantsTableView.setItems(data);
    }

    public void ajouterEtudiant() {
        try {
            // Load the FXML file for your modal form
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../../../resources/interfaces/AjouterEtudiantScene.fxml"));
            Parent root = loader.load();

            AjouterEtudiantSceneSceneController controller = loader.getController();
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

    public void fillClassesWithSeances(TableView<Map<String, String>> classesTableView,
            TableColumn<Map<String, String>, String> classesSalleColumn,
            TableColumn<Map<String, String>, String> classesStatusColumn,
            TableColumn<Map<String, String>, String> classesClasseColumn,
            TableColumn<Map<String, String>, String> classesEffectifColumn,
            TableColumn<Map<String, String>, String> classesCoursColumn,
            TableColumn<Map<String, String>, String> classesActionColumn,
            TableColumn<Map<String, String>, Map<String, String>> classesProfesseurColumn, Pane activeClassePane,
            ButtonClickHandler<Map<String, String>> clickHandler) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        data.addAll(ClasseService.getAllClassesWithCurrentSeances());
        // Associate data with columns
        classesSalleColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("salleNom")));
        classesCoursColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("coursNom")));
        classesStatusColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("status")));
        classesStatusColumn.setCellFactory(new CustomStatusCell());
        classesClasseColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("classeNom")));
        // Set custom cell factory for classesClasseColumn
        classesClasseColumn.setCellFactory(new CustomClasseCell());
        classesProfesseurColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        classesProfesseurColumn.setCellFactory(new CustomCellFactory());
        classesEffectifColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("effectif")));
        classesActionColumn.setCellFactory(new CustomClasseCellButton(activeClassePane, clickHandler));

        classesTableView.setItems(data);
    }
}
