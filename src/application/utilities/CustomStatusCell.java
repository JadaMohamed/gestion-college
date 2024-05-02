package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.util.Callback;
import java.util.Map;

public class CustomStatusCell implements
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

                System.out.println(item);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Determine the niveau icon path based on the class name
                    String statusIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714583564/Badge_Hors_cours_ce0q9a.png";
                    ; // Path to the class level icon
                    // Adjust this logic based on your class naming convention
                    if (item.contains("1")) {

                        statusIconPath = "https://res.cloudinary.com/djjwswdo4/image/upload/v1714583567/Badge_En_cours_vfrpxz.png";
                        iconWidth = 62;
                    }
                    // Set the icon image
                    System.out.println("here");
                    iconImageView.setImage(new Image(statusIconPath));

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
