package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.repositories.AdministrateurRepository;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdministrateurService {

    public static void loadAdminData(int currentAdminId, TextField nomFieldInfos, TextField prenomFieldInfos,
            TextField numFieldInfos,
            DatePicker datePickerInfos, TextField emailFieldSecurity) {
        try {
            ResultSet rs = AdministrateurRepository.getAdminData(currentAdminId);
            if (rs.next()) {
                nomFieldInfos.setText(rs.getString("nom"));
                prenomFieldInfos.setText(rs.getString("prenom"));
                numFieldInfos.setText(rs.getString("telephone"));
                datePickerInfos.setValue(rs.getDate("dateNaissance").toLocalDate());
                emailFieldSecurity.setText(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateAdminData(int adminId, String nom, String prenom, String telephone,
            java.sql.Date dateNaissance) {
        try {
            AdministrateurRepository.updateAdminData(adminId, nom, prenom, telephone, dateNaissance);
            return true; // If the update is successful, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // If there's an exception, return false
        }
    }

    public static boolean updatePassword(int adminId, String oldPassword, String newPassword, String email) {
        try {
            // Validate the old password
            ResultSet rs = AdministrateurRepository.validatePassword(adminId, oldPassword);
            if (rs.next()) {
                // If the old password is correct, update the password
                AdministrateurRepository.updatePassword(adminId, email, newPassword);
                return true; // Password update successful
            } else {
                // If the old password is incorrect, display an error message
                return false; // Password update failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
            return false; // Password update failed
        }
    }

    public static String getNameAdminById(int adminId){
        String adminName = null;
        try{
            ResultSet rs = AdministrateurRepository.getAdminData(adminId);
            if(rs.next()){
                adminName = rs.getString("nom");
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        return adminName;
    }

    public static String getUrlPhotoAdminById(int adminId){
        String adminUrlPhoto = null;
        try{
            ResultSet rs = AdministrateurRepository.getAdminData(adminId);
            if(rs.next()){
                adminUrlPhoto = rs.getString("photoUrl");
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        return adminUrlPhoto;
    }

    public static String getRoleUser(int adminId) throws SQLException {
        ResultSet rs = AdministrateurRepository.getRoleUser(adminId);
        if (rs.next()) {
            return rs.getString("role");
        }
        return null;
    }
}
