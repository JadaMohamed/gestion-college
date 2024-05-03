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

public class ET1618 implements
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
                    // Extract enseignant data from the map;
                    String enseignantFullName = item.get("enseignantFullName16_18");
                    String enseignantPhotoUrl = item.get("photoUrlEnseignant16_18");
                    String coursNom = item.get("coursnom16_18");
                    String schedualesInfos = item.get("nomSalle16_18") + ": 16:00 - 18:00";
                    if ((enseignantFullName == null || enseignantFullName.contains("-"))) {
                        Label cellLabel = new Label("");
                        setText(null);
                        setGraphic(cellLabel);
                        return;
                    }

                    // Create labels for full name and email
                    Label nameLabel = new Label(enseignantFullName);
                    Label coursLabel = new Label(coursNom);
                    Label schedulesLabel = new Label(schedualesInfos);
                    coursLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
                    // Set font weight for email label
                    nameLabel.setStyle("-fx-font-weight: lighter; -fx-font-size: 10px; -fx-text-fill: #475467;");
                    schedulesLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #475467;");
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
                    VBox vbox = new VBox(coursLabel, nameLabel, schedulesLabel);
                    vbox.setAlignment(Pos.CENTER_LEFT);

                    // Create an HBox to hold the image and VBox vertically centered
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(imageView, vbox);
                    hbox.setSpacing(10);
                    hbox.setAlignment(Pos.TOP_LEFT);
                    hbox.setStyle(
                            "-fx-background-color: #F4EBFF; -fx-background-radius: 4px ; -fx-border-radius: 4px; -fx-border-color: #7F56D9; -fx-padding: 5px; -fx-border-width: 0 0 0 3");
                    // Set the HBox as the graphic of the cell
                    setGraphic(hbox);
                }
            }
        };
    }

}
