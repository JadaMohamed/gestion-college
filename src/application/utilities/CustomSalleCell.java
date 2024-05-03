package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.util.Callback;
import java.util.Map;

public class CustomSalleCell implements
        Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {

    @Override
    public TableCell<Map<String, String>, String> call(
            TableColumn<Map<String, String>, String> param) {
        return new TableCell<Map<String, String>, String>() {
            private final ImageView iconImageView = new ImageView();
            private final Label salleNameLabel = new Label();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Determine the niveau icon path based on the class name
                    String niveauIconPath = "/resources/images/icons/Salle Cours.png"; // Default path if class name
                                                                                   // doesn't match
                    // Adjust this logic based on your class naming convention
                    if (item.contains("Salle")) {
                        niveauIconPath = "/resources/images/icons/Salle Cours.png"; // Update path for local resource
                    } else if (item.contains("Laboratoire")) {
                        niveauIconPath = "/resources/images/icons/Laboratoire.png"; // Update path for local resource
                    } else if (item.contains("Terrain")) {
                        niveauIconPath = "/resources/images/icons/Sport.png"; // Update path for local resource
                    }

                    try {
                        // Set the icon image
                        iconImageView.setImage(new Image(getClass().getResourceAsStream(niveauIconPath)));
                    } catch (Exception e) {
                        System.out.println("Error loading niveau icon image: " + e.getMessage());
                    }
                    iconImageView.setFitWidth(24); // Adjust the size as needed
                    iconImageView.setFitHeight(24);

                    // Set the class name text
                    salleNameLabel.setText(item);

                    // Set font style for class name label
                    salleNameLabel.setStyle("-fx-font-weight: bold;");

                    // Create an HBox to hold the icon and class name horizontally
                    HBox hbox = new HBox(iconImageView, salleNameLabel);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    hbox.setSpacing(5); // Adjust spacing as needed

                    // Set the HBox as the graphic of the cell
                    setGraphic(hbox);
                }
            }
        };
    }
}

