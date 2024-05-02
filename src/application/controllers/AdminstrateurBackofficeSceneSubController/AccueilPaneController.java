package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.repositories.SeanceRepository;
import application.services.ClasseService;
import application.services.SeanceService;
import application.utilities.CustomCellFactory;
import application.utilities.CustomClasseCell;
import application.utilities.CustomStatusCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccueilPaneController {
    private AdminstrateurBackofficeSceneController mainController;

    public AccueilPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        updateSallesDisponibles();
        updateCoursEnCours();
        updateSallesOccupesLabels();
        updateCoursEnCoursLabels();
        updateEffectifEnCours();
    }

    public void updateSallesDisponibles() {
        int nombreSallesDisponibles = SeanceService.getNombreSallesDisponibles();
        mainController.setSallesDisponiblesText(String.valueOf(nombreSallesDisponibles));
    }

    public void updateCoursEnCours() {
        try {
            ResultSet resultSet = SeanceRepository.getNombreSeanceEnCours();
            if (resultSet.next()) {
                int nombreCoursEnCours = resultSet.getInt("seancesencours");
                mainController.setCoursEnCoursText(String.valueOf(nombreCoursEnCours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSallesOccupesLabels() {
        try {
            int[] nombreSallesOccupes = SeanceService.getNombreSallesOccupes();
            mainController.setNombreLaboratoiresOccupesText(String.valueOf(2 - nombreSallesOccupes[0]));
            mainController.setNombreSalleCoursOccupesText(String.valueOf(17 - nombreSallesOccupes[1]));
            mainController.setNombreSalleDeSportOccupesText(String.valueOf(3 - nombreSallesOccupes[2]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoursEnCoursLabels() {
        int[] nombreCoursParNiveau = SeanceService.getNombreCoursParNiveau();
        mainController.setCoursEnCours6emeText(String.valueOf(nombreCoursParNiveau[0]));
        mainController.setCoursEnCours5emeText(String.valueOf(nombreCoursParNiveau[1]));
        mainController.setCoursEnCours4emeText(String.valueOf(nombreCoursParNiveau[2]));
        mainController.setCoursEnCours3emeText(String.valueOf(nombreCoursParNiveau[3]));
    }

    public void updateEffectifEnCours() {
        try {
            int effectifEnCours = SeanceService.getEffectifEnCours();
            mainController.setEffectifEnCoursText(String.valueOf(effectifEnCours));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillCurrentSeances(TableView<Map<String, String>> coursEncoursTableView,
            TableColumn<Map<String, String>, String> coursEncoursSalleColumn,
            TableColumn<Map<String, String>, String> coursEncoursHorairesColumn,
            TableColumn<Map<String, String>, String> coursEncoursClasseColumn,
            TableColumn<Map<String, String>, String> coursEncoursCourNomColumn,
            TableColumn<Map<String, String>, String> coursEncoursEffectifColumn,
            TableColumn<Map<String, String>, Map<String, String>> coursEncoursProfesseurColumn) {
        ObservableList<Map<String, String>> coursEncoursData = FXCollections.observableArrayList();
        coursEncoursData.addAll(SeanceService.getSeancesEnCours());

        // Associate data with columns
        coursEncoursSalleColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("salleNom")));
        coursEncoursHorairesColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("horaires")));
        coursEncoursClasseColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("classeNom")));
        coursEncoursProfesseurColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        coursEncoursProfesseurColumn.setCellFactory(new CustomCellFactory());
        coursEncoursCourNomColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("coursNom")));
        coursEncoursEffectifColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("effectif")));

        coursEncoursTableView.setItems(coursEncoursData);
    }

    public void fillClassesWithSeances(TableView<Map<String, String>> classesTableView,
            TableColumn<Map<String, String>, String> classesSalleColumn,
            TableColumn<Map<String, String>, String> classesStatusColumn,
            TableColumn<Map<String, String>, String> classesClasseColumn,
            TableColumn<Map<String, String>, String> classesEffectifColumn,
            TableColumn<Map<String, String>, String> classesCoursColumn,
            TableColumn<Map<String, String>, String> classesActionColumn,
            TableColumn<Map<String, String>, Map<String, String>> classesProfesseurColumn) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        data.addAll(ClasseService.getAllClassesWithCurrentSeances());

        // handle actions
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

        classesTableView.setItems(data);
    }
}
