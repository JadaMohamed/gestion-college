package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Callback;
import java.util.Map;

public class CustomEnseignantCoursCell implements
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
                    String coursNom = item.get("classeNom") + " : " + item.get("coursNom");
                    String salleInfo = item.get("salleNom") + " " + item.get("horaire");
                    // Create labels for full name and email
                    Label coursNomLabel = new Label(coursNom);
                    Label salleInfoLabel = new Label(salleInfo);

                    // Set font weight for email label
                    salleInfoLabel.setStyle("-fx-font-weight: lighter;-fx-font-size: 10px;-fx-text-fill: #475467;");
                    coursNomLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 11px;");
                    // Create a VBox to hold labels vertically
                    VBox vbox = new VBox(coursNomLabel, salleInfoLabel);
                    vbox.setAlignment(Pos.CENTER_LEFT);

                    // Set the HBox as the graphic of the cell
                    setGraphic(vbox);
                }
            }

        };
    }
}
