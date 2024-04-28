package application.repositories;

import java.sql.ResultSet;

import application.database.SqlConnection;

public class LoginRepository {
    public static int validateLogin(String email,String password){
        int count = 0;
        try {
            java.sql.Connection connectDB = SqlConnection.getConnection();
            String verifyLogin = "SELECT count(1) FROM administrateur WHERE email = ? AND motDePass = ? ";
            java.sql.PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                count = queryResult.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

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
}
