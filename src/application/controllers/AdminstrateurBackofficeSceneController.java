package application.controllers;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.scene.layout.VBox;
import java.util.stream.Collectors;
import application.controllers.AdminstrateurBackofficeSceneSubController.AccueilPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.AffectationPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ClassesPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.ParametresPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.SallesPaneController;
import application.controllers.AdminstrateurBackofficeSceneSubController.SidebarController;
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
            annulerButtonParametres, absencesButton;

    public Button getAbsencesButton() {
        return absencesButton;
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
            listEtudiantsEditColumn, listEtudiantsSexeColumn;
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
                listEtudiantsSexeColumn);
    }

    public void fillListCoursEncoursTableView(String searchKey) {
        accueilPaneController.fillListCoursEncoursTableView(searchKey,
                coursEncoursTableView, coursEncoursSalleColumn,
                coursEncoursHorairesColumn, coursEncoursClasseColumn,
                coursEncoursCourNomColumn, coursEncoursEffectifColumn,
                coursEncoursProfesseurColumn);
    }

    ButtonClickHandler<Map<String, String>> voirClasseClickHandler = rowData -> {
        // set activeClasse (Classe) data
        // used in active listEtudiantsPane
        activeClasse.setId(Integer.parseInt(rowData.get("classeId")));
        activeClasse.setEffectif(Integer.parseInt(rowData.get("effectif")));
        activeClasse.setNom(rowData.get("classeNom"));
        // set nomClasse's breadcrumb lable in activeClassePane
        activeClasseLabel.setText(rowData.get("classeNom"));

        // set activeClasse's informations
        classesPaneController.setActiveClasseInformation(rowData, activeClasseEffectif, activeClasseNomLabel,
                activeClasseEffectif, activeClasseStatutIcon);

        // fill classeEmploiTableView
        classesPaneController.fillEmploisDeTempsTableView(rowData, classeEmploiJourColumn, classeEmploiTableView,
                classeEmploi8_10Column, classeEmploi10_12Column, classeEmploi14_16Column, classeEmploi16_18Column);
        fillListEtudiantsTableView("");
    };

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

    // ButtonClickHandler<Map<String, String>> voirSalleClickHandler2 = rowData -> {
    // System.out.println("hey");
    // System.out.println(activeSalle.getId());
    // // set activeSalle data
    // activeSalle.setId(Integer.parseInt(rowData.get("salleId")));
    // activeSalle.setNomSalle(rowData.get("Sallenom"));
    // // set nomSalle's breadcrumb label in activeSallePane
    // activeSalleLabel.setText(rowData.get("Sallenom"));

    // // set activeSalle's informations
    // sallesPaneController.setActiveSalleInformation(rowData, activeSalleLabel,
    // activeSalleNomLabel, activeSalleCapacite,
    // activeSalleDisponibilite, activeSalleCoccupe, activeSalleStatutIcon,
    // materielSalleLabel,materielSalleListView);
    // // Afficher les matériaux de la salle active
    // sallesPaneController.showActiveSalleMaterials(activeSalle.getId());
    // };

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
    //
    //
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
    public void initialize(int adminId) {
        //
        // activeClasse caches the selected classe informations from classesTables View
        activeClasse = new Classe();
        //
        // to identify the logged in administrator
        this.loggedInAdminId = adminId;

        initialData = FXCollections.observableArrayList(SeanceService.getSeancesEnCoursBis());
        coursEncoursTableView.setItems(data);
        //
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
        //
        //
        // activeClassePaneController usage
        searchTextFieldAccueilPane.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillListCoursEncoursTableView(newValue);
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
        laa_1012_ExcuseColumn
                .setCellFactory(new AbsenceIsExcusedToggler("10_12", numSemaine, statusTogglerClickHandler));
        laa_1416_ExcuseColumn
                .setCellFactory(new AbsenceIsExcusedToggler("14_16", numSemaine, statusTogglerClickHandler));
        laa_1618_ExcuseColumn
                .setCellFactory(new AbsenceIsExcusedToggler("16_18", numSemaine, statusTogglerClickHandler));

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
        // activeSalle.setId(Integer.parseInt(rowData.get("idSalle")));
        // activeSalle.setNomSalle(rowData.get("nomSalle"));
        // // set nomSalle's breadcrumb label in activeSallePane
        // activeSalleLabel.setText(rowData.get("nomSalle"));

        sallesPaneController.setActiveSalleInformation(rowData, activeSalleLabel, activeSalleNomLabel,
                activeSalleCapacite,
                activeSalleDisponibilite, activeSalleCoccupe, activeSalleStatutIcon, materielSalleLabel,
                materielSalleAnchropane);
        sallesPaneController.showActiveSalleMaterials(Integer.parseInt(rowData.get("idSalle")));

        activeSalleLabel.setText(rowData.get("nomSalle"));
        StringBuilder builder = new StringBuilder();

        System.out.println(rowData);
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

        // set activeSalle's informations
        // sallesPaneController.setActiveSalleInformation(rowData, activeSalleLabel,
        // activeSalleNomLabel, activeSalleCapacite,
        // activeSalleDisponibilite, activeSalleCoccupe, activeSalleStatutIcon,
        // materielSalleLabel);
        // Afficher les matériaux de la salle active
    };

    private void filterTable(NiveauClasse selectedNiveauClasse) {
        ObservableList<Map<String, String>> filteredItems = FXCollections.observableArrayList();

        // Vérifiez si initialData est null ou vide
        if (initialData == null || initialData.isEmpty()) {
            System.out.println("initialData is null or empty");
            return;
        }

        // Parcourez toutes les lignes de notre TableView
        for (Map<String, String> item : initialData) {
            if (item.get("classe").contains(selectedNiveauClasse.getNom())) {
                filteredItems.add(item);
            }
        }
        // Mettez à jour le TableView avec les éléments filtrés
        System.out.println(filteredItems);
        coursEncoursTableView.setItems(filteredItems);
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