package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.util.Callback;
import java.util.Map;

public class CustomStatusCellSalle implements
        Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {

    @Override
    public TableCell<Map<String, String>, String> call(
            TableColumn<Map<String, String>, String> param) {
        return new TableCell<Map<String, String>, String>() {
            private final ImageView iconImageView = new ImageView();
            private int iconWidth = 0;

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Determine the niveau icon path based on the class name
                    String statusIconPath = "/resources/images/icons/Badge_Disponible.png";
                    ; // Path to the class level icon
                      // Adjust this logic based on your class naming convention
                    if (item.contains("1")) {

                        statusIconPath = "/resources/images/icons/Badge_Occupée.png";
                        iconWidth = 62;
                    } else {
                        statusIconPath = "/resources/images/icons/Badge_Disponible.png";
                        iconWidth = 72;
                    }
                    try {
                        // Set the icon image
                        iconImageView.setImage(new Image(getClass().getResourceAsStream(statusIconPath)));
                    } catch (Exception e) {
                        System.out.println("Error loading status icon image: " + e.getMessage());
                    }
                    iconImageView.setFitWidth(iconWidth);
                    iconImageView.setFitHeight(18);

                    // Create an HBox to hold the icon and class name horizontally
                    HBox hbox = new HBox(iconImageView);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    // Set the HBox as the graphic of the cell
                    setGraphic(hbox);
                }
            }
        };
    }
}
