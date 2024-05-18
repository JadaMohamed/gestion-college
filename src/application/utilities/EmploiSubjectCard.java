package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

public class EmploiSubjectCard implements
        Callback<TableColumn<Map<String, String>, Map<String, String>>, TableCell<Map<String, String>, Map<String, String>>> {
    final private String timeStamp;
    final private ButtonClickHandler<Map<String, String>> buttonClickHandler;

    public EmploiSubjectCard(String timeStamp, ButtonClickHandler<Map<String, String>> buttonClickHandler) {
        this.timeStamp = timeStamp;
        this.buttonClickHandler = buttonClickHandler;
    }

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

                    Map<String, String> timeStapmsPresentation = new HashMap<>();
                    timeStapmsPresentation.put("8_10", "08:00 - 10:00");
                    timeStapmsPresentation.put("10_12", "10:00 - 12:00");
                    timeStapmsPresentation.put("14_16", "14:00 - 16:00");
                    timeStapmsPresentation.put("16_18", "16:00 - 18:00");
                    // Extract enseignant data from the map;
                    String enseignantFullName = item.get("enseignantFullName" + timeStamp);
                    String enseignantPhotoUrl = item.get("photoUrlEnseignant" + timeStamp);
                    String coursNom = item.get("coursnom" + timeStamp);
                    String schedualesInfos = item.get("nomSalle" + timeStamp) + " "
                            + timeStapmsPresentation.get(timeStamp);
                    if ((enseignantFullName == null || enseignantFullName.contains("-"))) {
                        HBox hbox = new HBox();
                        setText(null);
                        hbox.setPrefHeight(56);
                        setGraphic(hbox);
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
                    Button button = new Button();
                    // Create a VBox to hold labels vertically
                    VBox vbox = new VBox(coursLabel, nameLabel, schedulesLabel);
                    vbox.setAlignment(Pos.CENTER_LEFT);
                    VBox vboxLeft = new VBox(imageView, button);
                    vboxLeft.setSpacing(7);
                    Image icon = new Image(
                            getClass().getResourceAsStream("/resources/images/icons/eye.png"));
                    ImageView leftImageView = new ImageView(icon);
                    leftImageView.setFitWidth(16); // Set the width of the image
                    leftImageView.setFitHeight(16); // Set the height of the image
                    button.setGraphic(leftImageView);
                    button.setStyle(
                            " -fx-padding: 3px 5px 3px 5px ;  -fx-background-color: #DDC7FA ; -fx-cursor: Hand");
                    button.setOnAction(event -> {
                        // Handle button click here
                        Map<String, String> rowData = getTableRow().getItem();
                        rowData.put("activeSeanceId", rowData.get("seanceId" + timeStamp));
                        System.out.print(rowData);
                        // Call the button click handler function
                        buttonClickHandler.handleButtonClick(rowData);

                    });
                    // Create an HBox to hold the image and VBox vertically centered
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(vboxLeft, vbox);
                    hbox.setSpacing(5);
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
