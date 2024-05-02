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

public class CustomClasseCell implements
        Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {

    @Override
    public TableCell<Map<String, String>, String> call(
            TableColumn<Map<String, String>, String> param) {
        return new TableCell<Map<String, String>, String>() {
            private final ImageView iconImageView = new ImageView();
            private final Label classNameLabel = new Label();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Determine the niveau icon path based on the class name
                    String niveauIconPath = "/resources/images/icons/3EME-xs.png"; // Default path if class name
                                                                                   // doesn't match
                    // Adjust this logic based on your class naming convention
                    if (item.contains("3eme")) {
                        niveauIconPath = "/resources/images/icons/3EME-xs.png"; // Update path for local resource
                    } else if (item.contains("4eme")) {
                        niveauIconPath = "/resources/images/icons/4EME-xs.png"; // Update path for local resource
                    } else if (item.contains("5eme")) {
                        niveauIconPath = "/resources/images/icons/5EME-xs.png"; // Update path for local resource
                    } else if (item.contains("6eme")) {
                        niveauIconPath = "/resources/images/icons/6EME-xs.png"; // Update path for local resource
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
                    classNameLabel.setText(item);

                    // Set font style for class name label
                    classNameLabel.setStyle("-fx-font-weight: bold;");

                    // Create an HBox to hold the icon and class name horizontally
                    HBox hbox = new HBox(iconImageView, classNameLabel);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    hbox.setSpacing(5); // Adjust spacing as needed

                    // Set the HBox as the graphic of the cell
                    setGraphic(hbox);
                }
            }
        };
    }
}
