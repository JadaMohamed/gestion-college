package application.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.SqlConnection;

public class AdministrateurRepository {

    public static int getAdminId(String email) {
        int adminId = 0;
        try {
            java.sql.Connection connectDB = SqlConnection.getConnection();
            String query = "SELECT id FROM administrateur WHERE email = ?";
            java.sql.PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                adminId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminId;
    }

    public static ResultSet getAdminData(int adminId) throws SQLException {
        java.sql.Connection conn = SqlConnection.getConnection();
        java.sql.PreparedStatement statement = conn.prepareStatement("SELECT * FROM administrateur WHERE id = ?");
        statement.setInt(1, adminId);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static void updateAdminData(int adminId, String nom, String prenom, String telephone,
            java.sql.Date dateNaissance) throws SQLException {
        java.sql.Connection conn = SqlConnection.getConnection();
        java.sql.PreparedStatement statement = conn.prepareStatement(
                "UPDATE administrateur SET nom = ?,prenom = ?,telephone = ?,dateNaissance = ? WHERE id = ?");
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setString(3, telephone);
        statement.setDate(4, dateNaissance);
        statement.setInt(5, adminId);
        statement.executeUpdate();
    }

    public static ResultSet validatePassword(int adminId, String oldPassword) throws SQLException {
        java.sql.Connection conn = SqlConnection.getConnection();
        String query = "SELECT * FROM administrateur WHERE id = ? AND motDePass = ?";
        java.sql.PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, adminId);
        statement.setString(2, oldPassword);
        ResultSet rs = statement.executeQuery();
        return rs;
    }

    public static void updatePassword(int adminId, String email, String newPassword) throws SQLException {
        java.sql.Connection conn = SqlConnection.getConnection();
        String updateQuery = "UPDATE administrateur SET email = ?, motDePass = ? WHERE id = ?";
        java.sql.PreparedStatement statement = conn.prepareStatement(updateQuery);
        statement.setString(1, email);
        statement.setString(2, newPassword);
        statement.setInt(3, adminId);
        statement.executeUpdate();
    }

    public static ResultSet getRoleUser(int idUser) throws SQLException {
        java.sql.Connection conn = SqlConnection.getConnection();
        String query = "SELECT role FROM administrateur WHERE id = ?";
        java.sql.PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,idUser);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
