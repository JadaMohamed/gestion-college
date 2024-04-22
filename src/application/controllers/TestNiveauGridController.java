package application.controllers;

import java.util.Vector;

import application.model.Niveau;
import application.services.NiveauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TestNiveauGridController {

    @FXML
    private TableView<Niveau> niveauTableView;

    @FXML
    private TableColumn<Niveau, Integer> idNiveauColumn;

    @FXML
    private TableColumn<Niveau, String> nomNiveauColumn;

    public void initialize() {
        // Call the service to retrieve data
        Vector<Niveau> niveauData = NiveauService.getAllNiveaus();

        // Create a list with a single Niveau object
        ObservableList<Niveau> niveauList = FXCollections.observableArrayList();
        niveauList.addAll(niveauData);

        // Associate data with columns
        idNiveauColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getIdNiveau()));
        nomNiveauColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNomNiveau()));

        niveauTableView.setItems(niveauList);
    }
}
