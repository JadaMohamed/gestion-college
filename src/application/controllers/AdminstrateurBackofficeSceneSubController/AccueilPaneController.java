package application.controllers.AdminstrateurBackofficeSceneSubController;


import java.util.ArrayList;
import java.util.List;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.model.Seance;
import application.repositories.CategorieSalleRepository;
import application.repositories.SallesRepository;
import application.repositories.SeanceRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccueilPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    private final SeanceRepository seanceRepository;
    private final SallesRepository sallesRepository;
    private final CategorieSalleRepository categorieSalleRepository;

    private int nombreLaboratoiresDisponibles;
    private int nombreSalleCoursDisponibles;
    private int nombreSalleDeSportDisponibles;

    public AccueilPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
        this.seanceRepository = new SeanceRepository();
        this.sallesRepository = new SallesRepository();
        this.categorieSalleRepository = new CategorieSalleRepository();
    }

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

    public void initialize() {

    }
    
}

