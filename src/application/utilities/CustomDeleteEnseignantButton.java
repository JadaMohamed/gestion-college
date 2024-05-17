package application.utilities;

import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class CustomDeleteEnseignantButton
        implements Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {
    private final ButtonClickHandler<Map<String, String>> buttonClickHandler;

    public CustomDeleteEnseignantButton(ButtonClickHandler<Map<String, String>> buttonClickHandler) {
        this.buttonClickHandler = buttonClickHandler;
    }

    @Override
    public TableCell<Map<String, String>, String> call(TableColumn<Map<String, String>, String> param) {
        return new TableCell<>() {
            private final Button button = new Button();

            {
                Image icon = new Image(getClass().getResourceAsStream("/resources/images/icons/trash-03.png"));
                ImageView imageView = new ImageView(icon);
                imageView.setFitWidth(16); // Set the width of the image
                imageView.setFitHeight(16); // Set the height of the image

                // Set the image as the graphic for the button
                button.setGraphic(imageView);
                button.setStyle(
                        "-fx-background-color: #E5C4C2; -fx-cursor: Hand; -fx-padding: 5px");
                button.setOnAction(event -> {
                    // Handle button click here
                    Map<String, String> rowData = getTableRow().getItem();
                    System.out.println("Button clicked. Data for row: " + rowData);

                    // Call the button click handler function
                    buttonClickHandler.handleButtonClick(rowData);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }
}
