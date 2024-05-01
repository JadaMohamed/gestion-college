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

public class CustomCellFactory implements
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
                    // Extract enseignant data from the map
                    String enseignantFullName = item.get("enseignantFullName");
                    String enseignantEmail = item.get("enseignantEmail");
                    String enseignantPhotoUrl = item.get("enseignantPhotoUrl");

                    // Create labels for full name and email
                    Label nameLabel = new Label(enseignantFullName);
                    Label emailLabel = new Label(enseignantEmail);

                    // Set font weight for email label
                    emailLabel.setStyle("-fx-font-weight: lighter;-fx-font-size: 10px;-fx-text-fill: #475467;");

                    nameLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 11px;");

                    // Create an image view for the photo
                    ImageView imageView = new ImageView();
                    imageView.setImage(new Image(enseignantPhotoUrl));

                    // Set size of the image view
                    imageView.setFitWidth(24);
                    imageView.setFitHeight(24);

                    /// Create a VBox to hold labels vertically
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
