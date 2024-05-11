package application.utilities;

@FunctionalInterface
public interface ButtonClickHandler<T> {
    void handleButtonClick(T rowData);
}
