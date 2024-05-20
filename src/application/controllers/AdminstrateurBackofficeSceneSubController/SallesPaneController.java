package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;
import application.controllers.AdminstrateurBackofficeSceneController;
import application.model.CategorieSalle;
import application.model.MaterielSalle;
import application.services.SallesService;
import application.services.SeanceService;
import application.utilities.CustomSalleCell;
import application.utilities.CustomStatusCellSalle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SallesPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    // private TextField searchFieldSalle;
    private ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

    // private TableView<Map<String, String>> sallesTableView;
    public SallesPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    // Créer et configurer le ComboBox pour la sélection de la classe de salle
    ComboBox<CategorieSalle> categoryComboBox = new ComboBox<>();

    public void initialize() {
        updateSallesOccupesLabels();

    }

    public void search(String keyword, TableView<Map<String, String>> sallesTableView) {
        // Vérifiez si le champ de recherche est vide
        if (keyword.isEmpty()) {
            // Si le champ de recherche est vide
            sallesTableView.setItems(data);
            return;
        }

        // TableView<Map<String, String>> tableView = sallesTableView; // assuming sallesTableView is accessible
        if (sallesTableView == null) {
            System.out.println("Error: TableView sallesTableView is not initialized.");
            return;
        }
        ObservableList<Map<String, String>> items = sallesTableView.getItems();
        ObservableList<Map<String, String>> filteredItems = FXCollections.observableArrayList();

        String camelCaseKeyword = toCamelCase(keyword);

        for (Map<String, String> item : items) {
            String nomSalle = toCamelCase(item.get("nomSalle"));
            if (nomSalle != null && nomSalle.contains(camelCaseKeyword)) {
                filteredItems.add(item);
            }
        }

        sallesTableView.setItems(filteredItems);
        return;
    }

    private String toCamelCase(String str) {
        String[] parts = str.split("\\s+");
        StringBuilder camelCaseString = new StringBuilder();
        for (String part : parts) {
            camelCaseString.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }

    public void setActiveSalleInformation(Map<String, String> rowData, Text activeSalleLabel,
            Label activeSalleNomLabel, Label activeSalleCapacite, Label activeSalleDisponibilite,
            Label activeSalleCoccupe, ImageView activeSalleStatutIcon, Label materielSalleLabel,
            AnchorPane materielSalleAnchorPane) {

        activeSalleLabel.setText(rowData.get("nomSalle"));
        activeSalleNomLabel.setText(rowData.get("nomSalle"));
        activeSalleCapacite.setText(rowData.get("capacite"));
        // Calcul du nombre d'heures disponibles et occupées par semaine
        int idSalle = Integer.parseInt(rowData.get("idSalle"));
        int availableHoursPerWeek = SallesService.getAvailableHoursPerWeek(idSalle);
        int occupiedHoursPerWeek = SallesService.getOccupiedHoursPerWeek(idSalle);

        // Affichage du statut de disponibilité de la salle en fonction des heures
        activeSalleDisponibilite.setText(availableHoursPerWeek > 0 ? "" + availableHoursPerWeek + " h/semaine" : "Non");
        activeSalleCoccupe.setText(occupiedHoursPerWeek > 0 ? "" + occupiedHoursPerWeek + " h/semaine" : "Non");

        // Effacer le contenu précédent de l'AnchorPane
        materielSalleAnchorPane.getChildren().clear();

        // Récupérer les matériels de la salle
        int salleId = Integer.parseInt(rowData.get("idSalle"));
        Vector<MaterielSalle> materiels = SallesService.getMaterialBySalleId(salleId);

        // Créer un VBox pour contenir les labels des matériaux
        VBox vbox = new VBox();
        vbox.setSpacing(5.0);

        // Créer et configurer les Labels pour chaque matériel
        for (MaterielSalle materiel : materiels) {
            // Créer un Label avec le nom et la quantité du matériel
            Label label = new Label("• " + materiel.getQuantite() + " " + materiel.getNom());

            // Ajouter le Label au VBox
            vbox.getChildren().add(label);
        }

        // Ajouter le VBox à l'AnchorPane
        AnchorPane.setTopAnchor(vbox, 10.0);
        AnchorPane.setLeftAnchor(vbox, 10.0);
        materielSalleAnchorPane.getChildren().add(vbox);

        // Mettre à jour l'icône du statut en fonction du statut de la salle
        if (Integer.parseInt(rowData.get("statut")) == 1) {
            activeSalleStatutIcon.setImage(
                    new Image(getClass().getResourceAsStream("/resources/images/icons/Badge_Occupée.png")));
        } else {
            activeSalleStatutIcon
                    .setImage(new Image(getClass().getResourceAsStream("/resources/images/icons/Badge_Disponible.png")));
        }

        StringBuilder builder = new StringBuilder();

        // Itérer sur les entrées de la carte rowData
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            // Concaténer la clé et la valeur avec un séparateur et ajouter au StringBuilder
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // materielSalleLabel.setText(builder.toString());
    }

    public Vector<MaterielSalle> showActiveSalleMaterials(int salleId) {
        // Appel de la méthode du service pour récupérer les matériaux de la salle
        Vector<MaterielSalle> materials = SallesService.getMaterialBySalleId(salleId);
        return materials;
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
        sallesStatusColumn.setCellFactory(new CustomStatusCellSalle());
        sallesClasseColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("classe")));

        sallesCapaciteColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("capacite")));
        sallesCoursColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("cours")));

        sallesTableView.setItems(data);
    }
}
