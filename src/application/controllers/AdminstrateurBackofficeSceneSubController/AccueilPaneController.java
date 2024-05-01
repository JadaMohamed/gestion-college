package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.repositories.SeanceRepository;
import application.services.SeanceService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccueilPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    
    public AccueilPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
        // this.seanceRepository = new SeanceRepository();
        // this.sallesRepository = new SallesRepository();
        // this.categorieSalleRepository = new CategorieSalleRepository();
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

    public void updateEffectifEnCours(){
        try{
            int effectifEnCours = SeanceService.getEffectifEnCours();
        mainController.setEffectifEnCoursText(String.valueOf(effectifEnCours));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

