package application.utilities;

import java.util.Map;

@FunctionalInterface
public interface ButtonClickHandler {
    void handleButtonClick(Map<String, String> rowData);
}
