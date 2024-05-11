package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.SQLException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.model.CategorieSalle;
import application.services.SallesService;
import application.services.SeanceService;
import application.utilities.CustomSalleCell;
import application.utilities.CustomStatusCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.util.function.Predicate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SallesPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    private TextField searchField;
    private ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
    private TableView<Map<String, String>> sallesTableView;
    public SallesPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }
    // Créer et configurer le ComboBox pour la sélection de la classe de salle
        ComboBox<CategorieSalle> categoryComboBox = new ComboBox<>();
    public void initialize() {
        updateSallesOccupesLabels();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appelez une méthode de recherche à chaque fois que le texte change
            search(newValue);});
        categoryComboBox.setPromptText("Select Category");

        // Remplir le ComboBox avec les catégories de salle disponibles
        categoryComboBox.getItems().addAll(
        new CategorieSalle(1, "LABORATOIRE"),
        new CategorieSalle(2, "CLASSE"),
        new CategorieSalle(3, "SALLE_DE_SPORTS")
);
    
    // Ajouter un gestionnaire d'événements pour le ComboBox
    categoryComboBox.setOnAction(event -> {
        CategorieSalle selectedCategory = categoryComboBox.getValue(); // Obtenir la catégorie de salle sélectionnée
    
        // Filtrer les données des salles en fonction de la catégorie de salle sélectionnée
        Predicate<Map<String, String>> sallePredicate = salle -> {
            String salleCategory = salle.get("categorieSalle"); // Remplacez "categorieSalle" par le nom de la colonne correspondant à la catégorie de salle dans vos données
            return salleCategory.equals(selectedCategory.toString()); // Vérifiez si la catégorie de salle de la salle correspond à la catégorie sélectionnée
        };
    
        // Appliquer le prédicat de filtre et afficher les résultats dans la table des salles
        ObservableList<Map<String, String>> filteredData = data.filtered(sallePredicate);
        sallesTableView.setItems(filteredData);
    });
    }
    private void search(String keyword) {
        // Vérifiez si le champ de recherche est vide
        if (keyword.isEmpty()) {
            // Si le champ de recherche est vide
            sallesTableView.setItems(data);
            return;
        }
    
        // Créez un prédicat pour filtrer les données
        Predicate<Map<String, String>> predicate = salle -> {
            String nomSalle = salle.get("nomSalle").toLowerCase(); 
            return nomSalle.contains(keyword.toLowerCase()); // Vérifiez si le nom de la salle contient le mot-clé de recherche
        };
    
        // Appliquez le prédicat pour filtrer les données
        ObservableList<Map<String, String>> filteredData = data.filtered(predicate);
        // Mettez à jour le TableView avec les données filtrées
        sallesTableView.setItems(filteredData);
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
