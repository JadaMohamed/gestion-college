-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql8.freesqldatabase.com
-- Generation Time: May 02, 2024 at 04:27 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql8703505`
--

-- --------------------------------------------------------

--
-- Table structure for table `absence`
--

CREATE TABLE `absence` (
  `id` int(11) NOT NULL,
  `idSeance` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `motif` varchar(50) NOT NULL,
  `estExcuse` tinyint(1) NOT NULL,
  `numSemaine` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `motDePass` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT '/resources/images/profiles/default.png',
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrateur`
--

INSERT INTO `administrateur` (`id`, `nom`, `prenom`, `email`, `dateNaissance`, `motDePass`, `telephone`, `photoUrl`, `role`) VALUES
(1, 'Jada', 'Mohamed', 'mohamed@jada.com', '1979-11-28', 'jada', '0679008358', '/resources/images/profiles/default.png', 'Administrateur'),
(2, 'RAIDI', 'Oualid', 'raidioualid@crestwood.com', '2003-11-25', 'raidioualid', '0620726234', '/resources/images/profiles/default.png', 'Administrateur'),
(3, 'RIFAI', 'Mohamed', 'rifaimohamed@crestwood.com', '2003-10-05', 'rifaimohamed', '0659905400', '/resources/images/profiles/default.png', 'Administrateur');

-- --------------------------------------------------------

--
-- Table structure for table `categorieSalle`
--

CREATE TABLE `categorieSalle` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorieSalle`
--

INSERT INTO `categorieSalle` (`id`, `nom`) VALUES
(1, 'Laboratoire'),
(2, 'Classe'),
(3, 'Salle de sports');

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

CREATE TABLE `classe` (
  `id` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `idNiveauClasse` int(11) NOT NULL,
  `effectif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classe`
--

INSERT INTO `classe` (`id`, `numero`, `idNiveauClasse`, `effectif`) VALUES
(20, 1, 1, 25),
(21, 1, 2, 25),
(22, 1, 3, 25),
(23, 1, 4, 25),
(24, 2, 1, 25),
(25, 2, 2, 25),
(26, 2, 3, 25),
(27, 2, 4, 25),
(28, 3, 1, 25),
(29, 3, 2, 25),
(30, 3, 3, 25),
(31, 3, 4, 25),
(32, 4, 1, 25),
(33, 4, 2, 25),
(34, 4, 3, 25),
(35, 4, 4, 25),
(36, 5, 4, 25);

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `id` int(11) NOT NULL,
  `idTypeCours` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cours`
--

