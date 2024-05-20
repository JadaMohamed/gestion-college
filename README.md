# Gestion administrative d’un collège

## Membres du Groupe

- **Jada Mohamed**
- **Raidi Oualid**
- **Rifai Mohamed**

## Compilation des Programmes

Pour compiler et exécuter ce projet JavaFX, suivez les étapes ci-dessous :

### Prérequis

1. Assurez-vous d'avoir installé JDK (Java Development Kit) sur votre machine.
2. Assurez-vous d'avoir installé un IDE supportant Java (par exemple, Visual Studio Code, IntelliJ IDEA, Eclipse).

### Étapes de Compilation et d'Exécution

1. **Configuration de l'Environnement de Développement :**

   - Ouvrez Visual Studio Code (ou votre IDE préféré).
   - Assurez-vous que le répertoire racine du projet est ouvert dans l'IDE.

2. **Configuration des Paramètres :**

   - Les paramètres spécifiques au projet se trouvent dans le fichier `.vscode/settings.json`. Ce fichier définit les chemins des sources, le chemin de sortie des binaires, ainsi que les bibliothèques référencées.
   - Les configurations de lancement se trouvent dans le fichier `.vscode/launch.json`, spécifiant la classe principale et les arguments de la machine virtuelle Java.

3. **Compilation :**

   - Les fichiers sources se trouvent dans le répertoire `src`.
   - Les bibliothèques nécessaires (JavaFX et JDBC driver) sont situées dans le répertoire `libraries`.

4. **Exécution :**

   - Utilisez la configuration de lancement fournie dans `launch.json` pour exécuter l'application.
   - Alternativement, vous pouvez lancer l'application.

### Création de la Base de Données

1. Assurez-vous d'avoir installé MySQL sur votre machine.
2. Utilisez le script SQL fourni dans le répertoire `sql/database-script.sql` pour créer la base de données locale
3. Mettez à jour les informations de connexion à la base de données dans l'application si nécessaire dans `src/application/databse/SqlConnection.java`

## Description du Contenu des Répertoires

### /.vscode

Ce répertoire contient les fichiers de configuration pour Visual Studio Code :

- `settings.json` : Définit les chemins des sources, des bibliothèques et du dossier de sortie.
- `launch.json` : Contient les configurations pour lancer l'application JavaFX.

### /bin

Ce répertoire contient les fichiers binaires compilés. Après la compilation, les fichiers exécutables sont placés ici.

### /libraries

Ce répertoire contient les bibliothèques nécessaires au projet :

- `javafx-sdk-22` : Contient les fichiers JAR de JavaFX.
- `jdbc-driver` : Contient le driver JDBC pour MySQL.

### /sql

Ce répertoire contient le script SQL pour créer la base de données MySQL nécessaire au fonctionnement de l'application.

### /src

Ce répertoire contient le code source de l'application, organisé en plusieurs packages :

- `application` : Contient les classes principales et la logique de l'application.
  - `database` : Contient des classes utilitaires pour la connexion à la base de données.
  - `controllers` : Contient les contrôleurs JavaFX.
  - `model` : Contient les classes de modèle représentant les données de l'application.
  - `repositories` : Contient les classes responsables de l'accès aux données (DAO).
  - `services` : Contient les services de l'application qui implémentent la logique métier.
  - `utilities` : Contient les utilitaires et classes utilitaires diverses.

- `resources` : Contient les fichiers de ressources utilisés par l'application (les fichiers FXML, les images, les fichiers styles (css)).
