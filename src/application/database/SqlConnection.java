package application.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
    private static Connection conn;
    private static String databaseName = "sql8702002";
    private static String hostName = "sql8.freesqldatabase.com";
    private static String port = "3306";
    private static String user = "sql8702002";
    private static String password = "dpGJ3qaTpA";
    private static String url = "jdbc:mysql://" + hostName + ":" + port + "/" + databaseName;

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
