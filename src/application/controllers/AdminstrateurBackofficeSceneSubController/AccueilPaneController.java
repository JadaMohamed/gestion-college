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

    public void calculerSallesDisponibles() {
        // Récupérer les séances pour le jour et l'heure actuels
        List<Seance> seances = seanceRepository.getSeancesActuelles();
    
        // Récupérer le nombre total de salles pour chaque catégorie
        int totalLaboratoires = sallesRepository.getTotalSallesByCategory("Laboratoire");
        int totalSalleCours = sallesRepository.getTotalSallesByCategory("Cours");
        int totalSalleSports = sallesRepository.getTotalSallesByCategory("Sports");
    
        // Filtrer les séances pour récupérer les idSalle
        List<Integer> idSalles = new ArrayList<>();
        for (Seance seance : seances) {
            idSalles.add(seance.getIdSalle());
        }
    
        // Si des séances sont trouvées
        if (!idSalles.isEmpty()) {
            // Récupérer les catégories de salle pour chaque idSalle
            List<Integer> idCategories = sallesRepository.getIdCategoriesByIdSalles(idSalles);
    
            // Compter le nombre de salles occupées pour chaque catégorie
            int laboratoiresOccupes = 0;
            int salleCoursOccupes = 0;
            int salleSportsOccupes = 0;
            for (Integer idCategorie : idCategories) {
                switch (idCategorie) {
                    case 1: // Laboratoire
                        laboratoiresOccupes++;
                        break;
                    case 2: // Salle de cours
                        salleCoursOccupes++;
                        break;
                    case 3: // Salle de sports
                        salleSportsOccupes++;
                        break;
                }
            }
    
            // Mettre à jour le nombre de salles disponibles pour chaque catégorie
            nombreLaboratoiresDisponibles = totalLaboratoires - laboratoiresOccupes;
            nombreSalleCoursDisponibles = totalSalleCours - salleCoursOccupes;
            nombreSalleDeSportDisponibles = totalSalleSports - salleSportsOccupes;
        } else {
            // Si aucune séance n'est trouvée, toutes les salles sont disponibles
            nombreLaboratoiresDisponibles = totalLaboratoires;
            nombreSalleCoursDisponibles = totalSalleCours;
            nombreSalleDeSportDisponibles = totalSalleSports;
        }
    
        // Mettre à jour les vues correspondantes dans le mainController
        mainController.updateSallesDisponibles(nombreLaboratoiresDisponibles, nombreSalleCoursDisponibles, nombreSalleDeSportDisponibles);
    }
    
    
    public int getNombreLaboratoiresDisponibles() {
        return nombreLaboratoiresDisponibles;
    }

    public int getNombreSalleCoursDisponibles() {
        return nombreSalleCoursDisponibles;
    }

    public int getNombreSalleDeSportDisponibles() {
        return nombreSalleDeSportDisponibles;
    }
    
}

