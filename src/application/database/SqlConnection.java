package application.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
    private static Connection conn;
    private static String databaseName = "gestiondecollege";
    private static String user = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/" + databaseName;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de Driver: " + e);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
