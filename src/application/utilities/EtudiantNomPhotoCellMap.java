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
import java.util.Map;

public class EtudiantNomPhotoCellMap implements
        Callback<TableColumn<Map<String, String>, Map<String, String>>, TableCell<Map<String, String>, Map<String, String>>> {

    @Override
    public TableCell<Map<String, String>, Map<String, String>> call(
            TableColumn<Map<String, String>, Map<String, String>> param) {
        return new TableCell<Map<String, String>, Map<String, String>>() {
            @Override
            protected void updateItem(Map<String, String> item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Extract etudiant data from the map;
                    String etudiantFullName = item.get("etudiant_nom") + " " + item.get("etudiant_prenom");
                    String etudiantCNE = item.get("etudiant_cne");
                    String etudiantPhotoUrl = item.get("etudiant_photoURL");
                    if ((etudiantFullName == null || etudiantFullName.contains("-"))) {
                        Label cellLabel = new Label("-");
                        setText(null);
                        setGraphic(cellLabel);
                        return;
                    }

                    // Create labels for full name and email
                    Label nameLabel = new Label(etudiantFullName);
                    Label emailLabel = new Label(etudiantCNE);

                    // Set font weight for email label
                    emailLabel.setStyle("-fx-font-weight: lighter;-fx-font-size: 10px;-fx-text-fill: #475467;");
                    nameLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 11px;");

                    // Create an image view for the photo
                    ImageView imageView = new ImageView();
                    try {
                        imageView.setImage(new Image(getClass().getResourceAsStream(etudiantPhotoUrl)));
                    } catch (Exception e) {
                        imageView.setImage(
                                new Image(getClass().getResourceAsStream("/resources/images/profiles/default.png")));
                    }

                    // Set size of the image view
                    imageView.setFitWidth(24);
                    imageView.setFitHeight(24);

                    // Create a VBox to hold labels vertically
                    VBox vbox = new VBox(nameLabel, emailLabel);
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
