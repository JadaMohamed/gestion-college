package application.controllers.AdminstrateurBackofficeSceneSubController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.repositories.SeanceRepository;
import application.services.AdministrateurService;
import application.services.SeanceService;
import application.utilities.CustomCellFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AccueilPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    public AccueilPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    }

    public void initialize(){

    }

    public void updatePhotoAdmin(ImageView photoAdmin) {
        int adminId = mainController.getLoggedInAdminId();
        String adminUrlPhoto = AdministrateurService.getUrlPhotoAdminById(adminId);
        if (adminUrlPhoto != null && !adminUrlPhoto.isEmpty()) {
            String imageUrl = getClass().getResource(adminUrlPhoto).toExternalForm();
            Image image = new Image(imageUrl);
            photoAdmin.setImage(image);
        } else {
            String defaultImageUrl = getClass().getResource("../../../images/profiles/default.png").toExternalForm();
            Image image = new Image(defaultImageUrl);
            photoAdmin.setImage(image);
        }
    }
    

    public void updateNameAdmin(Text nameAdminText){
        int adminId = mainController.getLoggedInAdminId();
        String nameAdmin = AdministrateurService.getNameAdminById(adminId);
        nameAdminText.setText(nameAdmin);
    }

    public void updateSallesDisponibles(Text SallesDisponibles) {
        int nombreSallesDisponibles = SeanceService.getNombreSallesDisponibles();
        SallesDisponibles.setText(String.valueOf(nombreSallesDisponibles));
    }

    public void updateCoursEnCours(Text coursEnCours) {
        try {
            ResultSet resultSet = SeanceRepository.getNombreSeanceEnCours();
            if (resultSet.next()) {
                int nombreCoursEnCours = resultSet.getInt("seancesencours");
                coursEnCours.setText(String.valueOf(nombreCoursEnCours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSallesOccupesLabels(Label nombreLaboratoiresDisponibles, Label nombreSalleCoursDisponibles,
            Label nombreSalleDeSportDisponibles) {
        try {
            int[] nombreSallesOccupes = SeanceService.getNombreSallesOccupes();
            nombreLaboratoiresDisponibles.setText(String.valueOf(2 - nombreSallesOccupes[0]));
            nombreSalleCoursDisponibles.setText(String.valueOf(17 - nombreSallesOccupes[1]));
            nombreSalleDeSportDisponibles.setText(String.valueOf(3 - nombreSallesOccupes[2]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoursEnCoursLabels(Label nombreClasse3EnCours, Label nombreClasse4EnCours,
            Label nombreClasse5EnCours, Label nombreClasse6EnCours) {
        int[] nombreCoursParNiveau = SeanceService.getNombreCoursParNiveau();
        nombreClasse3EnCours.setText(String.valueOf(nombreCoursParNiveau[3]));
        nombreClasse4EnCours.setText(String.valueOf(nombreCoursParNiveau[2]));
        nombreClasse5EnCours.setText(String.valueOf(nombreCoursParNiveau[1]));
        nombreClasse6EnCours.setText(String.valueOf(nombreCoursParNiveau[0]));
    }

    public void updateEffectifEnCours(Text effectifEnCours) {
        try {
            int effectif = SeanceService.getEffectifEnCours();
            effectifEnCours.setText(String.valueOf(effectif));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillCurrentSeances(TableView<Map<String, String>> coursEncoursTableView,
            TableColumn<Map<String, String>, String> coursEncoursSalleColumn,
            TableColumn<Map<String, String>, String> coursEncoursHorairesColumn,
            TableColumn<Map<String, String>, String> coursEncoursClasseColumn,
            TableColumn<Map<String, String>, String> coursEncoursCourNomColumn,
            TableColumn<Map<String, String>, String> coursEncoursEffectifColumn,
            TableColumn<Map<String, String>, Map<String, String>> coursEncoursProfesseurColumn) {
        ObservableList<Map<String, String>> coursEncoursData = FXCollections.observableArrayList();
        coursEncoursData.addAll(SeanceService.getSeancesEnCoursBis());

        // Associate data with columns
        coursEncoursSalleColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("nomSalle")));
        coursEncoursHorairesColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("horaire")));
        coursEncoursClasseColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("classe")));
        coursEncoursProfesseurColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        coursEncoursProfesseurColumn.setCellFactory(new CustomCellFactory());
        coursEncoursCourNomColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("nomCours")));
        coursEncoursEffectifColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().get("effectif")));

        coursEncoursTableView.setItems(coursEncoursData);
    }

    public void fillListCoursEncoursTableView(String searchKey,
                                          TableView<Map<String, String>> coursEncoursTableView,
                                          TableColumn<Map<String, String>, String> coursEncoursSalleColumn,
                                          TableColumn<Map<String, String>, String> coursEncoursHorairesColumn,
                                          TableColumn<Map<String, String>, String> coursEncoursClasseColumn,
                                          TableColumn<Map<String, String>, String> coursEncoursCourNomColumn,
                                          TableColumn<Map<String, String>, String> coursEncoursEffectifColumn,
                                          TableColumn<Map<String, String>, Map<String, String>> coursEncoursProfesseurColumn) {

    // Get data from database
    ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
    if (searchKey == null || searchKey.isEmpty()) {
        data.addAll(SeanceService.getSeancesEnCoursBis());
    } else {
        data.addAll(SeanceService.getSeances_search(searchKey));
    }

    // Set cell value factories
    coursEncoursHorairesColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("horaire")));
    coursEncoursClasseColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("classe")));
    coursEncoursCourNomColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("nomCours")));
    coursEncoursSalleColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("nomSalle")));
    coursEncoursEffectifColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().get("effectif")));
    coursEncoursProfesseurColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
    coursEncoursProfesseurColumn.setCellFactory(new CustomCellFactory());

    // Set data to TableView
    coursEncoursTableView.setItems(data);
    }
}
