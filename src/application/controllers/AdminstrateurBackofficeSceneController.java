package application.controllers;

import java.util.Map;

import application.controllers.AdminstrateurBackofficeSceneSubController.AccueilPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.AffectationPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ClassesPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ParametresPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.SidebarController;
import application.model.Classe;
import application.model.Enseignant;
import application.model.Horaires;
import application.model.Salle;
import application.model.TypeCours;
import application.model.enums.JoursSemaine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AdminstrateurBackofficeSceneController {

    @FXML
    private Button accueilButton, classesButton, deconnecterButton, sallesButton, parametresButton, affectationButton,
            annulerButtonParametres;

    @FXML
    private Pane affectationPane, accueilPane, classesPane, sallesPane, parametresPane, rootPane;

    @FXML
    private Button sauvegarderButtonInfosParametres, sauvegarderButtonSecurityParametres, handleAffecterButton,
            handleAnnulerAffectation;

    @FXML
    private ComboBox<Salle> sallesComboAffectation;

    @FXML
    private ComboBox<Horaires> horairesComboAffectation;
    @FXML
    private ComboBox<JoursSemaine> joursComboAffectation;
    @FXML
    private ComboBox<Enseignant> enseignantComboAffectation;

    @FXML
    private ComboBox<TypeCours> typeCoursComboAffectation;

    @FXML
    private TableView<Map<String, String>> coursEncoursTableView, classesTableView;
    @FXML
    private TableColumn<Map<String, String>, String> coursEncoursSalleColumn, coursEncoursHorairesColumn,
            coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn, classesClasseColumn,
            classesEffectifColumn, classesStatusColumn, classesSalleColumn, classesActionColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> coursEncoursProfesseurColumn, classesProfesseurColumn;

    @FXML
    private ComboBox<Classe> classesComboAffectation;

    @FXML
    private TextField nomFieldParametres, prenomFieldParametres, telephoneFieldParametres, emailFieldParametres,
            oldPasswordParametres,
            newPasswordParametres, nomCoursFieldAffectation;

    @FXML
    private DatePicker dateBirthDatePickerParametres;

    @FXML
    private Label errorPasswordUpdate;

    private int currentAdminId;

    @FXML
    private Text SallesDisponibles, coursEnCours, effectifEnCours;

    @FXML
    private Label nombreLaboratoiresDisponibles, nombreSalleCoursDisponibles, nombreSalleDeSportDisponibles,
            nombreClasse3EnCours, nombreClasse4EnCours, nombreClasse5EnCours, nombreClasse6EnCours,
            nombreAbscencesNonExcuses,
            nombre3emeEnCours, nombre4emeEnCours, nombre5emeEnCours, nombre6emeEnCours;

    private SidebarController sidebarController;
    private ParametresPaneController parametresPaneController;
    private AffectationPaneController affectationPaneController;
    private AccueilPaneController accueilPaneController;
    private ClassesPaneController classesPaneController;
    // private SalllesPaneController SalllesPaneController;

    public void initialize(int adminId) {
        this.currentAdminId = adminId;
        sidebarController = new SidebarController(this);
        parametresPaneController = new ParametresPaneController(this);
        affectationPaneController = new AffectationPaneController(this);
        accueilPaneController = new AccueilPaneController(this);
        classesPaneController = new ClassesPaneController(this);
        // sallesPaneController = new SallesPaneControllers(this);

        sidebarController.initialize();
        parametresPaneController.initialize();
        parametresPaneController.loadAdminData();
        affectationPaneController.initialize();
        accueilPaneController.initialize();
        classesPaneController.initialize();
        accueilPaneController.fillCurrentSeances(coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn,
                coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
        accueilPaneController.fillClassesWithSeances(classesTableView, classesSalleColumn, classesStatusColumn,
                classesClasseColumn, classesEffectifColumn,
                classesProfesseurColumn);
    }

    public void setSallesDisponiblesText(String text) {
        SallesDisponibles.setText(text);
    }

    public void setCoursEnCoursText(String text) {
        coursEnCours.setText(text);
    }

    public void setNombreLaboratoiresOccupesText(String text) {
        nombreLaboratoiresDisponibles.setText(text);
    }

    public void setNombreSalleCoursOccupesText(String text) {
        nombreSalleCoursDisponibles.setText(text);
    }

    public void setNombreSalleDeSportOccupesText(String text) {
        nombreSalleDeSportDisponibles.setText(text);
    }

    public void setCoursEnCours6emeText(String text) {
        nombreClasse6EnCours.setText(text);
    }

    public void setCoursEnCours5emeText(String text) {
        nombreClasse5EnCours.setText(text);
    }

    public void setCoursEnCours4emeText(String text) {
        nombreClasse4EnCours.setText(text);
    }

    public void setCoursEnCours3emeText(String text) {
        nombreClasse3EnCours.setText(text);
    }

    public void setEffectifEnCoursText(String text) {
        effectifEnCours.setText(text);
    }

    public void setNombre3emeEnCoursText(String text) {
        nombre3emeEnCours.setText(text);
    }

    public void setNombre4emeEnCoursText(String text) {
        nombre4emeEnCours.setText(text);
    }

    public void setNombre5emeEnCoursText(String text) {
        nombre5emeEnCours.setText(text);
    }

    public void setNombre6emeEnCoursText(String text) {
        nombre6emeEnCours.setText(text);
    }

    @FXML
    public void handleSideBarButtonAction(ActionEvent event) {
        sidebarController.handleSideBarButtonAction(event);
    }

    @FXML
    public void handleCancelParametresChanges(ActionEvent event) {
        parametresPaneController.annulerLesModifications();
    }

    @FXML
    public void handleAnnulerAffectation(ActionEvent event) {
        affectationPaneController.annulerAffectation();
    }

    @FXML
    public void handleAffecterButton(ActionEvent event) {
        affectationPaneController.affecterUneSeance();
    }

    @FXML
    public void handleSauvegarderButtonSecurityParameters(ActionEvent event) {
        parametresPaneController.handleSauvegarderButtonSecurityParameters();
    }

    @FXML
    public void handleSauvegarderButtonInfosParameters(ActionEvent event) {
        parametresPaneController.handleSauvegarderButtonInfosParameters();
    }

    // Methods to access UI elements SubControllers

    public int getCurrentAdminId() {
        return currentAdminId;
    }

    public Button getAccueilButton() {
        return accueilButton;
    }

    public Button getSallesButton() {
        return sallesButton;
    }

    public Button getClassesButton() {
        return classesButton;
    }

    public Button getParametresButton() {
        return parametresButton;
    }

    public Button getDeconnecterButton() {
        return deconnecterButton;
    }

    public Pane getAccueilPane() {
        return accueilPane;
    }

    public Pane getSallesPane() {
        return sallesPane;
    }

    public Pane getAffectationPane() {
        return affectationPane;
    }

    public Pane getClassesPane() {
        return classesPane;
    }

    public Pane getParametresPane() {
        return parametresPane;
    }

    public Button getAnnulerButton() {
        return annulerButtonParametres;
    }

    public Button getAffectationButton() {
        return affectationButton;
    }

    public TextField getNomFieldParametres() {
        return nomFieldParametres;
    }

    public TextField getPreomFieldParametres() {
        return prenomFieldParametres;
    }

    public TextField getEmailFieldParametres() {
        return emailFieldParametres;
    }

    public TextField getTelephoneFieldParametres() {
        return telephoneFieldParametres;
    }

    public DatePicker getDateBirthDatePickerParametres() {
        return dateBirthDatePickerParametres;
    }

    public TextField getOldPasswordParametres() {
        return oldPasswordParametres;
    }

    public TextField getNomCoursFieldAffectation() {
        return nomCoursFieldAffectation;
    }

    public TextField getNewPasswordParametres() {
        return newPasswordParametres;
    }

    public ComboBox<Salle> getSallesComboAffectation() {
        return sallesComboAffectation;
    }

    public ComboBox<JoursSemaine> getJoursComboAffectation() {
        return joursComboAffectation;
    }

    public ComboBox<Classe> getClassesComboAffectation() {
        return classesComboAffectation;
    }

    public ComboBox<Horaires> getHorairesComboAffectation() {
        return horairesComboAffectation;
    }

    public ComboBox<TypeCours> getTypeCoursComboAffectation() {
        return typeCoursComboAffectation;
    }

    public Scene getScene() {
        return rootPane.getScene();
    }

    public ComboBox<Enseignant> getEnseignantComboAffectation() {
        return enseignantComboAffectation;
    }

}