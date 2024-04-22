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
SET time_zone = "+00:00";


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
  `idAbsence` int(11) NOT NULL,
  `idSeance` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `motifAbsence` varchar(50) NOT NULL,
  `estAbsenceExcuse` tinyint(1) NOT NULL,
  `numSemaine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `asministrateur`
--

CREATE TABLE `asministrateur` (
  `idAdministrateur` int(11) NOT NULL,
  `nomAdministrateur` varchar(50) NOT NULL,
  `passwordAdministrateur` varchar(50) NOT NULL,
  `roleAdministrateur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `caegoriesalle`
--

CREATE TABLE `caegoriesalle` (
  `idCategorieSalle` int(11) NOT NULL,
  `nomCategorieSalle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `idClasse` int(11) NOT NULL,
  `numeroClasse` int(11) NOT NULL,
  `idNiveauClasse` int(11) NOT NULL,
  `effectifClasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `idCours` int(11) NOT NULL,
  `idTypeCours` int(11) NOT NULL,
  `nomCours` varchar(50) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `idEnseignant` int(11) NOT NULL,
  `nomEnseignant` varchar(50) NOT NULL,
  `prenomEnseignant` int(50) NOT NULL,
  `emailEnseignent` int(50) NOT NULL,
  `telephoneEnseignent` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `idEtudiant` int(11) NOT NULL,
  `nomEtudiant` varchar(50) NOT NULL,
  `prenomEtudiant` varchar(50) NOT NULL,
  `telephoneParentEtudiant` varchar(15) NOT NULL,
  `emailParentEtudiant` varchar(50) NOT NULL,
  `dateNaissanceEtudiant` date NOT NULL,
  `idClasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `materielsalle`
--

CREATE TABLE `materielsalle` (
  `idMaterielSalle` int(11) NOT NULL,
  `idSalle` int(11) NOT NULL,
  `legendeMateriel` varchar(50) NOT NULL,
  `QuantiteDisponibleMateriel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

CREATE TABLE `niveau` (
  `idNiveau` int(11) NOT NULL,
  `nomNiveau` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `idSalle` int(11) NOT NULL,
  `idCategorieSalle` int(11) NOT NULL,
  `nomSalle` varchar(50) NOT NULL,
  `capaciteSalle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `idSeance` int(11) NOT NULL,
  `idSalle` int(11) NOT NULL,
  `jourSeance` varchar(10) NOT NULL,
  `heureDebutSeance` time NOT NULL,
  `heureFinSeance` time NOT NULL,
  `idCours` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `typecours`
--

CREATE TABLE `typecours` (
  `idTypeCours` int(11) NOT NULL,
  `nomCours` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`idAbsence`),
  ADD KEY `idSeance` (`idSeance`),
  ADD KEY `idEtudiant` (`idEtudiant`);

--
-- Indexes for table `asministrateur`
--
ALTER TABLE `asministrateur`
  ADD PRIMARY KEY (`idAdministrateur`);

--
-- Indexes for table `caegoriesalle`
--
ALTER TABLE `caegoriesalle`
  ADD PRIMARY KEY (`idCategorieSalle`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`idClasse`),
  ADD KEY `idNiveauClasse` (`idNiveauClasse`);

--
-- Indexes for table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`idCours`),
  ADD KEY `idTypeCours` (`idTypeCours`),
  ADD KEY `idEnseignant` (`idEnseignant`),
  ADD KEY `idNiveau` (`idNiveau`);

--
-- Indexes for table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`idEnseignant`);

--
-- Indexes for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`idEtudiant`),
  ADD KEY `idClasse` (`idClasse`);

--
-- Indexes for table `materielsalle`
--
ALTER TABLE `materielsalle`
  ADD PRIMARY KEY (`idMaterielSalle`),
  ADD KEY `idSalle` (`idSalle`);

--
-- Indexes for table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`idNiveau`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`idSalle`),
  ADD KEY `idCategorieSalle` (`idCategorieSalle`);

--
-- Indexes for table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`idSeance`),
  ADD KEY `idSalle` (`idSalle`),
  ADD KEY `idCours` (`idCours`),
  ADD KEY `idClasse` (`idClasse`);

--
-- Indexes for table `typecours`
--
ALTER TABLE `typecours`
  ADD PRIMARY KEY (`idTypeCours`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absence`
--
ALTER TABLE `absence`
  MODIFY `idAbsence` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `asministrateur`
--
ALTER TABLE `asministrateur`
  MODIFY `idAdministrateur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `caegoriesalle`
--
ALTER TABLE `caegoriesalle`
  MODIFY `idCategorieSalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `classe`
--
ALTER TABLE `classe`
  MODIFY `idClasse` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cours`
--
ALTER TABLE `cours`
  MODIFY `idCours` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `idEnseignant` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `idEtudiant` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `materielsalle`
--
ALTER TABLE `materielsalle`
  MODIFY `idMaterielSalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `salle`
--
ALTER TABLE `salle`
  MODIFY `idSalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `seance`
--
ALTER TABLE `seance`
  MODIFY `idSeance` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `typecours`
--
ALTER TABLE `typecours`
  MODIFY `idTypeCours` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`idSeance`) REFERENCES `seance` (`idSeance`),
  ADD CONSTRAINT `absence_ibfk_2` FOREIGN KEY (`idSeance`) REFERENCES `seance` (`idSeance`),
  ADD CONSTRAINT `absence_ibfk_3` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`idEtudiant`);

--
-- Constraints for table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`idNiveauClasse`) REFERENCES `niveau` (`idNiveau`);

--
-- Constraints for table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `cours_ibfk_1` FOREIGN KEY (`idTypeCours`) REFERENCES `typecours` (`idTypeCours`),
  ADD CONSTRAINT `cours_ibfk_2` FOREIGN KEY (`idTypeCours`) REFERENCES `typecours` (`idTypeCours`),
  ADD CONSTRAINT `cours_ibfk_3` FOREIGN KEY (`idEnseignant`) REFERENCES `enseignant` (`idEnseignant`),
  ADD CONSTRAINT `cours_ibfk_4` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`idNiveau`);

--
-- Constraints for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`idClasse`) REFERENCES `classe` (`idNiveauClasse`);

--
-- Constraints for table `materielsalle`
--
ALTER TABLE `materielsalle`
  ADD CONSTRAINT `materielsalle_ibfk_1` FOREIGN KEY (`idSalle`) REFERENCES `salle` (`idSalle`);

--
-- Constraints for table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`idCategorieSalle`) REFERENCES `caegoriesalle` (`idCategorieSalle`);

--
-- Constraints for table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`idSalle`) REFERENCES `salle` (`idSalle`),
  ADD CONSTRAINT `seance_ibfk_2` FOREIGN KEY (`idCours`) REFERENCES `cours` (`idCours`),
  ADD CONSTRAINT `seance_ibfk_3` FOREIGN KEY (`idClasse`) REFERENCES `classe` (`idClasse`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
