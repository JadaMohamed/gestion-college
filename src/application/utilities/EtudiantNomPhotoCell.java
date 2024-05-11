package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Callback;

import application.model.Etudiant;

public class EtudiantNomPhotoCell implements
        Callback<TableColumn<Etudiant, Etudiant>, TableCell<Etudiant, Etudiant>> {

    @Override
    public TableCell<Etudiant, Etudiant> call(
            TableColumn<Etudiant, Etudiant> param) {
        return new TableCell<Etudiant, Etudiant>() {
            @Override
            protected void updateItem(Etudiant item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Extract enseignant data from the map;
                    String etudiantFullName = item.getNom().substring(0, 1).toUpperCase() + item.getNom().substring(1)
                            + " "
                            + item.getPrenom().substring(0, 1).toUpperCase() + item.getPrenom().substring(1);
                    String etudiantCNE = item.getCne();
                    String enseignantPhotoUrl = item.getPhotoURL();
                    if ((etudiantFullName == null || etudiantFullName.contains("-"))) {
                        Label cellLabel = new Label("-");
                        setText(null);
                        setGraphic(cellLabel);
                        return;
                    }

                    // Create labels for full name and email
                    Label nameLabel = new Label(etudiantFullName);
                    Label cneLabel = new Label(etudiantCNE);

                    // Set font weight for email label
                    cneLabel.setStyle("-fx-font-weight: lighter;-fx-font-size: 10px;-fx-text-fill: #475467;");
                    nameLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 12px;");

                    // Create an image view for the photo
                    ImageView imageView = new ImageView();
                    try {
                        imageView.setImage(new Image(getClass().getResourceAsStream(enseignantPhotoUrl)));
                    } catch (Exception e) {
                        imageView.setImage(
                                new Image(getClass().getResourceAsStream("/resources/images/profiles/default.png")));
                    }

                    // Set size of the image view
                    imageView.setFitWidth(24);
                    imageView.setFitHeight(24);

                    // Create a VBox to hold labels vertically
                    VBox vbox = new VBox(nameLabel, cneLabel);
                    vbox.setAlignment(Pos.CENTER_LEFT);

                    // Create an HBox to hold the image and VBox vertically centered
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(imageView, vbox);
                    hbox.setSpacing(10);
                    hbox.setAlignment(Pos.CENTER_LEFT);

                    // Set the HBox as the graphic of the cell
                    setGraphic(hbox);
                }
            }

        };
    }
}
