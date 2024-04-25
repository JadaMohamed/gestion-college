package application.controllers;

import java.util.Vector;

import application.model.NiveauClasse;
import application.services.NiveauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TestNiveauGridController {

    @FXML
    private TableView<NiveauClasse> niveauTableView;

    @FXML
    private TableColumn<NiveauClasse, Integer> idNiveauColumn;

    @FXML
    private TableColumn<NiveauClasse, String> nomNiveauColumn;

    public void initialize() {
        // Call the service to retrieve data
        Vector<NiveauClasse> niveauData = NiveauService.getAllNiveaus();

        // Create a list with a single Niveau object
        ObservableList<NiveauClasse> niveauList = FXCollections.observableArrayList();
        niveauList.addAll(niveauData);

        // Associate data with columns
        idNiveauColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        nomNiveauColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getNom()));

        niveauTableView.setItems(niveauList);
    }
}
