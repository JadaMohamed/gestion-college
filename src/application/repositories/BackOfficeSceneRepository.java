package application.repositories;

import application.database.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackOfficeSceneRepository {

    public static ResultSet getAdminData(int adminId) throws SQLException {
        Connection conn = SqlConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM administrateur WHERE id = ?");
        statement.setInt(1, adminId);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static void updateAdminData(int adminId, String nom, String prenom, String telephone, java.sql.Date dateNaissance) throws SQLException {
        Connection conn = SqlConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE administrateur SET nom = ?,prenom = ?,telephone = ?,dateNaissance = ? WHERE id = ?");
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, telephone);
        statement.setDate(4, dateNaissance);
        statement.setInt(5, adminId);
        statement.executeUpdate();
    }

    public static ResultSet validatePassword(int adminId, String oldPassword) throws SQLException {
        Connection conn = SqlConnection.getConnection();
        String query = "SELECT * FROM administrateur WHERE id = ? AND motDePass = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, adminId);
        statement.setString(2, oldPassword);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static void updatePassword(int adminId, String email, String newPassword) throws SQLException {
        Connection conn = SqlConnection.getConnection();
        String updateQuery = "UPDATE administrateur SET email = ?, motDePass = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(updateQuery);
        statement.setString(1, email);
        statement.setString(2, newPassword);
        statement.setInt(3, adminId);
        statement.executeUpdate();
    }
}
