package application.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import application.model.TypeCours;
import application.repositories.TypeCoursRepository;

public class TypeCoursService {

    public static Vector<TypeCours> getAllTypes() {

        Vector<TypeCours> resTypeCours = new Vector<TypeCours>();
        ResultSet result;
        try {
            result = TypeCoursRepository.getAllTypes();
            while (result.next()) {
                TypeCours statTypeCours = new TypeCours();
                statTypeCours.setId(result.getInt("id"));
                statTypeCours.setNom(result.getString("nom"));
                resTypeCours.add(statTypeCours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resTypeCours;
    }
}
