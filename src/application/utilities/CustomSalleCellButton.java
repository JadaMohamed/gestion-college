package application.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import java.util.Map;

public class CustomSalleCellButton
        implements Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {
    private final Pane activeSallePane;
    private final ButtonClickHandler<Map<String, String>> buttonClickHandler;

    public CustomSalleCellButton(Pane activeSallePane, ButtonClickHandler<Map<String, String>> buttonClickHandler) {
        this.activeSallePane = activeSallePane;
        this.buttonClickHandler = buttonClickHandler;
    }

    @Override
    public TableCell<Map<String, String>, String> call(TableColumn<Map<String, String>, String> param) {
        return new TableCell<>() {
            private final Button button = new Button("Voir");

            {
                button.setStyle(
                        "-fx-font-weight: bold; -fx-text-fill: #6941c6; -fx-background-color: #f6efff; -fx-cursor: Hand");
                button.setOnAction(event -> {
                    // Handle button click here
                    Map<String, String> rowData = getTableRow().getItem();
                    System.out.println("Button clicked. Data for row: " + rowData);

                    // Call the button click handler function
                    buttonClickHandler.handleButtonClick(rowData);

                    // Bring the activeClassePane to front
                    activeSallePane.toFront();
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
