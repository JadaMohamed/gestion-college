package application.utilities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Callback;

import application.model.Etudiant;

public class EtudiantContactParentsCell implements
        Callback<TableColumn<Etudiant, Etudiant>, TableCell<Etudiant, Etudiant>> {

    @Override
    public TableCell<Etudiant, Etudiant> call(
            TableColumn<Etudiant, Etudiant> param) {
        return new TableCell<Etudiant, Etudiant>() {
            @Override
            protected void updateItem(Etudiant item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Extract enseignant data from the map;
                    String etudiantEmail = item.getEmailParent();
                    String etudiantTelephone = item.getTelephoneParent();
                    if ((etudiantEmail == null || etudiantEmail.contains("-"))) {
                        Label cellLabel = new Label("-");
                        setText(null);
                        setGraphic(cellLabel);
                        return;
                    }

                    // Create labels for full name and email
                    Label nameLabel = new Label(etudiantEmail);
                    Label cneLabel = new Label(etudiantTelephone);

                    // Set font weight for email label
                    cneLabel.setStyle("-fx-font-weight: lighter;-fx-font-size: 10px;-fx-text-fill: #475467;");
                    nameLabel.setStyle("-fx-font-weight: bold;-fx-font-size: 11px;");

                    // Create a VBox to hold labels vertically
                    VBox vbox = new VBox(nameLabel, cneLabel);
                    vbox.setAlignment(Pos.CENTER_LEFT);
                    // Set the HBox as the graphic of the cell
                    setGraphic(vbox);
                }
            }

        };
    }
}
