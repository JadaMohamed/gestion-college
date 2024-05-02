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
                    String niveauIconPath = ""; // Path to the class level icon
                    // Adjust this logic based on your class naming convention
                    if (item.contains("3em")) {
                        niveauIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714582826/3EME-xs_doghwa.png";
                    } else if (item.contains("4eme")) {
                        niveauIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714582825/4EME-xs_cxhfov.png";

                    } else if (item.contains("5eme")) {
                        niveauIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714582826/5EME-xs_b1oqla.png";
                    } // Add more conditions for other class levels
                    else if (item.contains("6eme")) {
                        niveauIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714582826/6EME-xs_gzo58p.png";
                    } else {
                        niveauIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714582826/3EME-xs_doghwa.png";
                    }
                    try {
                        // Set the icon image
                        iconImageView.setImage(new Image(niveauIconPath));
                    } catch (Exception e) {
                        System.out.println("error from here 2");
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
