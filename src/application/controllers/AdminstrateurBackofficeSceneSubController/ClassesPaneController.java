package application.controllers.AdminstrateurBackofficeSceneSubController;

import application.controllers.AdminstrateurBackofficeSceneController;
import application.services.SeanceService;

public class ClassesPaneController {
    private AdminstrateurBackofficeSceneController mainController;
    
    public ClassesPaneController(AdminstrateurBackofficeSceneController mainController) {
        this.mainController = mainController;
    
    }

    public void initialize(){
        update_emeCoursEnCours();
    }
    public void update_emeCoursEnCours(){
        int[] nombreCoursParNiveau = SeanceService.getNombreCoursParNiveau();
        mainController.setNombre3emeEnCoursText(String.valueOf(nombreCoursParNiveau[3]));
        mainController.setNombre4emeEnCoursText(String.valueOf(nombreCoursParNiveau[2]));
        mainController.setNombre5emeEnCoursText(String.valueOf(nombreCoursParNiveau[1]));
        mainController.setNombre6emeEnCoursText(String.valueOf(nombreCoursParNiveau[0]));
    }
}
