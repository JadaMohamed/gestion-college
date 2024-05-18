package application.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

import application.model.Classe;
import application.model.Enseignant;
import application.model.Horaires;
import application.model.Salle;
import application.model.Seance;
import application.model.TypeCours;
import application.model.enums.JoursSemaine;
import application.repositories.CoursRepository;
import application.repositories.SallesRepository;
import application.repositories.SeanceRepository;
import application.repositories.TypeCoursRepository;
import application.services.ClasseService;
import application.services.EnseignantService;
import application.services.SallesService;
import application.services.SeanceService;
import application.services.TypeCoursService;
import application.utilities.PushAlert;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ConsulterSeanceSceneController {

    private Seance seance = new Seance();

    private Stage stage;
    @FXML
    private AnchorPane consulterSeanceRootPane;
    @FXML
    private ComboBox<Salle> sallesComboBox;
    @FXML
    private ComboBox<Horaires> horairesComboBox;
    @FXML
    private ComboBox<JoursSemaine> joursComboBox;
    @FXML
    private ComboBox<Enseignant> enseignantComboBox;
    @FXML
    private ComboBox<TypeCours> typeCoursComboBox;
    @FXML
    private ComboBox<Classe> classesComboBox;
    @FXML
    private TextField nomCoursField;

    @FXML
    public void handleModifierButtonOnAction(ActionEvent event) {
        modifierUneSeance();
    }

    @FXML
    public void handleSupprimerButtonOnAction(ActionEvent event) {
        Stage currentStage = (Stage) consulterSeanceRootPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this seance ? ");

        // Show and wait for the user's response
        alert.initOwner(currentStage);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, perform delete action
                SeanceRepository.supprimerSeanceById(seance.getId());
                CoursRepository.supprimerCoursById(seance.getCours().getId());
                PushAlert.showAlert("Succès",
                        "La seance a été supprimé avec succès",
                        AlertType.INFORMATION,
                        currentStage);
                Stage a = (Stage) consulterSeanceRootPane.getScene().getWindow();
                a.close();
            }

        });
    }

    @FXML
    public void handleAjouterTypeCoursButton(ActionEvent event) {
        AjouterTypeCours();
    }

    @FXML
    public void handleShowMaterielSalle() {
        Salle selectedSalle = sallesComboBox.getValue();
        if (selectedSalle != null) {
            int salleId = selectedSalle.getId();
            ResultSet resultSet = SallesRepository.getMaterialBySalleId(salleId);
            try {
                if (resultSet.next()) {
                    resultSet.beforeFirst();
                    afficherAlerteMateriels(selectedSalle, resultSet);
                } else {
                    afficherAucunMaterielAlerte(selectedSalle);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            afficherAucuneSalleSelectionneeAlerte();
        }
    }

    private void setCurrentSeance(int idSeance) {
        this.seance = SeanceService.getSeanceById(idSeance);
    }

    @FXML
    public void initialize(Stage stage, int idSeance) {
        this.stage = stage;
        setCurrentSeance(idSeance);
        fillEnseignants();
        fillClassesComboBox();
        fillTypeCours();
        fillJoursSemaine();
        updateHoraires();
        nomCoursField.setText(seance.getCours().getNomCours());
        updateAvailableSallesByHoraire();
        Salle thisSalle = seance.getSalle();
        sallesComboBox.getItems().add(thisSalle);
        sallesComboBox.getSelectionModel().select(thisSalle);

        joursComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> updateHoraires());
        classesComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    updateHoraires();
                });
        enseignantComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> updateHoraires());

        horairesComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> updateAvailableSallesByHoraire());
    }

    public void fillClassesComboBox() {

        Vector<Classe> allClasses = ClasseService.getAllClasses();
        classesComboBox.getItems().clear();
        classesComboBox.getItems().addAll(allClasses);
        if (!allClasses.isEmpty()) {
            ObservableList<Classe> Classes = classesComboBox.getItems();
            for (Classe Classe : Classes) {
                if (Classe.getId() == seance.getClasse().getId()) {
                    classesComboBox.getSelectionModel().select(Classe);
                    break;
                }
            }
        }
    }

    public void fillTypeCours() {
        Vector<TypeCours> allTypes = TypeCoursService.getAllTypes();
        typeCoursComboBox.getItems().clear();
        typeCoursComboBox.getItems().addAll(allTypes);
        if (!allTypes.isEmpty()) {
            ObservableList<TypeCours> TypeCourss = typeCoursComboBox.getItems();
            for (TypeCours TypeCours : TypeCourss) {
                if (TypeCours.getId() == seance.getCours().getTypeCours().getId()) {
                    typeCoursComboBox.getSelectionModel().select(TypeCours);
                    break;
                }
            }
        }
    }

    public void modifierUneSeance() {
        Salle selectedSalle = sallesComboBox.getSelectionModel().getSelectedItem();
        TypeCours selectedTypeCours = typeCoursComboBox.getSelectionModel()
                .getSelectedItem();
        Horaires selectedHoraires = horairesComboBox.getSelectionModel().getSelectedItem();
        JoursSemaine selectedJours = joursComboBox.getSelectionModel().getSelectedItem();
        Classe selectedClasse = classesComboBox.getSelectionModel().getSelectedItem();
        Enseignant selectedEnseignant = enseignantComboBox.getSelectionModel()
                .getSelectedItem();
        String selectedCourName = nomCoursField.getText();
        if (selectedClasse != null && selectedTypeCours != null && selectedEnseignant != null
                && !selectedCourName.isEmpty() && selectedHoraires != null && selectedJours != null
                && selectedSalle != null) {
            SeanceService.modifierSeance(selectedClasse, selectedTypeCours, selectedEnseignant, selectedCourName,
                    selectedHoraires, selectedJours, selectedSalle, seance.getCours().getId(), seance.getId());
            updateHoraires();
            updateAvailableSallesByHoraire();
            setCurrentSeance(seance.getId());
            PushAlert.showAlert("Modification succès", "La modification a été faite avec succès", AlertType.INFORMATION,
                    this.stage);
            Stage a = (Stage) consulterSeanceRootPane.getScene().getWindow();
            a.close();
        } else {
            PushAlert.showAlert("Modification échoué", "Veuillez remplir le formulaire", AlertType.ERROR,
                    this.stage);
        }

    }

    private void updateAvailableSallesByHoraire() {

        sallesComboBox.getItems().clear();
        Horaires selectedHoraires = horairesComboBox.getSelectionModel().getSelectedItem();
        JoursSemaine selectedJours = joursComboBox.getSelectionModel().getSelectedItem();

        if (selectedHoraires != null && selectedJours != null) {
            Vector<Salle> salles = SallesService.getAvailableSallesByHoraire(selectedJours, selectedHoraires);
            sallesComboBox.getItems().addAll(salles);
            sallesComboBox.getSelectionModel().select(0);
        }
    }

    public void AjouterTypeCours() {
        String typeCoursName;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter type cours");
        dialog.setHeaderText("Type Cours");
        dialog.setContentText("Entrez s'il vous plait le nom de type: ");

        // Show the dialog and wait for the user to input text
        Optional<String> result = dialog.showAndWait();

        // Capture the input and assign it to the variable motif
        typeCoursName = result.get();
        if (typeCoursName.isEmpty()) {

            PushAlert.showAlert("Ajout échoué", "Veuillez forni un nom pour le type", AlertType.ERROR,
                    this.stage);
        } else {
            TypeCoursRepository.AjouterTypeCours(typeCoursName);
            PushAlert.showAlert("Ajout succès", "L'ajout a été faite avec succès", AlertType.INFORMATION,
                    this.stage);
            Vector<TypeCours> allTypes = TypeCoursService.getAllTypes();
            typeCoursComboBox.getItems().clear();
            typeCoursComboBox.getItems().addAll(allTypes);
            if (!allTypes.isEmpty()) {
                for (TypeCours typeCours : allTypes) {
                    if (typeCours.getNom().equals(typeCoursName)) {
                        typeCoursComboBox.getSelectionModel().select(typeCours);
                        break;
                    }
                }
            }
        }
    }

    private void updateHoraires() {
        // Clear the existing items in horairesComboAffectation
        horairesComboBox.getItems().clear();

        // Get the selected JoursSemaine and Classe
        JoursSemaine selectedJour = joursComboBox.getValue();
        Classe selectedClasse = classesComboBox.getValue();
        Enseignant selectedEnseignant = enseignantComboBox.getValue();

        // Check if both JoursSemaine and Classe are selected
        if (selectedJour != null && selectedClasse != null) {
            // Get the available time slots for the selected JoursSemaine and Classe
            Vector<Horaires> horaires = SeanceService.getAvailableHoraires(selectedJour, selectedClasse,
                    selectedEnseignant);
            // Add the available time slots to horairesComboAffectation
            horairesComboBox.getItems().addAll(horaires);
            Horaires thisSeanceH = new Horaires(seance.getHeureDebut().toLocalTime(),
                    seance.getHeureFin().toLocalTime());
            if (joursComboBox.getSelectionModel().getSelectedItem() == seance.getJour()) {
                horairesComboBox.getItems().add(thisSeanceH);
            }
            if (horaires != null) {
                ObservableList<Horaires> horairesList = horairesComboBox.getItems();
                for (Horaires horaire : horairesList) {
                    if (horaire.getHeureDebut().equals(seance.getHeureDebut().toLocalTime())) {
                        horairesComboBox.getSelectionModel().select(horaire);
                        break;
                    }
                }
            }
        }
    }

    public void fillJoursSemaine() {
        joursComboBox.getItems().addAll(JoursSemaine.values());
        if (joursComboBox != null) {
            ObservableList<JoursSemaine> joursSemaines = joursComboBox.getItems();
            for (JoursSemaine joursSemaine : joursSemaines) {
                if (joursSemaine == seance.getJour()) {
                    joursComboBox.getSelectionModel().select(joursSemaine);
                    break;
                }
            }
        }

    }

    public void fillSalles() {
        Vector<Salle> salles = SallesService.getAllSalles();
        sallesComboBox.getItems().clear();
        sallesComboBox.getItems().addAll(salles);
        Salle thisSalle = seance.getSalle();
        sallesComboBox.getItems().add(thisSalle);
        if (!salles.isEmpty()) {
            sallesComboBox.getSelectionModel().select(thisSalle);
        }
    }

    public void fillEnseignants() {
        Vector<Enseignant> allEnseignants = EnseignantService.getAllEnseignants();
        enseignantComboBox.getItems().clear();
        enseignantComboBox.getItems().addAll(allEnseignants);

        // Set custom cell factory to display both full name, email, and photo
        enseignantComboBox.setCellFactory(
                (Callback<ListView<Enseignant>, ListCell<Enseignant>>) new Callback<ListView<Enseignant>, ListCell<Enseignant>>() {
                    @Override
                    public ListCell<Enseignant> call(ListView<Enseignant> param) {
                        return new ListCell<Enseignant>() {
                            @Override
                            protected void updateItem(Enseignant enseignant, boolean empty) {
                                super.updateItem(enseignant, empty);
                                if (empty || enseignant == null) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    // Create a label for the name and email
                                    Label nameLabel = new Label(enseignant.getNom() + " " + enseignant.getPrenom());
                                    Label emailLabel = new Label(enseignant.getEmail());

                                    // Set font weight for email label
                                    emailLabel.setStyle("-fx-font-weight: lighter;");

                                    // Create an image view for the photo
                                    ImageView imageView = new ImageView();
                                    imageView.setImage(new Image(enseignant.getPhotoURL()));

                                    // Set size of the image view
                                    imageView.setFitWidth(32);
                                    imageView.setFitHeight(32);

                                    // Create an HBox to hold the photo and text
                                    HBox hbox = new HBox();
                                    VBox vbox = new VBox(nameLabel, emailLabel);
                                    vbox.setSpacing(3);
                                    hbox.getChildren().addAll(imageView,
                                            vbox);
                                    hbox.setSpacing(10);
                                    hbox.setFillHeight(true);
                                    HBox.setHgrow(nameLabel, Priority.ALWAYS);
                                    // Set the HBox as the graphic of the cell
                                    setGraphic(hbox);
                                }
                            }
                        };
                    }
                });

        if (!allEnseignants.isEmpty()) {
            ObservableList<Enseignant> enseignants = enseignantComboBox.getItems();
            for (Enseignant enseignant : enseignants) {
                if (enseignant.getId() == seance.getCours().getEnseignant().getId()) {
                    enseignantComboBox.getSelectionModel().select(enseignant);
                    break;
                }
            }
        }
    }

    private void afficherAlerteMateriels(Salle salle, ResultSet resultSet) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Matériel de la salle : " + salle.getNomSalle());
        alert.setHeaderText(null);
        StringBuilder contentText = new StringBuilder(
                "Liste du matériel de la salle " + salle.getNomSalle() + " :\n\n");
        try {
            while (resultSet.next()) {
                contentText.append((resultSet.getString("quantite") + " " + resultSet.getString("nomMateriel")))
                        .append("\n");
            }
            alert.setContentText(contentText.toString());
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherAucunMaterielAlerte(Salle salle) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aucun matériel");
        alert.setHeaderText(null);
        alert.setContentText("Il n'y a aucun matériel dans la salle " + salle.getNomSalle() + ".");
        alert.showAndWait();
    }

    private void afficherAucuneSalleSelectionneeAlerte() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune salle sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une salle dans la liste.");
        alert.showAndWait();
    }
}
