package application.controllers;

import java.util.Map;
import application.model.Etudiant;
import application.services.AbsenceService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class ConsulterAbsecesEtudiantSceneController {

        private Etudiant activeEtudiant = new Etudiant();
        @FXML
        private TableView<Map<String, String>> absencesTableView;

        @FXML
        private Text cneEtudiantText, nomEtudiantText;

        @FXML
        private TableColumn<Map<String, String>, String> jourColumn, motifColumn, semaineColumn, statutColumn;

        @FXML
        public void initialize(Etudiant e) {
                activeEtudiant = e;

                cneEtudiantText.setText(activeEtudiant.getCne());
                nomEtudiantText.setText(activeEtudiant.getNom() + " " + activeEtudiant.getPrenom());

                ObservableList<Map<String, String>> data = FXCollections
                                .observableArrayList();

                data.addAll(AbsenceService.getAbsencePourEtudiant(activeEtudiant.getId()));

                jourColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                                cellData.getValue().get("jour")));
                motifColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                                cellData.getValue().get("motif")));
                semaineColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                                cellData.getValue().get("numSemaine")));
                statutColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                                cellData.getValue().get("estExcuse").equals("1") ? "Oui" : "Non"));
                absencesTableView.setItems(data);

        }

}
