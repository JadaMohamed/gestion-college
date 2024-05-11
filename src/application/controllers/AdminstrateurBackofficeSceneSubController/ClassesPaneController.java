package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.ClasseService;
import application.services.SeanceService;
import application.utilities.ET0810;
import application.utilities.ET1012;
import application.utilities.ET1416;
import application.utilities.ET1618;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
