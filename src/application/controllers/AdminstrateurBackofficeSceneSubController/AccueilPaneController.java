package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import application.repositories.SeanceRepository;
import application.services.ClasseService;
import application.services.SeanceService;
import application.utilities.CustomCellFactory;
import application.utilities.CustomClasseCell;
import application.utilities.CustomStatusCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class AccueilPaneController {

    public AccueilPaneController() {

    }

    public void updateSallesDisponibles(Text SallesDisponibles) {
        int nombreSallesDisponibles = SeanceService.getNombreSallesDisponibles();
        SallesDisponibles.setText(String.valueOf(nombreSallesDisponibles));
    }

    public void updateCoursEnCours(Text coursEnCours) {
        try {
            ResultSet resultSet = SeanceRepository.getNombreSeanceEnCours();
            if (resultSet.next()) {
                int nombreCoursEnCours = resultSet.getInt("seancesencours");
                coursEnCours.setText(String.valueOf(nombreCoursEnCours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSallesOccupesLabels(Label nombreLaboratoiresDisponibles, Label nombreSalleCoursDisponibles,
            Label nombreSalleDeSportDisponibles) {
        try {
            int[] nombreSallesOccupes = SeanceService.getNombreSallesOccupes();
            nombreLaboratoiresDisponibles.setText(String.valueOf(2 - nombreSallesOccupes[0]));
            nombreSalleCoursDisponibles.setText(String.valueOf(17 - nombreSallesOccupes[1]));
            nombreSalleDeSportDisponibles.setText(String.valueOf(3 - nombreSallesOccupes[2]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoursEnCoursLabels(Label nombreClasse3EnCours, Label nombreClasse4EnCours,
            Label nombreClasse5EnCours, Label nombreClasse6EnCours) {
        int[] nombreCoursParNiveau = SeanceService.getNombreCoursParNiveau();
        nombreClasse3EnCours.setText(String.valueOf(nombreCoursParNiveau[0]));
        nombreClasse4EnCours.setText(String.valueOf(nombreCoursParNiveau[1]));
        nombreClasse5EnCours.setText(String.valueOf(nombreCoursParNiveau[2]));
        nombreClasse6EnCours.setText(String.valueOf(nombreCoursParNiveau[3]));
    }

    public void updateEffectifEnCours(Text effectifEnCours) {
        try {
            int effectif = SeanceService.getEffectifEnCours();
            effectifEnCours.setText(String.valueOf(effectif));
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
