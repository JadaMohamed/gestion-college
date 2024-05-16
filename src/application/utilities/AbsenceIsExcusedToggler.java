package application.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextInputDialog;
import javafx.util.Callback;
import java.util.Map;
import java.util.Optional;

import application.repositories.AbsenceRepository;

public class AbsenceIsExcusedToggler
        implements Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {
    private final ButtonClickHandler<Map<String, String>> buttonClickHandler;
    private final String timeStamp;
    private final int numSemaine;

    public AbsenceIsExcusedToggler(String timeStamp, int numSemaine,
            ButtonClickHandler<Map<String, String>> buttonClickHandler) {
        this.buttonClickHandler = buttonClickHandler;
        this.timeStamp = timeStamp;
        this.numSemaine = numSemaine;
    }

    @Override
    public TableCell<Map<String, String>, String> call(TableColumn<Map<String, String>, String> param) {
        return new TableCell<>() {
            private final Button button = new Button();

            {
                button.setOnAction(event -> {
                    // Handle button click here
                    Map<String, String> rowData = getTableRow().getItem();
                    if (rowData != null) {
                        System.out.println("Button clicked. Data for row: " + rowData);

                        Boolean haveCourse = !(rowData.get("seance_" + timeStamp + "_id") == null);
                        Boolean isPresent = !(rowData.get("seance_" + timeStamp + "_status").equals("present"));
                        Boolean estExcuse = !(Integer
                                .parseInt(rowData.get("seance_" + timeStamp + "_estExcuse")) == 0);
                        String motif;

                        if (haveCourse) {
                            int idSeance = Integer.parseInt(rowData.get("seance_" + timeStamp + "_id"));
                            System.out.println(idSeance);
                            int idEtudiant = Integer.parseInt(rowData.get("etudiant_id"));
                            System.out.println(idEtudiant);

                            if (!estExcuse && isPresent) {
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setTitle("Input Dialog");
                                dialog.setHeaderText("Enter the reason");
                                dialog.setContentText("Entrez s'il vous plait le motif:");

                                // Show the dialog and wait for the user to input text
                                Optional<String> result = dialog.showAndWait();

                                // Capture the input and assign it to the variable motif
                                motif = result.get();

                                AbsenceRepository.excuserAbsencePourEtudiant(numSemaine,
                                        idSeance,
                                        idEtudiant, motif);

                            }
                        }
                        // Call the button click handler function
                        buttonClickHandler.handleButtonClick(rowData);
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Get the current row data
                    Boolean haveCourse = false;
                    Map<String, String> rowData = getTableRow().getItem();

                    if (rowData != null) {
                        // Customize the button based on the row data
                        Boolean isPresent = !(rowData.get("seance_" + timeStamp + "_status").equals("present"));
                        // status
                        haveCourse = !(rowData.get("seance_" + timeStamp + "_id") == null);

                        if (haveCourse) {
                            Boolean estExcuse = !(Integer
                                    .parseInt(rowData.get("seance_" + timeStamp + "_estExcuse")) == 0);
                            if (!estExcuse && isPresent) {
                                button.setStyle(
                                        "-fx-background-color: #FEF3F2; -fx-cursor: Hand; -fx-padding: 5px; -fx-text-fill: #B42318");
                                button.setText("No");
                            } else {
                                // Set a different icon or appearance
                                button.setStyle(
                                        "-fx-background-color: #ECFDF3; -fx-cursor: Hand; -fx-padding: 5px; -fx-text-fill: #12B76A");
                                button.setText("Oui");
                            }
                        } else {
                            button.setStyle(
                                    "-fx-background-color: #F2F4F7; -fx-padding: 5px; -fx-text-fill: #344054");
                            button.setText("None");
                        }
                    } else {
                        button.setStyle(
                                "-fx-background-color: #F2F4F7;  -fx-padding: 5px; -fx-text-fill: #344054");
                        button.setText("None");
                    }
                    setGraphic(button);
                }
            }
        };
    }
}
