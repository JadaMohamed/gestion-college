package application.controllers.BackofficeSubControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccueilPaneController {

    @FXML
    public TableView<Object> coursEncoursTableView;
    @FXML
    private TableColumn<Object, String> coursEncoursAction;

    @FXML
    private TableColumn<Object, String> coursEncoursClasse;

    @FXML
    private TableColumn<Object, String> coursEncoursCours;

    @FXML
    private TableColumn<Object, String> coursEncoursHoraires;

    @FXML
    private TableColumn<Object, String> coursEncoursProfesseur;

    @FXML
    private TableColumn<Object, String> coursEncoursSalle;
}
