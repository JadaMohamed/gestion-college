package application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.scene.layout.VBox;
import java.util.stream.Collectors;
import application.controllers.AdminstrateurBackofficeSceneSubController.AccueilPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.AffectationPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ClassesPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.EnsignantsPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ParametresPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.SallesPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.SidebarController;
import application.model.CategorieSalle;
import application.model.Classe;
import application.model.Enseignant;
import application.model.Etudiant;
import application.model.Horaires;
import application.model.MaterielSalle;
import application.model.NiveauClasse;
import application.model.Salle;
import application.model.TypeCours;
import application.model.enums.JoursSemaine;
import application.services.ClasseService;
import application.services.SallesService;
import application.services.SeanceService;
import application.utilities.ButtonClickHandler;
import application.utilities.CustomSalleCellButton;
import application.utilities.DateUtil;
import application.utilities.ET0810;
import application.utilities.ET1012;
import application.utilities.ET1416;
import application.utilities.ET1618;
import application.utilities.EtudiantNomPhotoCellMap;
import application.utilities.FormattedLocalDateTime;
import javafx.beans.property.ReadOnlyObjectWrapper;
import application.utilities.AbsenceIsExcusedToggler;
import application.utilities.AbsenceStatusToggler;
import application.services.AbsenceService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AdminstrateurBackofficeSceneController {

    @FXML
    private Pane rootPane;
    @FXML
    private Label errorPasswordUpdate;
    private int loggedInAdminId;
    private Classe activeClasse;
    // private Salle activeSalle;
    //
    //
    //
    //
    //
    //
    // Everything related to accueilpane
    private AccueilPaneController accueilPaneController;
    @FXML
    private Text localDateTimeLabelAccueilPane;
    @FXML
    private Text SallesDisponibles, coursEnCours, effectifEnCours;
    @FXML
    private Pane accueilPane;
    @FXML
    private TableView<Map<String, String>> coursEncoursTableView;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> coursEncoursProfesseurColumn;
    @FXML
    private TableColumn<Map<String, String>, String> coursEncoursSalleColumn, coursEncoursHorairesColumn,
            coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn;
    @FXML
    private Label nombreLaboratoiresDisponibles, nombreSalleCoursDisponibles, nombreSalleDeSportDisponibles,
            nombreClasse3EnCours, nombreClasse4EnCours, nombreClasse5EnCours, nombreClasse6EnCours,
            nombreAbscencesNonExcuses;

    @FXML
    private ImageView photoAdmin;
    @FXML
    private Text nameAdminText;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField searchTextFieldAccueilPane;
    @FXML
    private ComboBox<NiveauClasse> filterComboBoxCoursEncours;
    private List<String> niveauNames = ClasseService.getAllNiveauNames();
    private List<NiveauClasse> niveaux = ClasseService.getAllNiveaux();
    private List<NiveauClasse> niveauObjects = niveaux.stream()
            .filter(niveau -> niveauNames.contains(niveau.getNom()))
            .collect(Collectors.toList());
    private ObservableList<Map<String, String>> initialData;
    private ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

    private ObservableList<Map<String, String>> initialDataSalles;
    private ObservableList<Map<String, String>> dataSalles = FXCollections.observableArrayList();

    private ObservableList<Map<String, String>> initialDataClasses;
    private ObservableList<Map<String, String>> dataClasses = FXCollections.observableArrayList();

    private NiveauClasse allOption = new NiveauClasse("All");
    //
    //
    //
    //
    //
    //
    //
    // Everything related to classespane
    private ClassesPaneController classesPaneController;
    @FXML
    private TableView<Map<String, String>> classesTableView;
    @FXML
    private Pane classesPane;
    @FXML
    private TableColumn<Map<String, String>, String> classesClasseColumn, classesEffectifColumn, classesStatusColumn,
            classesSalleColumn, classesCoursColumn, classesActionColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> classesProfesseurColumn;
    @FXML
    private Label nombre3emeEnCours, nombre4emeEnCours, nombre5emeEnCours, nombre6emeEnCours,
            nombreLabDisponibles, nombreSalleCourDisponibles, nombreSalleSportDisponibles;
    @FXML
    private TextField searchFieldClassesPane;
    @FXML
    ComboBox<NiveauClasse> filterComboBoxClassesPane;
    //
    //
    //
    //
    //
    //
    //
    // Everything related to parameterspane
    private ParametresPaneController parametresPaneController;
    @FXML
    private Pane parametresPane;
    @FXML
    private Button sauvegarderButtonInfosParametres, sauvegarderButtonSecurityParametres;
    @FXML
    private DatePicker dateBirthDatePickerParametres;
    @FXML
    private TextField nomFieldParametres, prenomFieldParametres, telephoneFieldParametres, emailFieldParametres,
            oldPasswordParametres,
            newPasswordParametres;

    @FXML
    public void handleCancelParametresChanges(ActionEvent event) {
        parametresPaneController.annulerLesModifications();
    }

    @FXML
    public void handleSauvegarderButtonSecurityParameters(ActionEvent event) {
        parametresPaneController.handleSauvegarderButtonSecurityParameters();
    }

    @FXML
    public void handleSauvegarderButtonInfosParameters(ActionEvent event) {
        parametresPaneController.handleSauvegarderButtonInfosParameters();
    }

    //
    //
    //
    //
    //
    //
    //
    // Everything related to sideBar
    private SidebarController sidebarController;
    @FXML
    private Button accueilButton, classesButton, parametresButton, deconnecterButton, sallesButton, affectationButton,
            annulerButtonParametres, absencesButton, professeursButton;

    public Button getAbsencesButton() {
        return absencesButton;
    }

    public Button getProfesseursButton() {
        return professeursButton;
    }

    @FXML
    public void handleSideBarButtonAction(ActionEvent event) {
        sidebarController.handleSideBarButtonAction(event);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // Everything realted to affectationPane
    private AffectationPaneController affectationPaneController;
    @FXML
    private Pane affectationPane;
    @FXML
    private Button handleAffecterButton, handleAnnulerAffectation, handleShowMaterielSalle;
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
    private ComboBox<Classe> classesComboAffectation;
    @FXML
    private TextField nomCoursFieldAffectation;

    @FXML
    public void handleAffecterButton(ActionEvent event) {
        affectationPaneController.affecterUneSeance();
        accueilPaneController.fillCurrentSeances(coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn,
                coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
    }

    @FXML
    public void handleAnnulerAffectation(ActionEvent event) {
        affectationPaneController.annulerAffectation();
    }

    @FXML
    private void handleShowMaterielSalle(ActionEvent e) {
        affectationPaneController.handleShowMaterielSalle();
    }

    @FXML
    private void handleAjouterTypeCoursButton(ActionEvent e) {
        affectationPaneController.AjouterTypeCours();
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // Everuthing realted sallespane
    private SallesPaneController sallesPaneController;
    @FXML
    private Pane sallesPane;
    @FXML
    private TableView<Map<String, String>> sallesTableView;
    @FXML
    private TextField searchFieldSalle;
    @FXML
    private ComboBox<CategorieSalle> categoryComboBox;
    private List<String> categorieNames = SallesService.getAllCategorieNames();
    private List<CategorieSalle> categories = SallesService.getAllCategories();
    private List<CategorieSalle> categorieObjects = categories.stream()
            .filter(categorieSalle -> categorieNames.contains(categorieSalle.getNom()))
            .collect(Collectors.toList());
    private CategorieSalle allOptionSalle = new CategorieSalle("All");
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // Everything related to activeclassepane
    @FXML
    private Pane activeClassePane;
    @FXML
    private TableView<Map<String, String>> classeEmploiTableView;
    @FXML
    private TableColumn<Map<String, String>, String> classeEmploiJourColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> classeEmploi8_10Column,
            classeEmploi10_12Column, classeEmploi14_16Column, classeEmploi16_18Column;
    @FXML
    private Text activeClasseLabel;
    @FXML
    private Label activeClasseNomLabel, activeClasseEffectif;
    @FXML
    private ImageView activeClasseStatutIcon;
    @FXML
    private TableView<Etudiant> listEtudiantsTableView;
    @FXML
    private TableColumn<Etudiant, Etudiant> listEtudiantsEtudiantColumn,
            listEtudiantsContactColumn, listEtudiantsContactParentsColumn;
    @FXML
    private TableColumn<Etudiant, String> listEtudiantsDateNaissanceColumn, listEtudiantsDeleteColumn,
            listEtudiantsEditColumn, listEtudiantsSexeColumn, listEtudiantsAbsenceColumn;
    @FXML
    private TextField searchListEtudiantTextField;
    @FXML
    private Button ajouterEtudiantButton;

    @FXML
    private void handleAjouterButtonOnAction(ActionEvent e) {
        classesPaneController.ajouterEtudiant();
    }

    public void fillListEtudiantsTableView(String searchKey) {
        classesPaneController.fillListEtudiantsTableView(activeClasse.getId(), searchKey, listEtudiantsTableView,
                listEtudiantsEtudiantColumn, listEtudiantsContactColumn, listEtudiantsContactParentsColumn,
                listEtudiantsDateNaissanceColumn, listEtudiantsDeleteColumn, listEtudiantsEditColumn,
                listEtudiantsSexeColumn, listEtudiantsAbsenceColumn);
    }

    public void fillListCoursEncoursTableView(String searchKey) {
        accueilPaneController.fillListCoursEncoursTableView(searchKey,
                coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn, coursEncoursClasseColumn,
                coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
    }

    public void fillListClassesTableView(String searchKey) {
        classesPaneController.fillListClassesTableView(activeClasse.getId(),
                searchKey,
                classesTableView,
                classesSalleColumn,
                classesStatusColumn,
                classesClasseColumn,
                classesEffectifColumn,
                classesCoursColumn,
                classesProfesseurColumn,
                classesActionColumn,
                activeClassePane,
                voirClasseClickHandler);
    }

    ButtonClickHandler<Map<String, String>> voirClasseClickHandler = rowData -> {
        // set activeClasse (Classe) data
        // used in active listEtudiantsPane
        System.out.println("Row Data = " + rowData);
        activeClasse.setId(Integer.parseInt(rowData.get("classeId")));
        activeClasse.setEffectif(Integer.parseInt(rowData.get("effectif")));
        activeClasse.setNom(rowData.get("classeNom"));
        // set nomClasse's breadcrumb lable in activeClassePane
        activeClasseLabel.setText(rowData.get("classeNom"));

        // set activeClasse's informations
        classesPaneController.setActiveClasseInformation(rowData, activeClasseEffectif, activeClasseNomLabel,
                activeClasseEffectif, activeClasseStatutIcon);
        classesPaneController.fillEmploisDeTempsTableView(rowData.get("classeId"), classeEmploiJourColumn,
                classeEmploiTableView,
                classeEmploi8_10Column, classeEmploi10_12Column, classeEmploi14_16Column, classeEmploi16_18Column);
        // fill classeEmploiTableView

        fillListEtudiantsTableView("");
    };

    public void fillActiveClasseEmploisDeTemps() {
        classesPaneController.fillEmploisDeTempsTableView(String.valueOf(activeClasse.getId()), classeEmploiJourColumn,
                classeEmploiTableView,
                classeEmploi8_10Column, classeEmploi10_12Column, classeEmploi14_16Column, classeEmploi16_18Column);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    // Everything related to activesallepane
    @FXML
    private Text activeSalleLabel, tempText1;
    @FXML
    private Pane activeSallePane;
    @FXML
    private Label activeSalleDisponibilite, activeSalleCoccupe, activeSalleCapacite, activeSalleNomLabel,
            materielSalleLabel;
    @FXML
    private ImageView activeSalleStatutIcon;
    @FXML
    private TableView<Map<String, String>> salleEmploiTableView;
    @FXML
    private TableColumn<Map<String, String>, String> sallesColumn, sallesCapaciteColumn, sallesStatutColumn,
            sallesCoursColumn, sallesClassesColumn,
            sallesActionColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> salleEmploi8_10Column,
            salleEmploi10_12Column, salleEmploi14_16Column, salleEmploi16_18Column;
    @FXML
    private TableColumn<Map<String, String>, String> salleEmploiJourColumn;
    @FXML
    private AnchorPane materielSalleAnchropane;

    public void fillMaterielSalleAnchorPane(int salleId, AnchorPane materielSalleAnchorPane) {
        // Effacer le contenu précédent de l'AnchorPane
        materielSalleAnchorPane.getChildren().clear();

        // Récupérer les matériels de la salle à partir de la base de données
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
    }

    // Absence
    @FXML
    private Pane absencesPane;

    public Pane getAbsencesPane() {
        return absencesPane;
    }

    @FXML
    private TableView<Map<String, String>> listAbscencesTableView;

    @FXML
    private TableColumn<Map<String, String>, String> laa_810_StatutColumn, laa_1012_StatutColumn,
            laa_1416_StatutColumn,
            laa_1618_StatutColumn;
    @FXML
    private TableColumn<Map<String, String>, String> laa_810_ExcuseColumn, laa_1012_ExcuseColumn,
            laa_1416_ExcuseColumn,
            laa_1618_ExcuseColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> laaEtudiantColumn;
    @FXML
    private ComboBox<Classe> listAbsenceclasseComboBox;
    @FXML
    private ComboBox<JoursSemaine> listAbsenceJourComboBox;

    @FXML
    private TextField listAbsencenumSemaineTextField;

    @FXML
    private void handleListAbsenceRefreshButton(ActionEvent e) {
        fillListAbsences();
    }
    //
    //
    //
    //
    //
    //
    //
    //
    //

    // ProfesseursPane
    private EnsignantsPaneController ensignantsPaneController;
    @FXML
    private Pane professeursPane;
    @FXML
    private Pane activeEnseignantPane;
    @FXML
    private TableView<Map<String, String>> professeursTableView;
    @FXML

    private TableColumn<Map<String, String>, Map<String, String>> professeursProfColumn, professeursCoursEnCoursColumn;
    @FXML
    private TableColumn<Map<String, String>, String> professeursTelephoneColumn, professeursNaissanceColumn,
            professeursNbrCoursColumn, professeursStatutColumn,
            professeursEditActionColumn, professeursDeleteActionColumn,
            professeursVoirActionColumn;
    @FXML
    private Label activeEnseignantNomLabel, activeEnseignantTotalSeancesLabel;
    @FXML
    private ImageView activeEnseignantStatutIcon;
    @FXML
    private Text activeEnseignantNomText;

    public Label getActiveEnseignantNomLabel() {
        return activeEnseignantNomLabel;
    }

    public Label getActiveEnseignantTotalSeancesLabel() {
        return activeEnseignantTotalSeancesLabel;
    }

    public ImageView getActiveEnseignantStatutIcon() {
        return activeEnseignantStatutIcon;
    }

    public Text getActiveEnseignantNomText() {
        return activeEnseignantNomText;
    }

    @FXML
    private void handleAjouterEnseignantButton(ActionEvent e) {
        ensignantsPaneController.ajouterEnseignant();
    }

    @FXML
    private TableView<Map<String, String>> enseignantEmploiTableView;
    @FXML
    private TableColumn<Map<String, String>, String> enseignantEmploiJourColumn;
    @FXML
    private TableColumn<Map<String, String>, Map<String, String>> enseignantEmploi8_10Column,
            enseignantEmploi10_12Column, enseignantEmploi14_16Column, enseignantEmploi16_18Column;

    public Pane getActiveEnseignantPane() {
        return activeEnseignantPane;
    }

    public TableView<Map<String, String>> getEnseignantEmploiTableView() {
        return enseignantEmploiTableView;
    }

    public TableColumn<Map<String, String>, String> getEnseignantEmploiJourColumn() {
        return enseignantEmploiJourColumn;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getEnseignantEmploi8_10Column() {
        return enseignantEmploi8_10Column;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getEnseignantEmploi10_12Column() {
        return enseignantEmploi10_12Column;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getEnseignantEmploi14_16Column() {
        return enseignantEmploi14_16Column;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getEnseignantEmploi16_18Column() {
        return enseignantEmploi16_18Column;
    }

    public TableView<Map<String, String>> getProfesseursTableView() {
        return professeursTableView;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getProfesseursProfColumn() {
        return professeursProfColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursTelephoneColumn() {
        return professeursTelephoneColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursNaissanceColumn() {
        return professeursNaissanceColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursNbrCoursColumn() {
        return professeursNbrCoursColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursStatutColumn() {
        return professeursStatutColumn;
    }

    public TableColumn<Map<String, String>, Map<String, String>> getProfesseursCoursEnCoursColumn() {
        return professeursCoursEnCoursColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursEditActionColumn() {
        return professeursEditActionColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursDeleteActionColumn() {
        return professeursDeleteActionColumn;
    }

    public TableColumn<Map<String, String>, String> getProfesseursVoirActionColumn() {
        return professeursVoirActionColumn;
    }

    public Pane getProfesseursPane() {
        return professeursPane;
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // initialize
    public void initialize(int adminId, String role) {
        //
        if ("vie scolaire".equalsIgnoreCase(role)) {
            affectationButton.setVisible(false);
        }

        niveauObjects.add(0, allOption);
        // activeClasse caches the selected classe informations from classesTables View
        activeClasse = new Classe();
        //
        // to identify the logged in administrator
        this.loggedInAdminId = adminId;

        categorieObjects = categories.stream()
                .filter(categorieSalle -> categorieNames.contains(categorieSalle.getNom()))
                .collect(Collectors.toList());
        categorieObjects.add(0, allOptionSalle);
        initialData = FXCollections.observableArrayList(SeanceService.getSeancesEnCoursBis());
        coursEncoursTableView.setItems(data);

        initialDataClasses = FXCollections.observableArrayList(ClasseService.getAllClassesWithCurrentSeances());
        classesTableView.setItems(dataClasses);

        // to show loacl date at the header of the dashboard
        localDateTimeLabelAccueilPane.setText(FormattedLocalDateTime.getFormattedDateTime());
        //
        // Initialize sub containers
        sidebarController = new SidebarController(this);
        parametresPaneController = new ParametresPaneController(this);
        affectationPaneController = new AffectationPaneController(this);
        classesPaneController = new ClassesPaneController(this);
        sallesPaneController = new SallesPaneController(this);
        accueilPaneController = new AccueilPaneController(this);
        ensignantsPaneController = new EnsignantsPaneController(this);

        ensignantsPaneController.initialize();
        //
        //
        //
        //
        //
        // accueilPaneController usage
        accueilPaneController.updateCoursEnCoursLabels(nombreClasse3EnCours, nombreClasse4EnCours, nombreClasse5EnCours,
                nombreClasse6EnCours);
        accueilPaneController.updateEffectifEnCours(effectifEnCours);
        accueilPaneController.updateSallesOccupesLabels(nombreLaboratoiresDisponibles, nombreSalleCoursDisponibles,
                nombreSalleDeSportDisponibles);
        accueilPaneController.updateCoursEnCours(coursEnCours);
        accueilPaneController.updateSallesDisponibles(SallesDisponibles);
        accueilPaneController.fillCurrentSeances(coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn,
                coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
        accueilPaneController.updateNameAdmin(nameAdminText);
        accueilPaneController.updatePhotoAdmin(photoAdmin);
        accueilPaneController.initialize();
        //
        //
        filterComboBoxCoursEncours.setItems(FXCollections.observableArrayList(niveauObjects));
        filterComboBoxCoursEncours.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        filterTable(newValue);
                    } else {
                        // Si aucun niveau n'est sélectionné, affichez toutes les données
                        coursEncoursTableView.setItems(initialData);
                    }
                });

        //
        //
        //
        //
        // sideBarController usage
        sidebarController.initialize();
        //
        //
        //
        //
        //
        // parametersPaneController usage
        parametresPaneController.initialize();
        parametresPaneController.loadAdminData();
        //
        //
        //
        //
        //
        // affectationPaneController usage
        affectationPaneController.initialize();
        //
        //
        //
        //
        //
        // classesPaneController usage
        classesPaneController.initialize();
        classesPaneController.fillClassesWithSeances(classesTableView, classesSalleColumn, classesStatusColumn,
                classesClasseColumn, classesEffectifColumn, classesCoursColumn, classesActionColumn,
                classesProfesseurColumn, activeClassePane, voirClasseClickHandler);

        filterComboBoxClassesPane.setItems(FXCollections.observableArrayList(niveauObjects));
        filterComboBoxClassesPane.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        filterTableClasses(newValue);
                    } else {
                        // Si aucun niveau n'est sélectionné, affichez toutes les données
                        classesTableView.setItems(initialDataClasses);
                    }
                });

        //
        //
        //
        //
        //
        // sallesPaneController usage
        sallesPaneController.initialize();
        sallesPaneController.fillSallesWithSeances(sallesTableView, sallesColumn, sallesStatutColumn,
                sallesClassesColumn, sallesCapaciteColumn, sallesCoursColumn, sallesActionColumn);
        sallesActionColumn.setCellFactory(new CustomSalleCellButton(activeSallePane, clickHandler2));
        //
        //
        //
        initialDataSalles = FXCollections.observableArrayList(SallesService.getAllSallesWithCurrentSeances());
        dataSalles.addAll(initialDataSalles);
        sallesTableView.setItems(dataSalles);
        categoryComboBox.setItems(FXCollections.observableArrayList(categorieObjects));
        categoryComboBox.setOnAction(e -> filterTable1(categoryComboBox.getValue()));
        
        // //
        //
        // activeClassePaneController usage
        searchTextFieldAccueilPane.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillListCoursEncoursTableView(newValue);
            }
        });

        searchFieldClassesPane.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillListClassesTableView(newValue);
            }
        });

        searchListEtudiantTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillListEtudiantsTableView(newValue);
            }
        });
        searchFieldSalle.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // Si le champ de recherche est vide, remplissez la table avec toutes les
                // données
                sallesPaneController.fillSallesWithSeances(sallesTableView, sallesColumn, sallesStatutColumn,
                        sallesClassesColumn, sallesCapaciteColumn, sallesCoursColumn, sallesActionColumn);
            } else {
                // Sinon, effectuez la recherche avec le nouveau mot-clé
                sallesPaneController.search(newValue, sallesTableView);
            }
        });
        //
        //
        //
        //
        //
        //
        fillAbsencesClassesComboBox();
        fillListAbsencesJoursSemaine();
        listAbsencenumSemaineTextField.setText(String.valueOf(DateUtil.nombreDeSemainesDepuisDebutDesEtudes()));
        fillListAbsences();
        // Add a TextFormatter to restrict the input to numbers
        listAbsencenumSemaineTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change; // Allow empty input to clear the field
            }
            try {
                int newValue = Integer.parseInt(newText);
                if (newValue >= 1 && newValue <= DateUtil.nombreDeSemainesDepuisDebutDesEtudes()) {
                    return change; // Allow change if it's a valid number within range
                }
            } catch (NumberFormatException e) {
                // Not a valid number
            }
            return null; // Reject change
        }));

        // Add a listener to ensure the field value remains within the range
        listAbsencenumSemaineTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                return; // Allow empty input to clear the field
            }
            try {
                int intValue = Integer.parseInt(newValue);
                if (intValue < 1 || intValue > DateUtil.nombreDeSemainesDepuisDebutDesEtudes()) {
                    listAbsencenumSemaineTextField.setText(oldValue); // Revert to old value if out of range
                }
            } catch (NumberFormatException e) {
                listAbsencenumSemaineTextField.setText(oldValue); // Revert to old value if not a valid number
            }
        });

    }

    public void fillAbsencesClassesComboBox() {

        Vector<Classe> allClasses = ClasseService.getAllClasses();
        listAbsenceclasseComboBox.getItems().clear();
        listAbsenceclasseComboBox.getItems().addAll(allClasses);
        if (!allClasses.isEmpty()) {
            listAbsenceclasseComboBox.getSelectionModel().select(0);
        }
    }

    public void fillListAbsencesJoursSemaine() {
        listAbsenceJourComboBox.getItems().addAll(JoursSemaine.values());
        listAbsenceJourComboBox.getSelectionModel().select(DateUtil.numDuJour() - 1);
    }

    ButtonClickHandler<Map<String, String>> statusTogglerClickHandler = rowData -> {
        fillListAbsences();
    };

    public void fillListAbsences() {
        // AbscencePane
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();

        if (listAbsencenumSemaineTextField.getText().isEmpty()) {
            listAbsencenumSemaineTextField.setText(String.valueOf(DateUtil.nombreDeSemainesDepuisDebutDesEtudes()));
        }

        int numSemaine = Integer.parseInt(listAbsencenumSemaineTextField.getText());
        int idClasse = listAbsenceclasseComboBox.getSelectionModel().getSelectedItem().getId();
        String jourDeSemaine = listAbsenceJourComboBox.getSelectionModel().getSelectedItem().name();

        data.addAll(AbsenceService.getListAbsence(numSemaine, jourDeSemaine, idClasse));

        // laa_810_StatutColumn.setCellValueFactory(
        // cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        laa_810_StatutColumn.setCellFactory(new AbsenceStatusToggler("8_10", numSemaine, statusTogglerClickHandler));
        // laa_1012_StatutColumn.setCellValueFactory(
        // cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        laa_1012_StatutColumn.setCellFactory(new AbsenceStatusToggler("10_12", numSemaine, statusTogglerClickHandler));
        // laa_1416_StatutColumn.setCellValueFactory(
        // cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        laa_1416_StatutColumn.setCellFactory(new AbsenceStatusToggler("14_16", numSemaine, statusTogglerClickHandler));
        // laa_1618_StatutColumn.setCellValueFactory(
        // cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        laa_1618_StatutColumn.setCellFactory(new AbsenceStatusToggler("16_18", numSemaine, statusTogglerClickHandler));
        //
        laa_810_ExcuseColumn.setCellFactory(new AbsenceIsExcusedToggler("8_10", numSemaine, statusTogglerClickHandler));
        laa_1012_ExcuseColumn.setCellFactory(new AbsenceIsExcusedToggler("10_12", numSemaine, statusTogglerClickHandler));
        laa_1416_ExcuseColumn.setCellFactory(new AbsenceIsExcusedToggler("14_16", numSemaine, statusTogglerClickHandler));
        laa_1618_ExcuseColumn.setCellFactory(new AbsenceIsExcusedToggler("16_18", numSemaine, statusTogglerClickHandler));

        //
        laaEtudiantColumn.setCellFactory(new EtudiantNomPhotoCellMap());
        laaEtudiantColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        listAbscencesTableView.setItems(data);
    }

    @FXML
    private void handleRefreshButtonAction(ActionEvent e) {
        accueilPaneController.fillCurrentSeances(coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn,
                coursEncoursClasseColumn, coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
        accueilPaneController.updateCoursEnCoursLabels(nombreClasse3EnCours, nombreClasse4EnCours, nombreClasse5EnCours,
                nombreClasse6EnCours);
        accueilPaneController.updateEffectifEnCours(effectifEnCours);
        accueilPaneController.updateSallesOccupesLabels(nombreLaboratoiresDisponibles, nombreSalleCoursDisponibles,
                nombreSalleDeSportDisponibles);
        accueilPaneController.updateCoursEnCours(coursEnCours);
        accueilPaneController.updateSallesDisponibles(SallesDisponibles);
    }

    ButtonClickHandler<Map<String, String>> clickHandler2 = rowData -> {

        System.out.println(rowData.get("idSalle"));

        sallesPaneController.setActiveSalleInformation(rowData, activeSalleLabel, activeSalleNomLabel,
                activeSalleCapacite,
                activeSalleDisponibilite, activeSalleCoccupe, activeSalleStatutIcon, materielSalleLabel,
                materielSalleAnchropane);
        sallesPaneController.showActiveSalleMaterials(Integer.parseInt(rowData.get("idSalle")));

        activeSalleLabel.setText(rowData.get("nomSalle"));
        StringBuilder builder = new StringBuilder();

        // Iterate over the entries of the rowData map
        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            // Concatenate the key-value pair into the StringBuilder
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        data.addAll(SallesService.getEmploiDeTempsSalle(rowData.get("idSalle")));
        salleEmploiJourColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                cellData.getValue().get("Day").substring(0, 3)));
        // 8-10
        salleEmploi8_10Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        salleEmploi8_10Column.setCellFactory(new ET0810());
        // 10-12
        salleEmploi10_12Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        salleEmploi10_12Column.setCellFactory(new ET1012());
        // 14-16
        salleEmploi14_16Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        salleEmploi14_16Column.setCellFactory(new ET1416());
        // 16-18
        salleEmploi16_18Column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        salleEmploi16_18Column.setCellFactory(new ET1618());

        // Set the concatenated string to the tempText
        tempText1.setText(builder.toString());
        salleEmploiTableView.setItems(data);
    };

    private void filterTable(NiveauClasse selectedNiveauClasse) {
        ObservableList<Map<String, String>> filteredItems = FXCollections.observableArrayList();

        // Vérifiez si initialData est null ou vide
        if (initialData == null || initialData.isEmpty()) {
            System.out.println("initialData is null or empty");
            return;
        }

        if (selectedNiveauClasse != null && "All".equals(selectedNiveauClasse.getNom())) {
            filteredItems.addAll(initialData);
        } else {
            // Sinon, filtrez les données en fonction de la sélection
            for (Map<String, String> item : initialData) {
                if (item.get("classe").contains(selectedNiveauClasse.getNom())) {
                    filteredItems.add(item);
                }
            }
        }
        // Mettez à jour le TableView avec les éléments filtrés
        coursEncoursTableView.setItems(filteredItems);
    }

    private void filterTable1(CategorieSalle selectedCategorieSalle) {
        ObservableList<Map<String, String>> filteredItems = FXCollections.observableArrayList();

        if (initialDataSalles == null || initialDataSalles.isEmpty()) {
            System.out.println("initialDataSalles is null or empty");
            return;
        }
        if (selectedCategorieSalle != null && "All".equals(selectedCategorieSalle.getNom())) {
            filteredItems.addAll(initialDataSalles);
        }

        Map<String, String> salleToCategorieMap = new HashMap<>();
        salleToCategorieMap.put("Salle A1", "Classe");
        salleToCategorieMap.put("Salle A2", "Classe");
        salleToCategorieMap.put("Salle B1", "Classe");
        salleToCategorieMap.put("Salle B2", "Classe");
        salleToCategorieMap.put("Salle C1", "Classe");
        salleToCategorieMap.put("Salle C2", "Classe");
        salleToCategorieMap.put("Salle D1", "Classe");
        salleToCategorieMap.put("Salle D2", "Classe");
        salleToCategorieMap.put("Salle E1", "Classe");
        salleToCategorieMap.put("Salle E2", "Classe");
        salleToCategorieMap.put("Salle F1", "Classe");
        salleToCategorieMap.put("Salle F2", "Classe");
        salleToCategorieMap.put("Salle G1", "Classe");
        salleToCategorieMap.put("Salle G2", "Classe");
        salleToCategorieMap.put("Salle H1", "Classe");
        salleToCategorieMap.put("Salle H2", "Classe");
        salleToCategorieMap.put("Salle H3", "Classe");
        salleToCategorieMap.put("Laboratoire de biologie", "Laboratoire");
        salleToCategorieMap.put("Laboratoire de chimie", "Laboratoire");
        salleToCategorieMap.put("Terrain de volleyball", "Salle de sports");
        salleToCategorieMap.put("Terrain de Basketball", "Salle de sports");
        salleToCategorieMap.put("Terrain de Football", "Salle de sports");

        for (Map<String, String> item : initialDataSalles) {
            String nomSalle = item.get("nomSalle");
            String categorieSalle = salleToCategorieMap.get(nomSalle);
            if (categorieSalle != null && categorieSalle.equals(selectedCategorieSalle.getNom())) {
                filteredItems.add(item);
            }
        }
        sallesTableView.setItems(filteredItems);
    }

    private void filterTableClasses(NiveauClasse selectedNiveauClasse) {
        ObservableList<Map<String, String>> filteredItems = FXCollections.observableArrayList();

        // Vérifiez si initialData est null ou vide
        if (initialDataClasses == null || initialDataClasses.isEmpty()) {
            System.out.println("initialDataClasses is null or empty");
            return;
        }

        if (selectedNiveauClasse != null && "All".equals(selectedNiveauClasse.getNom())) {
            filteredItems.addAll(initialDataClasses);
        } else {
            // Sinon, filtrez les données en fonction de la sélection
            for (Map<String, String> item : initialDataClasses) {
                if (item.get("classeNom").contains(selectedNiveauClasse.getNom())) {
                    filteredItems.add(item);
                }
            }
        }
        classesTableView.setItems(filteredItems);
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

    public void setNombreLabOccupesText(String text) {
        nombreLabDisponibles.setText(text);
    }

    public void setSalleDisponiblesText(String text) {
        nombreSalleCourDisponibles.setText(text);
    }

    public void setNombreSalleSportOccupesText(String text) {
        nombreSalleSportDisponibles.setText(text);
    }

    // Methods to access UI elements SubControllers

    public int getLoggedInAdminId() {
        return loggedInAdminId;
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

    public Classe getActiveClasse() {
        return activeClasse;
    }

    public void setCoursEncoursTableView(TableView<Map<String, String>> coursEncoursTableView) {
        this.coursEncoursTableView = coursEncoursTableView;
    }

    public TableView<Map<String, String>> getCoursEncoursTableView() {
        return coursEncoursTableView;
    }

    public void setSallesTableView(TableView<Map<String, String>> sallesTableView) {
        this.sallesTableView = sallesTableView;
    }

    public TableView<Map<String, String>> getSallesTableView() {
        return sallesTableView;
    }

    public void setInitialData(ObservableList<Map<String, String>> initialData) {
        this.initialData = initialData;
    }

    public ObservableList<Map<String, String>> getInitialData() {
        return initialData;
    }

    public void setSearchFieldSalle(TextField searchField) {
        this.searchFieldSalle = searchField;
    }

    public TextField getSearchFieldSalle() {
        return searchFieldSalle;
    }

    public void setAccueilPaneController(AccueilPaneController accueilPaneController) {
        this.accueilPaneController = accueilPaneController;
    }

    public AccueilPaneController getAccueilPaneController() {
        return accueilPaneController;
    }
}