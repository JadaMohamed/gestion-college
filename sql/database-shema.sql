-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2024 at 05:37 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestiondecollege`
--

-- --------------------------------------------------------

--
-- Table structure for table `absence`
--

CREATE TABLE `absence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSeance` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `motif` varchar(50) NOT NULL,
  `estExcuse` boolean NOT NULL,
  `numSemaine` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `adsministrateur`
--

CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dateNaissance` Date NOT NULL,
  `motDePass` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT 'https://res.cloudinary.com/djjwswdo4/image/upload/v1714356966/jzg6gsepwvzpsb0rej7a_1_p57hps.png',
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `categoriesalle`
--

CREATE TABLE `categorieSalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `idNiveauClasse` int(11) NOT NULL,
  `effectif` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTypeCours` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` int(50) NOT NULL,
  `email` int(50) NOT NULL,
  `telephone` int(15) NOT NULL,
  `dateNaissance` Date NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT 'https://res.cloudinary.com/djjwswdo4/image/upload/v1714356966/jzg6gsepwvzpsb0rej7a_1_p57hps.png',
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cne` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `telephoneParent` varchar(15) NOT NULL,
  `emailParent` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `idClasse` int(11) NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT 'https://res.cloudinary.com/djjwswdo4/image/upload/v1714356966/jzg6gsepwvzpsb0rej7a_1_p57hps.png',
  PRIMARY KEY (`id`)
  
);

-- --------------------------------------------------------

--
-- Table structure for table `materielsalle`
--

CREATE TABLE `materielSalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSalle` int(11) NOT NULL,
  `nomMateriel` varchar(50) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCategorieSalle` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `capacite` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSalle` int(11) NOT NULL,
  `jour` ENUM ('LUNDI','MARDI','MERCREDI', 'JEUDI', 'VENDREDI', 'SAMEDI', 'DIMANCHE')  NOT NULL,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `idCours` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Table structure for table `typecours`
--

CREATE TABLE `typeCours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

-- Constraints for table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`idSeance`) REFERENCES `seance` (`id`),
  ADD CONSTRAINT `absence_ibfk_3` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`id`);

--
-- Constraints for table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`idNiveauClasse`) REFERENCES `niveau` (`id`);

--
-- Constraints for table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `cours_ibfk_1` FOREIGN KEY (`idTypeCours`) REFERENCES `typeCours` (`id`),
  ADD CONSTRAINT `cours_ibfk_3` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignant` (`id`),
  ADD CONSTRAINT `cours_ibfk_4` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`id`);

--
-- Constraints for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`idClasse`) REFERENCES `classe` (`id`);

--
-- Constraints for table `materielsalle`
--
ALTER TABLE `materielSalle`
  ADD CONSTRAINT `materielsalle_ibfk_1` FOREIGN KEY (`idSalle`) REFERENCES `salle` (`id`);

--
-- Constraints for table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`idCategorieSalle`) REFERENCES `categorieSalle` (`id`);

--
-- Constraints for table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`idSalle`) REFERENCES `salle` (`id`),
  ADD CONSTRAINT `seance_ibfk_3` FOREIGN KEY (`idClasse`) REFERENCES `classe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