INSERT INTO `cours` (`id`, `idTypeCours`, `nom`, `idEnseignant`, `idNiveau`) VALUES
(1, 2, 'Probabilités et statistique', 2, 1),
(2, 9, 'Seance de sports', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `dateNaissance` date NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT '/resources/images/profiles/default.png'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseignant`
--

INSERT INTO `enseignant` (`id`, `nom`, `prenom`, `email`, `telephone`, `dateNaissance`, `photoUrl`) VALUES
(1, 'Elmoudden', 'Hafid', 'elmouddenhafid@gmail.com', '0650776520', '1980-05-20', '/resources/images/profiles/hafid.png'),
(2, 'Khaldoune', 'aziz', 'khaldouneaziz@gmail.com', '0645665789', '1977-05-21', '/resources/images/profiles/aziz.png'),
(3, 'Ibrahim', 'Jamal', 'ibrahimjamal@gmail.com', '0655334276', '1988-03-25', '/resources/images/profiles/ibrahim.png');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `cne` varchar(10) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `telephoneParent` varchar(15) NOT NULL,
  `emailParent` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `idClasse` int(11) NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT '/resources/images/profiles/default.png'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etudiant`
--

INSERT INTO `etudiant` (`id`, `cne`, `nom`, `prenom`, `email`, `telephone`, `telephoneParent`, `emailParent`, `dateNaissance`, `idClasse`, `photoUrl`) VALUES
(0, 'T14344333', 'jarjar', 'mohamed', 'jarjarmohamed1@gmail.com', '0688443820', '0730988756', 'samirjarjar@gmail.com', '2009-11-09', 27, '/resources/images/profiles/default.png'),
(1, 'R11777656', 'alioui', 'badr', 'aliouibadr@gmail.com', '0610942867', '0625242526', 'kamalalioui1@example.com', '2009-01-01', 21, '/resources/images/profiles/default.png'),
(2, '0', 'Fathi', 'rachid', 'fathirachid0@gmai.com', '0677867665', '0678347388', 'hichamfathii@example.com', '2010-05-15', 24, '/resources/images/profiles/default.png'),
(3, '0', 'rawi', 'aya', 'rawiaya@example.com', '0655447654', '0677880698', 'karimrawi@gmail.com', '2010-10-03', 24, '/resources/images/profiles/default.png'),
(4, '0', 'fahdi', 'ayoub', 'ayoubfahdi@gmail.com', '0655127887', '0606078854', 'fahdiabdelali@gmail.com', '2008-11-01', 25, '/resources/images/profiles/default.png'),
(566, '0', 'radi', 'jalal', 'radijalal@gmail.com', '0677945619', '0676564323', 'ahmedradi@gmail.com', '2009-08-05', 21, '/resources/images/profiles/default.png'),
(567, '0', 'tantaoui', 'ali', 'alitantaouii@gmail.com', '0655507200', '0623543001', 'aziztantatoui@gmail.com', '2010-10-30', 24, '/resources/images/profiles/default.png'),
(568, '0', 'fassih', 'salah', 'fassihsalah@gmail.com', '0661203733', '0651119238', 'mohamedfassih@gmail.com', '2010-11-21', 20, '/resources/images/profiles/default.png'),
(569, '0', 'choubi', 'ali', 'alichoubi@gmail.com', '0677429986', '0692736455', 'larbichoubi@gmail.com', '2011-10-15', 26, '/resources/images/profiles/default.png'),
(570, '0', 'fadil', 'wassim', 'wassimfadil@gmail.com', '0655007654', '0650607588', 'fadilahmed@gmail.com', '2011-12-03', 26, '/resources/images/profiles/default.png'),
(572, 'R15463576', 'mehdaoui', 'akram', 'akrammehdaoui@gmail.com', '0765102093', '0651617392', 'kamalmehdaoui@gmail.com', '2010-01-22', 20, '/resources/images/profiles/default.png'),
(573, 'R87656722', 'enadi', 'ilias', 'iliasenadi@gmail.com', '0770928765', '0671716542', 'hassanenadi@gmail.com', '2009-10-04', 27, '/resources/images/profiles/default.png');

-- --------------------------------------------------------

--
-- Table structure for table `materielSalle`
--

CREATE TABLE `materielSalle` (
  `id` int(11) NOT NULL,
  `idSalle` int(11) NOT NULL,
  `nomMateriel` varchar(50) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `niveau`
--

INSERT INTO `niveau` (`id`, `nom`) VALUES
(1, '3eme'),
(2, '4eme'),
(3, '5eme'),
(4, '6eme');

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `id` int(11) NOT NULL,
  `idCategorieSalle` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `capacite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salle`
--

INSERT INTO `salle` (`id`, `idCategorieSalle`, `nom`, `capacite`) VALUES
(1, 2, 'Salle A1', 45),
(2, 3, 'Terrain de volleyball', 50),
(3, 2, 'Salle A2', 45),
(4, 1, 'Laboratoire de biologie', 35),
(5, 3, 'Terrain de Basketball', 50),
(6, 3, 'Terrain de Football', 50),
(7, 2, 'Salle B1', 45),
(8, 1, 'Laboratoire de chimie', 35),
(9, 2, 'Salle B2', 45),
(10, 2, 'Salle C1', 45),
(11, 2, 'Salle C2', 45),
(12, 2, 'Salle D1', 45),
(13, 2, 'Salle D2', 45),
(14, 2, 'Salle E1', 45),
(15, 2, 'Salle E2', 45),
(16, 2, 'Salle F1', 45),
(17, 2, 'Salle F2', 45),
(18, 2, 'Salle G1', 45),
(19, 2, 'Salle G2', 45),
(20, 2, 'Classe H1', 45),
(21, 2, 'Salle H2', 45),
(22, 2, 'Salle H3', 45);

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `id` int(11) NOT NULL,
  `idSalle` int(11) NOT NULL,
  `jour` enum('LUNDI','MARDI','MERCREDI','JEUDI','VENDREDI','SAMEDI','DIMANCHE') NOT NULL,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `idCours` int(11) NOT NULL,
  `idClasse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seance`
--

INSERT INTO `seance` (`id`, `idSalle`, `jour`, `heureDebut`, `heureFin`, `idCours`, `idClasse`) VALUES
(1, 1, 'JEUDI', '14:00:00', '16:00:00', 1, 20),
(2, 2, 'JEUDI', '14:00:00', '16:00:00', 2, 25);

-- --------------------------------------------------------

--
-- Table structure for table `typeCours`
--

CREATE TABLE `typeCours` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `typeCours`
--

INSERT INTO `typeCours` (`id`, `nom`) VALUES
(1, 'Informatique'),
(2, 'Mathématiques'),
(3, 'Sciences'),
(4, 'Géographie'),
(5, 'Éducation'),
(6, 'Économie'),
(7, 'Français'),
(8, 'Anglais'),
(9, 'Sports');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `absence_ibfk_1` (`idSeance`),
  ADD KEY `absence_ibfk_3` (`idEtudiant`);

--
-- Indexes for table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categorieSalle`
--
ALTER TABLE `categorieSalle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `classe_ibfk_1` (`idNiveauClasse`);

--
-- Indexes for table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cours_ibfk_1` (`idTypeCours`),
  ADD KEY `cours_ibfk_3` (`idEnseignant`),
  ADD KEY `cours_ibfk_4` (`idNiveau`);

--
-- Indexes for table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `etudiant_ibfk_1` (`idClasse`);

--
-- Indexes for table `materielSalle`
--
ALTER TABLE `materielSalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `materielsalle_ibfk_1` (`idSalle`);

--
-- Indexes for table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `salle_ibfk_1` (`idCategorieSalle`);

--
-- Indexes for table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `seance_ibfk_1` (`idSalle`),
  ADD KEY `seance_ibfk_3` (`idClasse`);

--
-- Indexes for table `typeCours`
--
ALTER TABLE `typeCours`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absence`
--
ALTER TABLE `absence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `categorieSalle`
--
ALTER TABLE `categorieSalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `classe`
--
ALTER TABLE `classe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=574;
--
-- AUTO_INCREMENT for table `materielSalle`
--
ALTER TABLE `materielSalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `salle`
--
ALTER TABLE `salle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `seance`
--
ALTER TABLE `seance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `typeCours`
--
ALTER TABLE `typeCours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
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
-- Constraints for table `materielSalle`
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


SELECT 
    classe.id, 
    classe.numero AS numeroClasse, 
    niveau.nom AS nomNiveau, 
    classe.effectif, 
    CASE 
        WHEN seance.id IS NOT NULL THEN 
            CASE 
                WHEN DAYOFWEEK(CURDATE()) = 
                    CASE 
                        WHEN seance.jour = 'LUNDI' THEN 2 
                        WHEN seance.jour = 'MARDI' THEN 3 
                        WHEN seance.jour = 'MERCREDI' THEN 4 
                        WHEN seance.jour = 'JEUDI' THEN 5 
                        WHEN seance.jour = 'VENDREDI' THEN 6 
                        WHEN seance.jour = 'SAMEDI' THEN 7 
                        WHEN seance.jour = 'DIMANCHE' THEN 1 
                    END 
                AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin 
                THEN 1 
                ELSE 0 
            END 
        ELSE 0 
    END AS status, 
    CASE 
        WHEN seance.id IS NOT NULL THEN 
            CASE 
                WHEN DAYOFWEEK(CURDATE()) = 
                    CASE 
                        WHEN seance.jour = 'LUNDI' THEN 2 
                        WHEN seance.jour = 'MARDI' THEN 3 
                        WHEN seance.jour = 'MERCREDI' THEN 4 
                        WHEN seance.jour = 'JEUDI' THEN 5 
                        WHEN seance.jour = 'VENDREDI' THEN 6 
                        WHEN seance.jour = 'SAMEDI' THEN 7 
                        WHEN seance.jour = 'DIMANCHE' THEN 1 
                    END 
                AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin 
                THEN salle.nom 
                ELSE '-' 
            END 
        ELSE '-' 
    END AS nomSalle, 
    CASE 
        WHEN status=0 THEN COALESCE(cours.nom, '-') 
        ELSE '-' 
    END AS nomCours,
    CASE 
        WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.nom, '-') 
        ELSE '-' 
    END AS nomEnseignant, 
    CASE 
        WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.prenom, '-') 
        ELSE '-' 
    END AS prenomEnseignant, 
    CASE 
        WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.email, '-') 
        ELSE '-' 
    END AS emailEnseignant, 
    CASE 
        WHEN seance.id IS NOT NULL THEN COALESCE(enseignant.photoUrl, '-') 
        ELSE '-' 
    END AS photoUrlEnseignant
FROM 
    classe 
LEFT JOIN 
    niveau ON niveau.id = classe.idNiveauClasse 
LEFT JOIN 
    seance ON classe.id = seance.idClasse 
LEFT JOIN 
    salle ON seance.idSalle = salle.id 
LEFT JOIN 
    cours ON cours.id = seance.idCours 
LEFT JOIN 
    enseignant ON enseignant.id = cours.idEnseignant
    AND DAYOFWEEK(CURDATE()) = 
        CASE 
            WHEN seance.jour = 'LUNDI' THEN 2 
            WHEN seance.jour = 'MARDI' THEN 3 
            WHEN seance.jour = 'MERCREDI' THEN 4 
            WHEN seance.jour = 'JEUDI' THEN 5 
            WHEN seance.jour = 'VENDREDI' THEN 6 
            WHEN seance.jour = 'SAMEDI' THEN 7 
            WHEN seance.jour = 'DIMANCHE' THEN 1 
        END 
    AND CURTIME() BETWEEN seance.heureDebut AND seance.heureFin;
