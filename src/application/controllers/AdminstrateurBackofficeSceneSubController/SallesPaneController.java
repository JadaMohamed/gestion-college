package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.SQLException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.SallesService;
import application.services.SeanceService;
import application.utilities.CustomSalleCell;
import application.utilities.CustomStatusCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    public void fillSallesWithSeances(TableView<Map<String, String>> sallesTableView,
                                TableColumn<Map<String, String>, String> sallesColumn,
                                TableColumn<Map<String, String>, String> sallesStatusColumn,
                                TableColumn<Map<String, String>, String> sallesClasseColumn,
                                TableColumn<Map<String, String>, String> sallesCapaciteColumn,
                                TableColumn<Map<String, String>, String> sallesCoursColumn,
                                TableColumn<Map<String, String>, String> sallesActionColumn) {
    ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
    data.addAll(SallesService.getAllSallesWithCurrentSeances());

    // handle actions
    // Associate data with columns
    sallesColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
            cellData.getValue().get("nomSalle")));
    sallesColumn.setCellFactory(new CustomSalleCell());
    sallesStatusColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
            cellData.getValue().get("statut")));
    sallesStatusColumn.setCellFactory(new CustomStatusCell());
    sallesClasseColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
            cellData.getValue().get("classe")));

    sallesCapaciteColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
            cellData.getValue().get("capacite")));
    sallesCoursColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
            cellData.getValue().get("cours")));

    sallesTableView.setItems(data);
}
}
