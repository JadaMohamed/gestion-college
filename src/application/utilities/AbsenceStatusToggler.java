package application.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.util.Map;

import application.repositories.AbsenceRepository;

public class AbsenceStatusToggler
        implements Callback<TableColumn<Map<String, String>, String>, TableCell<Map<String, String>, String>> {
    private final ButtonClickHandler<Map<String, String>> buttonClickHandler;
    private final String timeStamp;
    private final int numSemaine;

    public AbsenceStatusToggler(String timeStamp, int numSemaine,
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
                        if (haveCourse) {
                            int idSeance = Integer.parseInt(rowData.get("seance_" + timeStamp + "_id"));
                            System.out.println(idSeance);
                            int idEtudiant = Integer.parseInt(rowData.get("etudiant_id"));
                            System.out.println(idEtudiant);
                            if (rowData.get("seance_" + timeStamp + "_status").equals("absent")) {
                                AbsenceRepository.supprimerAbsencePourEtudiant(numSemaine,
                                        idSeance,
                                        idEtudiant);
                            } else if (rowData.get("seance_" + timeStamp + "_status").equals("present")) {
                                AbsenceRepository.ajouterAbsencePourEtudiant(numSemaine,
                                        idSeance,
                                        idEtudiant);
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
                        String status = rowData.get("seance_" + timeStamp + "_status"); // Example key to get the status
                        haveCourse = !(rowData.get("seance_" + timeStamp + "_id") == null);
                        if (haveCourse) {
                            if ("absent".equals(status)) {
                                button.setStyle(
                                        "-fx-background-color: #FEF3F2; -fx-cursor: Hand; -fx-padding: 5px; -fx-text-fill: #B42318");
                                button.setText("Absent");
                            } else {
                                // Set a different icon or appearance
                                button.setStyle(
                                        "-fx-background-color: #ECFDF3; -fx-cursor: Hand; -fx-padding: 5px; -fx-text-fill: #12B76A");
                                button.setText("Present");
                            }
                        } else {
                            button.setStyle(
                                    "-fx-background-color: #F2F4F7; -fx-padding: 5px; -fx-text-fill: #344054");
                            button.setText("Hors cours");
                        }
                    } else {
                        button.setStyle(
                                "-fx-background-color: #F2F4F7; -fx-padding: 5px; -fx-text-fill: #344054");
                        button.setText("Hors cours");
                    }
                    setGraphic(button);
                }
            }
        };
    }
}
