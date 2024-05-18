SELECT 
    weekdays.weekday AS Day, 
    COALESCE(coursnom8_10, '-') AS coursnom8_10, 
    COALESCE(nomEnseignant8_10, '-') AS nomEnseignant8_10, 
    COALESCE(prenomEnseignant8_10, '-') AS prenomEnseignant8_10, 
    COALESCE(photoUrlEnseignant8_10, '-') AS photoUrlEnseignant8_10, 
    COALESCE(idEnseignant8_10, '-') AS idEnseignant8_10, 
    COALESCE(heureDebut8_10, '-') AS heureDebut8_10, 
    COALESCE(heureFin8_10, '-') AS heureFin8_10, 
    COALESCE(nomSalle8_10, '-') AS nomSalle8_10, 
    COALESCE(coursnom10_12, '-') AS coursnom10_12, 
    COALESCE(nomEnseignant10_12, '-') AS nomEnseignant10_12, 
    COALESCE(prenomEnseignant10_12, '-') AS prenomEnseignant10_12, 
    COALESCE(photoUrlEnseignant10_12, '-') AS photoUrlEnseignant10_12, 
    COALESCE(idEnseignant10_12, '-') AS idEnseignant10_12, 
    COALESCE(heureDebut10_12, '-') AS heureDebut10_12, 
    COALESCE(heureFin10_12, '-') AS heureFin10_12, 
    COALESCE(nomSalle10_12, '-') AS nomSalle10_12, 
    COALESCE(coursnom14_16, '-') AS coursnom14_16, 
    COALESCE(nomEnseignant14_16, '-') AS nomEnseignant14_16, 
    COALESCE(prenomEnseignant14_16, '-') AS prenomEnseignant14_16, 
    COALESCE(photoUrlEnseignant14_16, '-') AS photoUrlEnseignant14_16, 
    COALESCE(idEnseignant14_16, '-') AS idEnseignant14_16, 
    COALESCE(heureDebut14_16, '-') AS heureDebut14_16, 
    COALESCE(heureFin14_16, '-') AS heureFin14_16, 
    COALESCE(nomSalle14_16, '-') AS nomSalle14_16, 
    COALESCE(coursnom16_18, '-') AS coursnom16_18, 
    COALESCE(nomEnseignant16_18, '-') AS nomEnseignant16_18, 
    COALESCE(prenomEnseignant16_18, '-') AS prenomEnseignant16_18, 
    COALESCE(photoUrlEnseignant16_18, '-') AS photoUrlEnseignant16_18, 
    COALESCE(idEnseignant16_18, '-') AS idEnseignant16_18, 
    COALESCE(heureDebut16_18, '-') AS heureDebut16_18, 
    COALESCE(heureFin16_18, '-') AS heureFin16_18, 
    COALESCE(nomSalle16_18, '-') AS nomSalle16_18 
FROM 
    (SELECT 'LUNDI' AS weekday 
     UNION ALL SELECT 'MARDI' 
     UNION ALL SELECT 'MERCREDI' 
     UNION ALL SELECT 'JEUDI' 
     UNION ALL SELECT 'VENDREDI' 
     UNION ALL SELECT 'SAMEDI') AS weekdays 
LEFT JOIN 
    (SELECT 
        weekdays.weekday AS Day, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN cours.nom END) AS coursnom8_10, 
         MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.id END) AS idSeance8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.nom END) AS nomEnseignant8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.prenom END) AS prenomEnseignant8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN enseignant.id END) AS idEnseignant8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureDebut END) AS heureDebut8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN seance.heureFin END) AS heureFin8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '08:00:00' AND '09:59:00' THEN salle.nom END) AS nomSalle8_10, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN cours.nom END) AS coursnom10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.id END) AS idSeance10_12,
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.nom END) AS nomEnseignant10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.prenom END) AS prenomEnseignant10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN enseignant.id END) AS idEnseignant10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureDebut END) AS heureDebut10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN seance.heureFin END) AS heureFin10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '10:00:00' AND '11:59:00' THEN salle.nom END) AS nomSalle10_12, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN cours.nom END) AS coursnom14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.id END) AS idSeance14_16,
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.nom END) AS nomEnseignant14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.prenom END) AS prenomEnseignant14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN enseignant.id END) AS idEnseignant14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureDebut END) AS heureDebut14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN seance.heureFin END) AS heureFin14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '14:00:00' AND '15:59:00' THEN salle.nom END) AS nomSalle14_16, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN cours.nom END) AS coursnom16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.id END) AS idSeance16_18,
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.nom END) AS nomEnseignant16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.prenom END) AS prenomEnseignant16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN enseignant.photoUrl END) AS photoUrlEnseignant16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureDebut END) AS heureDebut16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN seance.heureFin END) AS heureFin16_18, 
        MAX(CASE WHEN seance.heureDebut BETWEEN '16:00:00' AND '17:59:00' THEN salle.nom END) AS nomSalle16_18 
    FROM 
        (SELECT 'LUNDI' AS weekday 
         UNION ALL SELECT 'MARDI' 
         UNION ALL SELECT 'MERCREDI' 
         UNION ALL SELECT 'JEUDI' 
         UNION ALL SELECT 'VENDREDI' 
         UNION ALL SELECT 'SAMEDI') AS weekdays 
    LEFT JOIN seance ON weekdays.weekday = seance.jour 
    LEFT JOIN cours ON seance.idCours = cours.id 
    LEFT JOIN enseignant ON enseignant.id = cours.idEnseignant 

