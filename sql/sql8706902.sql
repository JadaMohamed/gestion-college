-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql8.freesqldatabase.com
-- Generation Time: May 17, 2024 at 09:29 PM
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
-- Database: `sql8706902`
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
(1, 'Jada', 'Mohamed', 'mohamed@jada.com', '2002-07-19', 'jada', '0679008358', '/resources/images/profiles/default.png', 'Administrateur'),
(2, 'RAIDI', 'Oualid', 'raidioualid@crestwood.com', '2003-11-25', 'raidioualid', '0620726234', '/resources/images/profiles/default.png', 'Administrateur'),
(3, 'RIFAI', 'Mohamed', 'mohamed@com', '2003-10-05', 'rifai', '0659905400', '/resources/images/profiles/default.png', 'Administrateur'),
(4, 'Fadil', 'Youssef', 'f@cwd.com', '2003-07-22', '123', '0670861002', '/resources/images/profiles/default.png', 'vie scolaire');

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
(20, 1, 1, 15),
(21, 1, 2, 15),
(22, 1, 3, 15),
(23, 1, 4, 15),
(24, 2, 1, 15),
(25, 2, 2, 15),
(26, 2, 3, 15),
(27, 2, 4, 15),
(28, 3, 1, 15),
(29, 3, 2, 15),
(30, 3, 3, 15),
(31, 3, 4, 15),
(32, 4, 1, 15),
(33, 4, 2, 15),
(34, 4, 3, 15),
(35, 4, 4, 15),
(36, 5, 4, 15);

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
(13, 1, 'Algorithmique 1', 1, 3),
(14, 1, 'Language C', 1, 4),
(15, 2, 'Opérations sur les Nombres', 2, 1),
(16, 2, 'Géomètrie dans le plan', 2, 1),
(17, 2, 'Algèbre Élémentaire', 2, 1),
(18, 2, 'Les nombres rationnels', 3, 2),
(19, 2, 'Les triangles', 3, 2),
(20, 2, 'Équations, Ordre et Statistiques', 3, 2),
(21, 1, 'POO', 1, 1),
(22, 2, 'Analyse numerique', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `sexe` enum('M','F') NOT NULL DEFAULT 'M',
  `email` varchar(50) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `dateNaissance` date NOT NULL,
  `photoUrl` varchar(250) NOT NULL DEFAULT '/resources/images/profiles/default.png'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseignant`
--

INSERT INTO `enseignant` (`id`, `nom`, `prenom`, `sexe`, `email`, `telephone`, `dateNaissance`, `photoUrl`) VALUES
(1, 'Ibrahimi', 'Hafid', 'M', 'ibrahimihafid@gmail.com', '0650776520', '1980-05-21', '/resources/images/profiles/hafid.png'),
(2, 'Khaldoune', 'aziz', 'M', 'khaldouneaziz@gmail.com', '0645665789', '1977-05-21', '/resources/images/profiles/aziz.png'),
(3, 'Ibrahim', 'Jamal', 'M', 'ibrahimjamal@gmail.com', '0655334276', '1988-03-25', '/resources/images/profiles/ibrahim.png'),
(6, 'Haddad', 'Moulay', 'M', 'moulay.haddad@gmail.com', '0612345678', '1975-01-15', '/resources/images/profiles/haddad.png'),
(7, 'Benbrahim', 'Nadia', 'F', 'nadia.benbrahim@gmail.com', '0623456789', '1980-02-20', '/resources/images/profiles/benbrahim.png'),
(8, 'El Kadi', 'Youssef', 'M', 'youssef.elkadi@gmail.com', '0634567890', '1985-03-25', '/resources/images/profiles/elkadi.png'),
(9, 'Rachidi', 'Fatima', 'F', 'fatima.rachidi@gmail.com', '0645678901', '1990-04-30', '/resources/images/profiles/rachidi.png'),
(10, 'Ouazzani', 'Karim', 'M', 'karim.ouazzani@gmail.com', '0656789012', '1982-05-05', '/resources/images/profiles/ouazzani.png'),
(11, 'El Idrissi', 'Sara', 'F', 'sara.elidrissi@gmail.com', '0667890123', '1987-06-10', '/resources/images/profiles/elidrissi.png'),
(12, 'Mouhoub', 'Hassan', 'M', 'hassan.mouhoub@gmail.com', '0678901234', '1978-07-15', '/resources/images/profiles/mouhoub.png'),
(13, 'El Fadil', 'Leila', 'F', 'leila.elfadil@gmail.com', '0689012345', '1983-08-20', '/resources/images/profiles/elfadil.png'),
(14, 'Tahiri', 'Omar', 'M', 'omar.tahiri@gmail.com', '0690123456', '1988-09-25', '/resources/images/profiles/tahiri.png');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `cne` varchar(10) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` enum('M','F') NOT NULL,
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

INSERT INTO `etudiant` (`id`, `cne`, `nom`, `prenom`, `sexe`, `email`, `telephone`, `telephoneParent`, `emailParent`, `dateNaissance`, `idClasse`, `photoUrl`) VALUES
(564, 'R11777724', 'Berrada', 'Karim', 'M', 'karimberrada@gmail.com', '0654321098', '0643210987', 'berrada.karim@example.com', '2008-03-15', 20, '/resources/images/profiles/default.png'),
(565, 'R11777656', 'Alioui', 'Badr', 'M', 'aliouibadr@gmail.com', '0610942867', '0625242526', 'kamalalioui1@example.com', '2009-01-01', 20, '/resources/images/profiles/hafid.png'),
(566, 'R18877675', 'rawi', 'aya', 'F', 'rawiaya@example.com', '0655447654', '0677880698', 'karimrawi@gmail.com', '2010-10-03', 20, '/resources/images/profiles/hafid.png'),
(567, 'T28765560', 'fahdi', 'ayoub', 'M', 'ayoubfahdi@gmail.com', '0655127887', '0606078854', 'fahdiabdelali@gmail.com', '2008-11-01', 20, '/resources/images/profiles/hafid.png'),
(568, 'R120107883', 'radi', 'jalal', 'M', 'radijalal@gmail.com', '0677945619', '0676564323', 'ahmedradi@gmail.com', '2009-08-05', 20, '/resources/images/profiles/hafid.png'),
(569, 'J44533251', 'tantaoui', 'ali', 'M', 'alitantaouii@gmail.com', '0655507200', '0623543001', 'aziztantatoui@gmail.com', '2010-10-30', 20, '/resources/images/profiles/hafid.png'),
(570, 'R19287677', 'Fassih', 'salah', 'M', 'fassihsalah@gmail.com', '0661203733', '0651119238', 'mohamedfassih@gmail.com', '2010-11-21', 20, '/resources/images/profiles/hafid.png'),
(571, 'R21987282', 'Choubi', 'Ali', 'M', 'alichoubi@gmail.com', '0677429986', '0692736455', 'larbichoubi@gmail.com', '2011-10-15', 20, '/resources/images/profiles/hafid.png'),
(572, 'T2522873', 'fadil', 'wassim', 'M', 'wassimfadil@gmail.com', '0655007654', '0650607588', 'fadilahmed@gmail.com', '2011-12-03', 20, '/resources/images/profiles/hafid.png'),
(573, 'R15463576', 'Mehdaoui', 'Akram', 'M', 'akrammehdaoui@gmail.com', '0765102093', '0651617392', 'kamalmehdaoui@gmail.com', '2010-01-22', 20, '/resources/images/profiles/hafid.png'),
(574, 'R87656722', 'enadi', 'ilias', 'M', 'iliasenadi@gmail.com', '0770928765', '0671716542', 'hassanenadi@gmail.com', '2009-10-04', 20, '/resources/images/profiles/hafid.png'),
(575, 'X839473438', 'Jada', 'Mohamed', 'M', 'jadamohamed@gmail.com', '0639483943', '06389383672', 'hassanjada@gmail.com', '1979-11-28', 20, '/resources/images/profiles/default.png'),
(576, 'R4567890', 'Bouzidi', 'Fatima', 'F', 'fatima.bouzidi@gmail.com', '0612345679', '0612345679', 'bouzidiali@gmail.com', '2010-09-20', 20, '/resources/images/profiles/hafid.png'),
(577, 'R45678901', 'Hassan', 'Karim', 'M', 'karim.hassan@gmail.com', '0612345680', '0612345680', 'hakimhassan@gmail.com', '2011-11-30', 20, '/resources/images/profiles/hafid.png'),
(578, 'T6789012', 'Ahmed', 'Amina', 'F', 'amina.ahmed@gmail.com', '0612345681', '0612345681', 'fahdahmed@gmail.com', '2009-07-25', 20, '/resources/images/profiles/hafid.png'),
(579, 'R87654321', 'Zouhir', 'Youssef', 'M', 'youssef.zouhir@gmail.com', '0612345677', '0612345677', 'khalidzouhir@gmail.com', '2011-03-10', 21, '/resources/images/profiles/hafid.png'),
(580, 'J38039274', 'Afour', 'Rachid', 'M', 'rachid@gmail.com', '0635282733', '0533342927', 'mohamed@gmail.com', '2024-05-16', 21, '/resources/images/profiles/default.png'),
(581, 'X000000000', 'Someone', 'Felan', 'F', 'test@email.com', '0600000000', '0600000000', 'testparent@email.com', '2024-05-10', 21, '/resources/images/profiles/default.png'),
(582, 'D274746373', 'Raidi', 'Oualid', 'M', 'oualid@email.com', '0637339839', '0638388489', 'prentoualid@email.com', '2024-05-17', 21, '/resources/images/profiles/default.png'),
(583, 'T14344333', 'Jarjar', 'Mohamed', 'M', 'jarjarmohamed@gmail.com', '0688443820', '0730988756', 'samirjarjar@gmail.com', '2009-11-09', 21, '/resources/images/profiles/hafid.png'),
(584, 'R11777656', 'Alioui', 'Badr', 'M', 'aliouibadr@gmail.com', '0610942867', '0625242526', 'kamalalioui1@example.com', '2009-01-01', 21, '/resources/images/profiles/default.png'),
(585, 'R11777657', 'Bouzidi', 'Sara', 'F', 'sarabouzidi@gmail.com', '0654321098', '0643210987', 'kamalbouzidi1@example.com', '2008-03-15', 21, '/resources/images/profiles/default.png'),
(586, 'R11777658', 'Hamdi', 'Youssef', 'M', 'youssefhamdi@gmail.com', '0678901234', '0689012345', 'hamdiyoussef1@example.com', '2007-07-23', 21, '/resources/images/profiles/default.png'),
(587, 'R11777659', 'Amrani', 'Fatima', 'F', 'fatimaamrani@gmail.com', '0698765432', '0687654321', 'amranifatima1@example.com', '2006-11-04', 21, '/resources/images/profiles/default.png'),
(588, 'R11777660', 'El Moussaoui', 'Ahmed', 'M', 'ahmedelmoussaoui@gmail.com', '0643210987', '0654321098', 'moussaoui.ahmed1@example.com', '2005-09-09', 21, '/resources/images/profiles/default.png'),
(589, 'R11777661', 'Tazi', 'Leila', 'F', 'leilatazi@gmail.com', '0623456789', '0634567890', 'tazileila1@example.com', '2004-12-12', 21, '/resources/images/profiles/default.png'),
(590, 'R11777657', 'Bouzidi', 'Sara', 'F', 'sarabouzidi@gmail.com', '0654321098', '0643210987', 'kamalbouzidi1@example.com', '2008-03-15', 22, '/resources/images/profiles/default.png'),
(591, 'R11777658', 'Hamdi', 'Youssef', 'M', 'youssefhamdi@gmail.com', '0678901234', '0689012345', 'hamdiyoussef1@example.com', '2007-07-23', 22, '/resources/images/profiles/default.png'),
(592, 'R11777659', 'Amrani', 'Fatima', 'F', 'fatimaamrani@gmail.com', '0698765432', '0687654321', 'amranifatima1@example.com', '2006-11-04', 22, '/resources/images/profiles/default.png'),
(593, 'R11777660', 'El Moussaoui', 'Ahmed', 'M', 'ahmedelmoussaoui@gmail.com', '0643210987', '0654321098', 'moussaoui.ahmed1@example.com', '2005-09-09', 22, '/resources/images/profiles/default.png'),
(595, 'R11777662', 'Zouhairi', 'Yassin', 'M', 'yassinzouhairi@gmail.com', '0645678901', '0656789012', 'zouhairiyassin1@example.com', '2003-05-27', 22, '/resources/images/profiles/default.png'),
(596, 'R11777663', 'Cherkaoui', 'Nadia', 'F', 'nadiacherkaoui@gmail.com', '0678901234', '0689012345', 'cherkaouinadia1@example.com', '2002-08-31', 22, '/resources/images/profiles/default.png'),
(597, 'R11777664', 'El Kassimi', 'Karim', 'M', 'karimelkassimi@gmail.com', '0667890123', '0678901234', 'elkassimikarim1@example.com', '2001-11-03', 22, '/resources/images/profiles/default.png'),
(598, 'R11777665', 'Boulahya', 'Fatiha', 'F', 'fatihaboulahya@gmail.com', '0623456789', '0634567890', 'boulahyafatiha1@example.com', '2000-04-25', 22, '/resources/images/profiles/default.png'),
(599, 'R11777666', 'El Ouahabi', 'Hassan', 'M', 'hassanelouahabi@gmail.com', '0645678901', '0656789012', 'elouahabihassan1@example.com', '1999-02-17', 22, '/resources/images/profiles/default.png'),
(600, 'R11777667', 'Mazouz', 'Sofia', 'F', 'sofiamazouz@gmail.com', '0690123456', '0678901234', 'mazouzsofia1@example.com', '1998-06-29', 22, '/resources/images/profiles/default.png'),
(601, 'R11777668', 'El Mernissi', 'Mounir', 'M', 'mounirelmernissi@gmail.com', '0623456789', '0634567890', 'elmernissimounir1@example.com', '1997-09-08', 22, '/resources/images/profiles/default.png'),
(602, 'R11777669', 'Fahmi', 'Houda', 'F', 'houdafahmi@gmail.com', '0656789012', '0645678901', 'fahmihouda1@example.com', '1996-12-11', 22, '/resources/images/profiles/default.png'),
(603, 'R11777670', 'Benslimane', 'Omar', 'M', 'omarbenslimane@gmail.com', '0678901234', '0690123456', 'benslimaneomar1@example.com', '1995-10-23', 22, '/resources/images/profiles/default.png'),
(604, 'R11777671', 'El Haq', 'Amina', 'F', 'aminaelhaq@gmail.com', '0645678901', '0656789012', 'elhaqamina1@example.com', '1994-08-15', 22, '/resources/images/profiles/default.png'),
(605, 'R11777672', 'Benkirane', 'Mehdi', 'M', 'mehdibenkirane@gmail.com', '0623456789', '0634567890', 'benkirane.mehdi1@example.com', '1993-04-07', 22, '/resources/images/profiles/default.png'),
(606, 'R11777673', 'El Ayoubi', 'Fatima Zahra', 'F', 'fatimazahraelayoubi@gmail.com', '0678901234', '0690123456', 'elayoubifatimazahra1@example.com', '1992-02-29', 21, '/resources/images/profiles/default.png'),
(607, 'R11777674', 'El Amrani', 'Ahmed', 'M', 'ahmedelamrani@gmail.com', '0645678901', '0656789012', 'elamrani.ahmed1@example.com', '1991-07-21', 23, '/resources/images/profiles/default.png'),
(608, 'R11777675', 'El Malki', 'Nadia', 'F', 'nadiaelmalki@gmail.com', '0623456789', '0634567890', 'elmalkinadia1@example.com', '1990-09-03', 23, '/resources/images/profiles/default.png'),
(609, 'R11777676', 'Bouazzaoui', 'Khalid', 'M', 'khalidbouazzaoui@gmail.com', '0656789012', '0645678901', 'bouazzaouikhalid1@example.com', '1989-12-15', 23, '/resources/images/profiles/default.png'),
(610, 'R11777677', 'Boukhriss', 'Souad', 'F', 'souadboukhriss@gmail.com', '0690123456', '0678901234', 'boukhrisssouad1@example.com', '1988-06-27', 23, '/resources/images/profiles/default.png'),
(611, 'R11777678', 'El Khattabi', 'Abdelkader', 'M', 'abdelkaderelkhattabi@gmail.com', '0645678901', '0656789012', 'elkhattabiabdelkader1@example.com', '1987-02-18', 23, '/resources/images/profiles/default.png'),
(612, 'R11777679', 'El Moudden', 'Amina', 'F', 'aminaelmoudden@gmail.com', '0623456789', '0634567890', 'elmouddenamina1@example.com', '1986-11-10', 23, '/resources/images/profiles/default.png'),
(613, 'R11777680', 'Abdullah', 'Yasmine', 'F', 'yasmineabdullah@gmail.com', '0654321098', '0643210987', 'abdullah.yasmine@example.com', '2008-03-15', 23, '/resources/images/profiles/default.png'),
(614, 'R11777681', 'Hassan', 'Omar', 'M', 'omarhassan@gmail.com', '0678901234', '0689012345', 'hassan.omar@example.com', '2007-07-23', 23, '/resources/images/profiles/default.png'),
(615, 'R11777682', 'El Mansouri', 'Nour', 'F', 'nourelmansouri@gmail.com', '0698765432', '0687654321', 'elmansouri.nour@example.com', '2006-11-04', 23, '/resources/images/profiles/default.png'),
(616, 'R11777683', 'Mehdi', 'Amine', 'M', 'aminemehdi@gmail.com', '0643210987', '0654321098', 'mehdi.amine@example.com', '2005-09-09', 23, '/resources/images/profiles/default.png'),
(617, 'R11777684', 'Benali', 'Yassin', 'M', 'yassinbenali@gmail.com', '0623456789', '0634567890', 'benali.yassin@example.com', '2004-12-12', 23, '/resources/images/profiles/default.png'),
(618, 'R11777685', 'Touil', 'Sana', 'F', 'sanatouil@gmail.com', '0645678901', '0656789012', 'touil.sana@example.com', '2003-05-27', 23, '/resources/images/profiles/default.png'),
(619, 'R11777686', 'El Fassi', 'Omar', 'M', 'omarelfassi@gmail.com', '0678901234', '0689012345', 'elfassi.omar@example.com', '2002-08-31', 23, '/resources/images/profiles/default.png'),
(620, 'R11777687', 'Bella', 'Nada', 'F', 'nadabella@gmail.com', '0667890123', '0678901234', 'bella.nada@example.com', '2001-11-03', 23, '/resources/images/profiles/default.png'),
(621, 'R11777688', 'Rahmani', 'Karim', 'M', 'karimrahmani@gmail.com', '0623456789', '0634567890', 'rahmani.karim@example.com', '2000-04-25', 23, '/resources/images/profiles/default.png'),
(622, 'R11777689', 'El Haddadi', 'Hiba', 'F', 'hiba.elhaddadi@gmail.com', '0645678901', '0656789012', 'elhaddadi.hiba@example.com', '1999-02-17', 24, '/resources/images/profiles/default.png'),
(623, 'R11777690', 'El Idrissi', 'Sofia', 'F', 'sofiaelidrissi@gmail.com', '0690123456', '0678901234', 'elidrissi.sofia@example.com', '1998-06-29', 24, '/resources/images/profiles/default.png'),
(624, 'R11777691', 'El Hachimi', 'Ayoub', 'M', 'ayoubelhachimi@gmail.com', '0623456789', '0634567890', 'elhachimi.ayoub@example.com', '1997-09-08', 24, '/resources/images/profiles/default.png'),
(625, 'R11777692', 'Fassi Fihri', 'Salma', 'F', 'salmafassifihri@gmail.com', '0656789012', '0645678901', 'fassifihri.salma@example.com', '1996-12-11', 24, '/resources/images/profiles/default.png'),
(626, 'R11777693', 'Hilali', 'Imad', 'M', 'imadhilali@gmail.com', '0678901234', '0690123456', 'hilali.imad@example.com', '1995-10-23', 24, '/resources/images/profiles/default.png'),
(627, 'R11777694', 'El Mrini', 'Fatima', 'F', 'fatimaelmrini@gmail.com', '0645678901', '0656789012', 'elmrini.fatima@example.com', '1994-08-15', 24, '/resources/images/profiles/default.png'),
(628, 'R11777695', 'Bouchta', 'Mehdi', 'M', 'mehdibouchta@gmail.com', '0623456789', '0634567890', 'bouchta.mehdi@example.com', '1993-04-07', 24, '/resources/images/profiles/default.png'),
(629, 'R11777696', 'Tazi', 'Rania', 'F', 'raniatazi@gmail.com', '0678901234', '0690123456', 'tazi.rania@example.com', '1992-02-29', 24, '/resources/images/profiles/default.png'),
(630, 'R11777697', 'El Haouzi', 'Khalid', 'M', 'khalidelhaouzi@gmail.com', '0645678901', '0656789012', 'elhaouzi.khalid@example.com', '1991-07-21', 24, '/resources/images/profiles/default.png'),
(631, 'R11777698', 'ECharqawi', 'Nora', 'F', 'noraechcharqawi@gmail.com', '0623456789', '0634567890', 'echcharqawi.nora@example.com', '1990-09-03', 24, '/resources/images/profiles/default.png'),
(632, 'R11777699', 'El Kettani', 'Ahmed', 'M', 'ahmedelkettani@gmail.com', '0656789012', '0645678901', 'elkettani.ahmed@example.com', '1989-12-15', 24, '/resources/images/profiles/default.png'),
(633, 'R11777700', 'Bouazza', 'Salma', 'F', 'salma.bouazza@gmail.com', '0690123456', '0678901234', 'bouazza.salma@example.com', '1988-06-27', 24, '/resources/images/profiles/default.png'),
(634, 'R11777701', 'El Khatib', 'Hana', 'F', 'hanaelkhatib@gmail.com', '0645678901', '0656789012', 'elkhatib.hana@example.com', '1987-02-18', 24, '/resources/images/profiles/default.png'),
(635, 'R11777702', 'Bennani', 'Omar', 'M', 'omarbennani@gmail.com', '0623456789', '0634567890', 'bennani.omar@example.com', '1986-11-10', 24, '/resources/images/profiles/default.png'),
(636, 'R11777703', 'Fassi', 'Nadia', 'F', 'nadiafassi@gmail.com', '0654321098', '0643210987', 'fassi.nadia@example.com', '2008-03-15', 24, '/resources/images/profiles/default.png'),
(637, 'R11777704', 'El Mansouri', 'Ali', 'M', 'alielmansouri@gmail.com', '0678901234', '0689012345', 'elmansouri.ali@example.com', '2007-07-23', 25, '/resources/images/profiles/default.png'),
(638, 'R11777705', 'Hassani', 'Yasmina', 'F', 'yasminahassani@gmail.com', '0698765432', '0687654321', 'hassani.yasmina@example.com', '2006-11-04', 25, '/resources/images/profiles/default.png'),
(639, 'R11777706', 'Benjelloun', 'Karim', 'M', 'karimbenjelloun@gmail.com', '0643210987', '0654321098', 'benjelloun.karim@example.com', '2005-09-09', 25, '/resources/images/profiles/default.png'),
(640, 'R11777707', 'El Mernissi', 'Amina', 'F', 'aminaelmernissi@gmail.com', '0623456789', '0634567890', 'elmernissi.amina@example.com', '2004-12-12', 25, '/resources/images/profiles/default.png'),
(641, 'R11777708', 'Berrada', 'Imad', 'M', 'imadberrada@gmail.com', '0645678901', '0656789012', 'berrada.imad@example.com', '2003-05-27', 25, '/resources/images/profiles/default.png'),
(642, 'R11777709', 'El Alami', 'Hiba', 'F', 'hibaelalami@gmail.com', '0678901234', '0689012345', 'elalami.hiba@example.com', '2002-08-31', 25, '/resources/images/profiles/default.png'),
(643, 'R11777710', 'El Moussaoui', 'Mohamed', 'M', 'mohamedelmoussaoui@gmail.com', '0667890123', '0678901234', 'elmoussaoui.mohamed@example.com', '2001-11-03', 25, '/resources/images/profiles/default.png'),
(644, 'R11777711', 'El Makki', 'Asmae', 'F', 'asmaeelmakki@gmail.com', '0623456789', '0634567890', 'elmakki.asmae@example.com', '2000-04-25', 25, '/resources/images/profiles/default.png'),
(645, 'R11777712', 'Choukri', 'Anas', 'M', 'anaschoukri@gmail.com', '0645678901', '0656789012', 'choukri.anas@example.com', '1999-02-17', 25, '/resources/images/profiles/default.png'),
(646, 'R11777713', 'El Yazidi', 'Houda', 'F', 'houdaelyazidi@gmail.com', '0690123456', '0678901234', 'elyazidi.houda@example.com', '1998-06-29', 25, '/resources/images/profiles/default.png'),
(647, 'R11777714', 'El Amrani', 'Yassine', 'M', 'yassineelamrani@gmail.com', '0623456789', '0634567890', 'elamrani.yassine@example.com', '1997-09-08', 25, '/resources/images/profiles/default.png'),
(648, 'R11777715', 'El Badaoui', 'Salma', 'F', 'salmaelbadaoui@gmail.com', '0656789012', '0645678901', 'elbadaoui.salma@example.com', '1996-12-11', 25, '/resources/images/profiles/default.png'),
(649, 'R11777716', 'El Kettani', 'Mohamed', 'M', 'mohamedelkettani@gmail.com', '0678901234', '0690123456', 'elkettani.mohamed@example.com', '1995-10-23', 25, '/resources/images/profiles/default.png'),
(650, 'R11777717', 'Berrichi', 'Nawal', 'F', 'nawalberrichi@gmail.com', '0645678901', '0656789012', 'berrichi.nawal@example.com', '1994-08-15', 25, '/resources/images/profiles/default.png'),
(651, 'R11777718', 'Hafidi', 'Yassin', 'M', 'yassinhafidi@gmail.com', '0623456789', '0634567890', 'hafidi.yassin@example.com', '1993-04-07', 25, '/resources/images/profiles/default.png'),
(652, 'R11777719', 'El Kadiri', 'Fatima', 'F', 'fatimaelkadiri@gmail.com', '0678901234', '0690123456', 'elkadiri.fatima@example.com', '1992-02-29', 26, '/resources/images/profiles/default.png'),
(653, 'R11777720', 'Ech-choubi', 'Khalid', 'M', 'khalidechchoubi@gmail.com', '0645678901', '0656789012', 'echchoubi.khalid@example.com', '1991-07-21', 26, '/resources/images/profiles/default.png'),
(654, 'R11777721', 'Bouskri', 'Nadia', 'F', 'nadiabouskri@gmail.com', '0623456789', '0634567890', 'bouskri.nadia@example.com', '1990-09-03', 26, '/resources/images/profiles/default.png'),
(655, 'R11777722', 'El Koraichi', 'Ahmed', 'M', 'ahmedelkoraichi@gmail.com', '0656789012', '0645678901', 'elkoraichi.ahmed@example.com', '1989-12-15', 26, '/resources/images/profiles/default.png'),
(656, 'R11777723', 'El Kassimi', 'Houda', 'F', 'houdaelkassimi@gmail.com', '0690123456', '0678901234', 'elkassimi.houda@example.com', '1988-06-27', 26, '/resources/images/profiles/default.png'),
(658, 'R11777725', 'El Alaoui', 'Sara', 'F', 'saraelalaoui@gmail.com', '0678901234', '0689012345', 'elalaoui.sara@example.com', '2007-07-23', 26, '/resources/images/profiles/default.png'),
(659, 'R11777726', 'Abouzaid', 'Nadia', 'F', 'nadiaabouzaid@gmail.com', '0654321098', '0643210987', 'abouzaid.nadia@example.com', '2008-03-15', 26, '/resources/images/profiles/default.png'),
(660, 'R11777727', 'Hassani', 'Youssef', 'M', 'youssefhassani@gmail.com', '0678901234', '0689012345', 'hassani.youssef@example.com', '2007-07-23', 26, '/resources/images/profiles/default.png'),
(661, 'R11777728', 'Ait Benhaddou', 'Fatima', 'F', 'fatimaaitbenhaddou@gmail.com', '0698765432', '0687654321', 'aitbenhaddou.fatima@example.com', '2006-11-04', 26, '/resources/images/profiles/default.png'),
(662, 'R11777729', 'El Mansouri', 'Ahmed', 'M', 'ahmedelmansouri@gmail.com', '0643210987', '0654321098', 'elmansouri.ahmed@example.com', '2005-09-09', 26, '/resources/images/profiles/default.png'),
(663, 'R11777730', 'Tazi', 'Leila', 'F', 'leilatazi@gmail.com', '0623456789', '0634567890', 'tazi.leila@example.com', '2004-12-12', 26, '/resources/images/profiles/default.png'),
(664, 'R11777731', 'Zouhairi', 'Yassin', 'M', 'yassinzouhairi@gmail.com', '0645678901', '0656789012', 'zouhairi.yassin@example.com', '2003-05-27', 26, '/resources/images/profiles/default.png'),
(665, 'R11777732', 'Cherkaoui', 'Nadia', 'F', 'nadiacherkaoui@gmail.com', '0678901234', '0689012345', 'cherkaoui.nadia@example.com', '2002-08-31', 26, '/resources/images/profiles/default.png'),
(666, 'R11777733', 'El Kassimi', 'Karim', 'M', 'karimelkassimi@gmail.com', '0667890123', '0678901234', 'elkassimi.karim@example.com', '2001-11-03', 26, '/resources/images/profiles/default.png'),
(667, 'R11777734', 'Boulahya', 'Fatiha', 'F', 'fatihaboulahya@gmail.com', '0623456789', '0634567890', 'boulahya.fatiha@example.com', '2000-04-25', 26, '/resources/images/profiles/default.png'),
(668, 'R11777735', 'El Ouahabi', 'Hassan', 'M', 'hassanelouahabi@gmail.com', '0645678901', '0656789012', 'elouahabi.hassan@example.com', '1999-02-17', 31, '/resources/images/profiles/default.png'),
(669, 'R11777736', 'Mazouz', 'Sofia', 'F', 'sofiamazouz@gmail.com', '0690123456', '0678901234', 'mazouz.sofia@example.com', '1998-06-29', 27, '/resources/images/profiles/default.png'),
(670, 'R11777737', 'El Mernissi', 'Mounir', 'M', 'mounirelmernissi@gmail.com', '0623456789', '0634567890', 'elmernissi.mounir@example.com', '1997-09-08', 27, '/resources/images/profiles/default.png'),
(671, 'R11777738', 'Fahmi', 'Houda', 'F', 'houdafahmi@gmail.com', '0656789012', '0645678901', 'fahmi.houda@example.com', '1996-12-11', 27, '/resources/images/profiles/default.png'),
(672, 'R11777739', 'Benslimane', 'Omar', 'M', 'omarbenslimane@gmail.com', '0678901234', '0690123456', 'benslimane.omar@example.com', '1995-10-23', 27, '/resources/images/profiles/default.png'),
(673, 'R11777740', 'El Haq', 'Amina', 'F', 'aminaelhaq@gmail.com', '0645678901', '0656789012', 'elhaq.amina@example.com', '1994-08-15', 27, '/resources/images/profiles/default.png'),
(674, 'R11777741', 'Benkirane', 'Mehdi', 'M', 'mehdibenkirane@gmail.com', '0623456789', '0634567890', 'benkirane.mehdi@example.com', '1993-04-07', 27, '/resources/images/profiles/default.png'),
(675, 'R11777742', 'El Ayoubi', 'Fatima Zahra', 'F', 'fatimazahraelayoubi@gmail.com', '0678901234', '0690123456', 'elayoubi.fatimazahra@example.com', '1992-02-29', 27, '/resources/images/profiles/default.png'),
(676, 'R11777743', 'El Amrani', 'Ahmed', 'M', 'ahmedelamrani@gmail.com', '0645678901', '0656789012', 'elamrani.ahmed@example.com', '1991-07-21', 27, '/resources/images/profiles/default.png'),
(677, 'R11777744', 'El Malki', 'Nadia', 'F', 'nadiaelmalki@gmail.com', '0623456789', '0634567890', 'elmalki.nadia@example.com', '1990-09-03', 27, '/resources/images/profiles/default.png'),
(678, 'R11777745', 'Bouazzaoui', 'Khalid', 'M', 'khalidbouazzaoui@gmail.com', '0656789012', '0645678901', 'bouazzaoui.khalid@example.com', '1989-12-15', 27, '/resources/images/profiles/default.png'),
(679, 'R11777746', 'Boukhriss', 'Souad', 'F', 'souadboukhriss@gmail.com', '0690123456', '0678901234', 'boukhriss.souad@example.com', '1988-06-27', 27, '/resources/images/profiles/default.png'),
(680, 'R11777747', 'El Khattabi', 'Abdelkader', 'M', 'abdelkaderelkhattabi@gmail.com', '0645678901', '0656789012', 'elkhattabi.abdelkader@example.com', '1987-02-18', 27, '/resources/images/profiles/default.png'),
(681, 'R11777748', 'El Moudden', 'Amina', 'F', 'aminaelmoudden@gmail.com', '0623456789', '0634567890', 'elmoudden.amina@example.com', '1986-11-10', 27, '/resources/images/profiles/default.png'),
(682, 'R11777749', 'Abdullah', 'Yasmine', 'F', 'yasmineabdullah@gmail.com', '0654321098', '0643210987', 'abdullah.yasmine@example.com', '1985-04-03', 27, '/resources/images/profiles/default.png'),
(683, 'R11777750', 'Hassan', 'Omar', 'M', 'omarhassan@gmail.com', '0678901234', '0689012345', 'hassan.omar@example.com', '1984-08-25', 27, '/resources/images/profiles/default.png'),
(684, 'R11777751', 'Ahmed', 'Nadia', 'F', 'nadiaahmed@gmail.com', '0654321098', '0643210987', 'ahmed.nadia@example.com', '2008-03-15', 28, '/resources/images/profiles/default.png'),
(685, 'R11777752', 'Hassani', 'Youssef', 'M', 'youssefhassani@gmail.com', '0678901234', '0689012345', 'hassani.youssef@example.com', '2007-07-23', 28, '/resources/images/profiles/default.png'),
(686, 'R11777753', 'Ait Benhaddou', 'Fatima', 'F', 'fatimaaitbenhaddou@gmail.com', '0698765432', '0687654321', 'aitbenhaddou.fatima@example.com', '2006-11-04', 28, '/resources/images/profiles/default.png'),
(687, 'R11777754', 'El Mansouri', 'Ahmed', 'M', 'ahmedelmansouri@gmail.com', '0643210987', '0654321098', 'elmansouri.ahmed@example.com', '2005-09-09', 28, '/resources/images/profiles/default.png'),
(688, 'R11777755', 'Tazi', 'Leila', 'F', 'leilatazi@gmail.com', '0623456789', '0634567890', 'tazi.leila@example.com', '2004-12-12', 28, '/resources/images/profiles/default.png'),
(689, 'R11777756', 'Zouhairi', 'Yassin', 'M', 'yassinzouhairi@gmail.com', '0645678901', '0656789012', 'zouhairi.yassin@example.com', '2003-05-27', 28, '/resources/images/profiles/default.png'),
(690, 'R11777757', 'Cherkaoui', 'Nadia', 'F', 'nadiacherkaoui@gmail.com', '0678901234', '0689012345', 'cherkaoui.nadia@example.com', '2002-08-31', 28, '/resources/images/profiles/default.png'),
(691, 'R11777758', 'El Kassimi', 'Karim', 'M', 'karimelkassimi@gmail.com', '0667890123', '0678901234', 'elkassimi.karim@example.com', '2001-11-03', 28, '/resources/images/profiles/default.png'),
(692, 'R11777759', 'Boulahya', 'Fatiha', 'F', 'fatihaboulahya@gmail.com', '0623456789', '0634567890', 'boulahya.fatiha@example.com', '2000-04-25', 28, '/resources/images/profiles/default.png'),
(693, 'R11777760', 'El Ouahabi', 'Hassan', 'M', 'hassanelouahabi@gmail.com', '0645678901', '0656789012', 'elouahabi.hassan@example.com', '1999-02-17', 28, '/resources/images/profiles/default.png'),
(694, 'R11777761', 'Mazouz', 'Sofia', 'F', 'sofiamazouz@gmail.com', '0690123456', '0678901234', 'mazouz.sofia@example.com', '1998-06-29', 28, '/resources/images/profiles/default.png'),
(695, 'R11777762', 'El Mernissi', 'Mounir', 'M', 'mounirelmernissi@gmail.com', '0623456789', '0634567890', 'elmernissi.mounir@example.com', '1997-09-08', 28, '/resources/images/profiles/default.png'),
(696, 'R11777763', 'Fahmi', 'Houda', 'F', 'houdafahmi@gmail.com', '0656789012', '0645678901', 'fahmi.houda@example.com', '1996-12-11', 28, '/resources/images/profiles/default.png'),
(697, 'R11777764', 'Benslimane', 'Omar', 'M', 'omarbenslimane@gmail.com', '0678901234', '0690123456', 'benslimane.omar@example.com', '1995-10-23', 28, '/resources/images/profiles/default.png'),
(698, 'R11777765', 'El Haq', 'Amina', 'F', 'aminaelhaq@gmail.com', '0645678901', '0656789012', 'elhaq.amina@example.com', '1994-08-15', 28, '/resources/images/profiles/default.png'),
(699, 'R11777766', 'Benkirane', 'Mehdi', 'M', 'mehdibenkirane@gmail.com', '0623456789', '0634567890', 'benkirane.mehdi@example.com', '1993-04-07', 29, '/resources/images/profiles/default.png'),
(700, 'R11777767', 'El Ayoubi', 'Fatima Zahra', 'F', 'fatimazahraelayoubi@gmail.com', '0678901234', '0690123456', 'elayoubi.fatimazahra@example.com', '1992-02-29', 29, '/resources/images/profiles/default.png'),
(701, 'R11777768', 'El Amrani', 'Ahmed', 'M', 'ahmedelamrani@gmail.com', '0645678901', '0656789012', 'elamrani.ahmed@example.com', '1991-07-21', 29, '/resources/images/profiles/default.png'),
(702, 'R11777769', 'El Malki', 'Nadia', 'F', 'nadiaelmalki@gmail.com', '0623456789', '0634567890', 'elmalki.nadia@example.com', '1990-09-03', 29, '/resources/images/profiles/default.png'),
(703, 'R11777770', 'Bouazzaoui', 'Khalid', 'M', 'khalidbouazzaoui@gmail.com', '0656789012', '0645678901', 'bouazzaoui.khalid@example.com', '1989-12-15', 29, '/resources/images/profiles/default.png'),
(704, 'R11777771', 'Boukhriss', 'Souad', 'F', 'souadboukhriss@gmail.com', '0690123456', '0678901234', 'boukhriss.souad@example.com', '1988-06-27', 29, '/resources/images/profiles/default.png'),
(705, 'R11777772', 'El Khattabi', 'Abdelkader', 'M', 'abdelkaderelkhattabi@gmail.com', '0645678901', '0656789012', 'elkhattabi.abdelkader@example.com', '1987-02-18', 29, '/resources/images/profiles/default.png'),
(706, 'R11777773', 'El Moudden', 'Amina', 'F', 'aminaelmoudden@gmail.com', '0623456789', '0634567890', 'elmoudden.amina@example.com', '1986-11-10', 29, '/resources/images/profiles/default.png'),
(707, 'R11777774', 'Abdullah', 'Yasmine', 'F', 'yasmineabdullah@gmail.com', '0654321098', '0643210987', 'abdullah.yasmine@example.com', '1985-04-03', 29, '/resources/images/profiles/default.png'),
(708, 'R11777775', 'Hassan', 'Omar', 'M', 'omarhassan@gmail.com', '0678901234', '0689012345', 'hassan.omar@example.com', '1984-08-25', 29, '/resources/images/profiles/default.png'),
(709, 'R11777776', 'Ait Lbachir', 'Salma', 'F', 'salmaaitlbachir@gmail.com', '0698765432', '0687654321', 'aitlbachir.salma@example.com', '1983-10-17', 29, '/resources/images/profiles/default.png'),
(710, 'R11777777', 'El Farouki', 'Youssef', 'M', 'youssefelfarouki@gmail.com', '0643210987', '0654321098', 'elfarouki.youssef@example.com', '1982-12-09', 29, '/resources/images/profiles/default.png'),
(711, 'R11777778', 'Hassani', 'Fatima', 'F', 'fatimahassani@gmail.com', '0623456789', '0634567890', 'hassani.fatima@example.com', '1981-06-21', 29, '/resources/images/profiles/default.png'),
(712, 'R11777779', 'El Mekki', 'Mohamed', 'M', 'mohamedelmekki@gmail.com', '0645678901', '0656789012', 'elmekki.mohamed@example.com', '1980-09-13', 29, '/resources/images/profiles/default.png'),
(713, 'R11777780', 'Bouziani', 'Karima', 'F', 'karimabouziani@gmail.com', '0678901234', '0689012345', 'bouziani.karima@example.com', '1979-03-05', 29, '/resources/images/profiles/default.png'),
(714, 'R11777781', 'El Asri', 'Hassan', 'M', 'hassanelasri@gmail.com', '0623456789', '0634567890', 'elasri.hassan@example.com', '1978-05-27', 30, '/resources/images/profiles/default.png'),
(715, 'R11777782', 'Bouhouch', 'Nadia', 'F', 'nadiabouhouch@gmail.com', '0656789012', '0645678901', 'bouhouch.nadia@example.com', '1977-07-19', 30, '/resources/images/profiles/default.png'),
(716, 'R11777783', 'El Bahja', 'Youssef', 'M', 'youssefelbahja@gmail.com', '0690123456', '0678901234', 'elbahja.youssef@example.com', '1976-11-11', 30, '/resources/images/profiles/default.png'),
(717, 'R11777784', 'Hassani', 'Laila', 'F', 'lailahassani@gmail.com', '0645678901', '0656789012', 'hassani.laila@example.com', '1975-09-03', 30, '/resources/images/profiles/default.png'),
(718, 'R11777785', 'Boukhari', 'Karim', 'M', 'karimboukhari@gmail.com', '0623456789', '0634567890', 'boukhari.karim@example.com', '1974-01-25', 30, '/resources/images/profiles/default.png'),
(719, 'R11777786', 'El Malki', 'Fatima', 'F', 'fatimaelmalki@gmail.com', '0678901234', '0690123456', 'elmalki.fatima@example.com', '1973-04-17', 30, '/resources/images/profiles/default.png'),
(720, 'R11777787', 'El Habib', 'Ahmed', 'M', 'ahmedelhabib@gmail.com', '0645678901', '0656789012', 'elhabib.ahmed@example.com', '1972-06-09', 30, '/resources/images/profiles/default.png'),
(721, 'R11777788', 'Bouzidi', 'Nadia', 'F', 'nadiabouzidi@gmail.com', '0623456789', '0634567890', 'bouzidi.nadia@example.com', '1971-08-01', 30, '/resources/images/profiles/default.png'),
(722, 'R11777789', 'El Fassi', 'Youssef', 'M', 'youssefelfassi@gmail.com', '0656789012', '0645678901', 'elfassi.youssef@example.com', '1970-10-23', 30, '/resources/images/profiles/default.png'),
(723, 'R11777790', 'Hassani', 'Salma', 'F', 'salmahassani@gmail.com', '0690123456', '0678901234', 'hassani.salma@example.com', '1969-12-15', 30, '/resources/images/profiles/default.png'),
(724, 'R11777791', 'El Moussaoui', 'Karim', 'M', 'karimelmoussaoui@gmail.com', '0645678901', '0656789012', 'elmoussaoui.karim@example.com', '1968-02-05', 30, '/resources/images/profiles/default.png'),
(725, 'R11777792', 'Boumediene', 'Sara', 'F', 'saraboumediene@gmail.com', '0623456789', '0634567890', 'boumediene.sara@example.com', '1967-04-01', 30, '/resources/images/profiles/default.png'),
(726, 'R11777793', 'El Kadiri', 'Mohamed', 'M', 'mohamedelkadiri@gmail.com', '0678901234', '0690123456', 'elkadiri.mohamed@example.com', '1966-06-25', 30, '/resources/images/profiles/default.png'),
(727, 'R11777794', 'Hassani', 'Nadia', 'F', 'nadiaaahassani@gmail.com', '0645678901', '0656789012', 'hassani.nadiaa@example.com', '1965-08-18', 30, '/resources/images/profiles/default.png'),
(728, 'R11777795', 'Bouazzaoui', 'Omar', 'M', 'omarbouazzaoui@gmail.com', '0623456789', '0634567890', 'bouazzaoui.omar@example.com', '1964-10-10', 30, '/resources/images/profiles/default.png'),
(729, 'R11777796', 'Bouhouch', 'Loubna', 'F', 'loubnabouhouch@gmail.com', '0656789012', '0645678901', 'bouhouch.loubna@example.com', '1963-12-03', 31, '/resources/images/profiles/default.png'),
(730, 'R11777797', 'El Malki', 'Youssef', 'M', 'youssefelmalki@gmail.com', '0690123456', '0678901234', 'elmalki.youssef@example.com', '1962-02-25', 31, '/resources/images/profiles/default.png'),
(731, 'R11777798', 'Hassani', 'Salma', 'F', 'salmaahassani@gmail.com', '0645678901', '0656789012', 'hassani.salmaa@example.com', '1961-04-20', 21, '/resources/images/profiles/default.png'),
(732, 'R11777799', 'El Mansouri', 'Mohamed', 'M', 'mohamedelmansouri@gmail.com', '0623456789', '0634567890', 'elmansouri.mohamed@example.com', '1960-06-12', 21, '/resources/images/profiles/default.png'),
(733, 'R11777800', 'Bouzidi', 'Nadia', 'F', 'nadiabouzidi@gmail.com', '0678901234', '0690123456', 'bouzidi.nadia@example.com', '1959-08-05', 21, '/resources/images/profiles/default.png'),
(744, 'R11777661', 'Tazi', 'Leila', 'F', 'leilatazi@gmail.com', '0623456789', '0634567890', 'tazileila1@example.com', '2004-12-12', 31, '/resources/images/profiles/default.png'),
(745, 'R11777801', 'Bensalah', 'Youssef', 'M', 'youssefbensalah@gmail.com', '0654321098', '0643210987', 'bensalah.youssef@example.com', '2010-01-15', 31, '/resources/images/profiles/default.png'),
(746, 'R11777802', 'El Ghazi', 'Fatima', 'F', 'fatimaelghazi@gmail.com', '0678901234', '0689012345', 'elghazi.fatima@example.com', '2009-02-25', 31, '/resources/images/profiles/default.png'),
(747, 'R11777803', 'Ouarzazi', 'Karim', 'M', 'karimouarzazi@gmail.com', '0698765432', '0687654321', 'ouarzazi.karim@example.com', '2008-03-30', 31, '/resources/images/profiles/default.png'),
(748, 'R11777804', 'Jaziri', 'Nadia', 'F', 'nadiajaziri@gmail.com', '0643210987', '0654321098', 'jaziri.nadia@example.com', '2007-04-20', 31, '/resources/images/profiles/default.png'),
(749, 'R11777805', 'El Idrissi', 'Ahmed', 'M', 'ahmedelidrissi@gmail.com', '0623456789', '0634567890', 'elidrissi.ahmed@example.com', '2006-05-10', 31, '/resources/images/profiles/default.png'),
(750, 'R11777806', 'Bouanani', 'Leila', 'F', 'leilabouanani@gmail.com', '0645678901', '0656789012', 'bouanani.leila@example.com', '2005-06-05', 31, '/resources/images/profiles/default.png'),
(751, 'R11777807', 'Tahiri', 'Yassin', 'M', 'yassintahiri@gmail.com', '0678901234', '0689012345', 'tahiri.yassin@example.com', '2004-07-15', 31, '/resources/images/profiles/default.png'),
(752, 'R11777808', 'El Mokhtar', 'Sara', 'F', 'saraelmokhtar@gmail.com', '0667890123', '0678901234', 'elmokhtar.sara@example.com', '2003-08-25', 31, '/resources/images/profiles/default.png'),
(753, 'R11777809', 'El Fadil', 'Hassan', 'M', 'hassanelfadil@gmail.com', '0623456789', '0634567890', 'elfadil.hassan@example.com', '2002-09-05', 31, '/resources/images/profiles/default.png'),
(754, 'R11777810', 'Kharbouch', 'Fatiha', 'F', 'fatihakharbouch@gmail.com', '0645678901', '0656789012', 'kharbouch.fatiha@example.com', '2001-10-15', 31, '/resources/images/profiles/default.png'),
(755, 'R11777811', 'Rachidi', 'Mohamed', 'M', 'mohamedrachidi@gmail.com', '0690123456', '0678901234', 'rachidi.mohamed@example.com', '2000-11-25', 31, '/resources/images/profiles/default.png'),
(756, 'R11777812', 'Abdelaziz', 'Youssef', 'M', 'youssefabdelaziz@gmail.com', '0654321098', '0643210987', 'abdelaziz.youssef@example.com', '2010-02-14', 32, '/resources/images/profiles/default.png'),
(757, 'R11777813', 'El Majidi', 'Fatima', 'F', 'fatimaelmajidi@gmail.com', '0678901234', '0689012345', 'elmajidi.fatima@example.com', '2009-03-23', 32, '/resources/images/profiles/default.png'),
(758, 'R11777814', 'Benomar', 'Karim', 'M', 'karimbenomar@gmail.com', '0698765432', '0687654321', 'benomar.karim@example.com', '2008-04-12', 32, '/resources/images/profiles/default.png'),
(759, 'R11777815', 'El Fassi', 'Nadia', 'F', 'nadiaelfassi@gmail.com', '0643210987', '0654321098', 'elfassi.nadia@example.com', '2007-05-21', 32, '/resources/images/profiles/default.png'),
(760, 'R11777816', 'Bensaid', 'Ahmed', 'M', 'ahmedbensaid@gmail.com', '0623456789', '0634567890', 'bensaid.ahmed@example.com', '2006-06-30', 32, '/resources/images/profiles/default.png'),
(761, 'R11777817', 'Mokhtari', 'Leila', 'F', 'leilamokhtari@gmail.com', '0645678901', '0656789012', 'mokhtari.leila@example.com', '2005-07-19', 32, '/resources/images/profiles/default.png'),
(762, 'R11777818', 'Tazi', 'Yassin', 'M', 'yassintazi@gmail.com', '0678901234', '0689012345', 'tazi.yassin@example.com', '2004-08-28', 32, '/resources/images/profiles/default.png'),
(763, 'R11777819', 'El Ghazali', 'Sara', 'F', 'saraelghazali@gmail.com', '0667890123', '0678901234', 'elghazali.sara@example.com', '2003-09-17', 32, '/resources/images/profiles/default.png'),
(764, 'R11777820', 'El Bakkali', 'Hassan', 'M', 'hassanelbakkali@gmail.com', '0623456789', '0634567890', 'elbakkali.hassan@example.com', '2002-10-26', 32, '/resources/images/profiles/default.png'),
(765, 'R11777821', 'Ouahidi', 'Fatiha', 'F', 'fatihouahidi@gmail.com', '0645678901', '0656789012', 'ouahidi.fatiha@example.com', '2001-11-05', 32, '/resources/images/profiles/default.png'),
(766, 'R11777822', 'Ait Benhaddou', 'Mohamed', 'M', 'mohamedaitbenhaddou@gmail.com', '0690123456', '0678901234', 'aitbenhaddou.mohamed@example.com', '2000-12-14', 32, '/resources/images/profiles/default.png'),
(767, 'R11777823', 'El Mernissi', 'Salma', 'F', 'salmaelmernissi@gmail.com', '0654321098', '0643210987', 'elmernissi.salma@example.com', '1999-01-23', 32, '/resources/images/profiles/default.png'),
(768, 'R11777824', 'Ait El Cadi', 'Youssef', 'M', 'youssefaitelcadi@gmail.com', '0678901234', '0689012345', 'aitelcadi.youssef@example.com', '1998-02-12', 32, '/resources/images/profiles/default.png'),
(769, 'R11777825', 'El Malki', 'Fatima', 'F', 'fatimaelmalki@gmail.com', '0698765432', '0687654321', 'elmalki.fatima@example.com', '1997-03-21', 32, '/resources/images/profiles/default.png'),
(770, 'R11777826', 'Benkirane', 'Karim', 'M', 'karimbenkirane@gmail.com', '0643210987', '0654321098', 'benkirane.karim@example.com', '1996-04-30', 32, '/resources/images/profiles/default.png'),
(771, 'R11777827', 'Bensalah', 'Youssef', 'M', 'youssefbensalah@gmail.com', '0654321098', '0643210987', 'bensalah.youssef@example.com', '2010-01-15', 33, '/resources/images/profiles/default.png'),
(772, 'R11777828', 'El Ghazi', 'Fatima', 'F', 'fatimaelghazi@gmail.com', '0678901234', '0689012345', 'elghazi.fatima@example.com', '2009-02-25', 33, '/resources/images/profiles/default.png'),
(773, 'R11777829', 'Ouarzazi', 'Karim', 'M', 'karimouarzazi@gmail.com', '0698765432', '0687654321', 'ouarzazi.karim@example.com', '2008-03-30', 33, '/resources/images/profiles/default.png'),
(774, 'R11777830', 'Jaziri', 'Nadia', 'F', 'nadiajaziri@gmail.com', '0643210987', '0654321098', 'jaziri.nadia@example.com', '2007-04-20', 33, '/resources/images/profiles/default.png'),
(775, 'R11777831', 'El Idrissi', 'Ahmed', 'M', 'ahmedelidrissi@gmail.com', '0623456789', '0634567890', 'elidrissi.ahmed@example.com', '2006-05-10', 33, '/resources/images/profiles/default.png'),
(776, 'R11777832', 'Bouanani', 'Leila', 'F', 'leilabouanani@gmail.com', '0645678901', '0656789012', 'bouanani.leila@example.com', '2005-06-05', 33, '/resources/images/profiles/default.png'),
(777, 'R11777833', 'Tahiri', 'Yassin', 'M', 'yassintahiri@gmail.com', '0678901234', '0689012345', 'tahiri.yassin@example.com', '2004-07-15', 33, '/resources/images/profiles/default.png'),
(778, 'R11777834', 'El Mokhtar', 'Sara', 'F', 'saraelmokhtar@gmail.com', '0667890123', '0678901234', 'elmokhtar.sara@example.com', '2003-08-25', 33, '/resources/images/profiles/default.png'),
(779, 'R11777835', 'El Fadil', 'Hassan', 'M', 'hassanelfadil@gmail.com', '0623456789', '0634567890', 'elfadil.hassan@example.com', '2002-09-05', 33, '/resources/images/profiles/default.png'),
(780, 'R11777836', 'Kharbouch', 'Fatiha', 'F', 'fatihakharbouch@gmail.com', '0645678901', '0656789012', 'kharbouch.fatiha@example.com', '2001-10-15', 33, '/resources/images/profiles/default.png'),
(781, 'R11777837', 'Rachidi', 'Mohamed', 'M', 'mohamedrachidi@gmail.com', '0690123456', '0678901234', 'rachidi.mohamed@example.com', '2000-11-25', 33, '/resources/images/profiles/default.png'),
(782, 'R11777838', 'Zouhair', 'Amina', 'F', 'amina.zouhair@gmail.com', '0612345678', '0623456789', 'zouhair.amina@example.com', '1999-12-14', 33, '/resources/images/profiles/default.png'),
(783, 'R11777839', 'Bouziane', 'Omar', 'M', 'omar.bouziane@gmail.com', '0654321234', '0643214321', 'bouziane.omar@example.com', '1998-11-03', 33, '/resources/images/profiles/default.png'),
(784, 'R11777840', 'El Amrani', 'Laila', 'F', 'laila.elamrani@gmail.com', '0667894321', '0678905432', 'elamrani.laila@example.com', '1997-10-22', 33, '/resources/images/profiles/default.png'),
(785, 'R11777841', 'Messaoudi', 'Youssef', 'M', 'youssef.messaoudi@gmail.com', '0645671234', '0656782345', 'messaoudi.youssef@example.com', '1996-09-11', 33, '/resources/images/profiles/default.png'),
(786, 'R11777842', 'Naciri', 'Youssef', 'M', 'youssefnaciri@gmail.com', '0654321000', '0643211000', 'naciri.youssef@example.com', '2010-01-01', 34, '/resources/images/profiles/default.png'),
(787, 'R11777843', 'El Aoufi', 'Fatima', 'F', 'fatimaelaoufi@gmail.com', '0678901000', '0689011000', 'elaoufi.fatima@example.com', '2009-02-02', 34, '/resources/images/profiles/default.png'),
(788, 'R11777844', 'Boukhari', 'Karim', 'M', 'karimboukhari@gmail.com', '0698761000', '0687651000', 'boukhari.karim@example.com', '2008-03-03', 34, '/resources/images/profiles/default.png'),
(789, 'R11777845', 'Benjelloun', 'Nadia', 'F', 'nadiabenjelloun@gmail.com', '0643211001', '0654321001', 'benjelloun.nadia@example.com', '2007-04-04', 34, '/resources/images/profiles/default.png'),
(790, 'R11777846', 'El Hadi', 'Ahmed', 'M', 'ahmedelhadi@gmail.com', '0623451000', '0634561000', 'elhadi.ahmed@example.com', '2006-05-05', 34, '/resources/images/profiles/default.png'),
(791, 'R11777847', 'Ouazzani', 'Leila', 'F', 'leilaouazzani@gmail.com', '0645671000', '0656781000', 'ouazzani.leila@example.com', '2005-06-06', 34, '/resources/images/profiles/default.png'),
(792, 'R11777848', 'El Khattabi', 'Yassin', 'M', 'yassinelkhattabi@gmail.com', '0678901001', '0689011001', 'elkhattabi.yassin@example.com', '2004-07-07', 34, '/resources/images/profiles/default.png'),
(793, 'R11777849', 'Moufid', 'Sara', 'F', 'saramoufid@gmail.com', '0667891000', '0678901000', 'moufid.sara@example.com', '2003-08-08', 34, '/resources/images/profiles/default.png'),
(794, 'R11777850', 'El Alami', 'Hassan', 'M', 'hassanelalami@gmail.com', '0623451001', '0634561001', 'elalami.hassan@example.com', '2002-09-09', 34, '/resources/images/profiles/default.png'),
(795, 'R11777851', 'Kharbouch', 'Fatiha', 'F', 'fatihakharbouch@gmail.com', '0645671001', '0656781001', 'kharbouch.fatiha@example.com', '2001-10-10', 34, '/resources/images/profiles/default.png'),
(796, 'R11777852', 'Rachidi', 'Mohamed', 'M', 'mohamedrachidi@gmail.com', '0690121000', '0678901002', 'rachidi.mohamed@example.com', '2000-11-11', 34, '/resources/images/profiles/default.png'),
(797, 'R11777853', 'Zouhair', 'Amina', 'F', 'amina.zouhair@gmail.com', '0612341000', '0623451000', 'zouhair.amina@example.com', '1999-12-12', 34, '/resources/images/profiles/default.png'),
(798, 'R11777854', 'Bouziane', 'Omar', 'M', 'omar.bouziane@gmail.com', '0654321235', '0643214322', 'bouziane.omar@example.com', '1998-11-11', 34, '/resources/images/profiles/default.png'),
(799, 'R11777855', 'El Amrani', 'Laila', 'F', 'laila.elamrani@gmail.com', '0667894322', '0678905433', 'elamrani.laila@example.com', '1997-10-10', 34, '/resources/images/profiles/default.png'),
(800, 'R11777856', 'Messaoudi', 'Youssef', 'M', 'youssef.messaoudi@gmail.com', '0645671236', '0656782346', 'messaoudi.youssef@example.com', '1996-09-09', 34, '/resources/images/profiles/default.png'),
(801, 'R11777857', 'Alaoui', 'Youssef', 'M', 'youssefalaoui@gmail.com', '0654321002', '0643211002', 'alaoui.youssef@example.com', '2010-01-01', 35, '/resources/images/profiles/default.png'),
(802, 'R11777858', 'El Khatib', 'Fatima', 'F', 'fatimaelkhatib@gmail.com', '0678901002', '0689011002', 'elkhatib.fatima@example.com', '2009-02-02', 35, '/resources/images/profiles/default.png'),
(803, 'R11777859', 'Belarbi', 'Karim', 'M', 'karimbelarbi@gmail.com', '0698761002', '0687651002', 'belarbi.karim@example.com', '2008-03-03', 35, '/resources/images/profiles/default.png'),
(804, 'R11777860', 'Touhami', 'Nadia', 'F', 'nadiatouhami@gmail.com', '0643211003', '0654321003', 'touhami.nadia@example.com', '2007-04-04', 35, '/resources/images/profiles/default.png'),
(805, 'R11777861', 'Boussaid', 'Ahmed', 'M', 'ahmedboussaid@gmail.com', '0623451002', '0634561002', 'boussaid.ahmed@example.com', '2006-05-05', 35, '/resources/images/profiles/default.png'),
(806, 'R11777862', 'Sebaai', 'Leila', 'F', 'leilasebaai@gmail.com', '0645671002', '0656781002', 'sebaai.leila@example.com', '2005-06-06', 35, '/resources/images/profiles/default.png'),
(807, 'R11777863', 'Tayebi', 'Yassin', 'M', 'yassintayebi@gmail.com', '0678901003', '0689011003', 'tayebi.yassin@example.com', '2004-07-07', 35, '/resources/images/profiles/default.png'),
(808, 'R11777864', 'El Kacemi', 'Sara', 'F', 'saraelkacemi@gmail.com', '0667891002', '0678901002', 'elkacemi.sara@example.com', '2003-08-08', 35, '/resources/images/profiles/default.png'),
(809, 'R11777865', 'El Ouazzani', 'Hassan', 'M', 'hassanelouazzani@gmail.com', '0623451003', '0634561003', 'elouazzani.hassan@example.com', '2002-09-09', 35, '/resources/images/profiles/default.png'),
(810, 'R11777866', 'Moutaouakil', 'Fatiha', 'F', 'fatihamoutaouakil@gmail.com', '0645671003', '0656781003', 'moutaouakil.fatiha@example.com', '2001-10-10', 35, '/resources/images/profiles/default.png'),
(811, 'R11777867', 'El Hassani', 'Mohamed', 'M', 'mohamedelhassani@gmail.com', '0690121002', '0678901004', 'elhassani.mohamed@example.com', '2000-11-11', 35, '/resources/images/profiles/default.png'),
(812, 'R11777868', 'Zouaoui', 'Amina', 'F', 'amina.zouaoui@gmail.com', '0612341002', '0623451002', 'zouaoui.amina@example.com', '1999-12-12', 35, '/resources/images/profiles/default.png'),
(813, 'R11777869', 'Benchekroun', 'Omar', 'M', 'omarbenchekroun@gmail.com', '0654321236', '0643214323', 'benchekroun.omar@example.com', '1998-11-11', 35, '/resources/images/profiles/default.png'),
(814, 'R11777870', 'El Mernissi', 'Laila', 'F', 'laila.elmernissi@gmail.com', '0667894323', '0678905434', 'elmernissi.laila@example.com', '1997-10-10', 35, '/resources/images/profiles/default.png'),
(815, 'R11777871', 'Bennani', 'Youssef', 'M', 'youssef.bennani@gmail.com', '0645671237', '0656782347', 'bennani.youssef@example.com', '1996-09-09', 35, '/resources/images/profiles/default.png'),
(816, 'R11777872', 'Mouhoub', 'Youssef', 'M', 'youssefmouhoub@gmail.com', '0654321003', '0643211003', 'mouhoub.youssef@example.com', '2010-01-01', 36, '/resources/images/profiles/default.png'),
(817, 'R11777873', 'Ait Ben Hadi', 'Fatima', 'F', 'fatimaaitbenhadi@gmail.com', '0678901003', '0689011003', 'aitbenhadi.fatima@example.com', '2009-02-02', 36, '/resources/images/profiles/default.png'),
(818, 'R11777874', 'El Baz', 'Karim', 'M', 'karimelbaz@gmail.com', '0698761003', '0687651003', 'elbaz.karim@example.com', '2008-03-03', 36, '/resources/images/profiles/default.png'),
(819, 'R11777875', 'Belghiti', 'Nadia', 'F', 'nadiabelghiti@gmail.com', '0643211004', '0654321004', 'belghiti.nadia@example.com', '2007-04-04', 36, '/resources/images/profiles/default.png'),
(820, 'R11777876', 'El Mansour', 'Ahmed', 'M', 'ahmedelmansour@gmail.com', '0623451003', '0634561003', 'elmansour.ahmed@example.com', '2006-05-05', 36, '/resources/images/profiles/default.png'),
(821, 'R11777877', 'Bahri', 'Leila', 'F', 'leilabahri@gmail.com', '0645671003', '0656781003', 'bahri.leila@example.com', '2005-06-06', 36, '/resources/images/profiles/default.png'),
(822, 'R11777878', 'Tlemsani', 'Yassin', 'M', 'yassintlemsani@gmail.com', '0678901004', '0689011004', 'tlemsani.yassin@example.com', '2004-07-07', 36, '/resources/images/profiles/default.png'),
(823, 'R11777879', 'El Kachani', 'Sara', 'F', 'saraelkachani@gmail.com', '0667891003', '0678901003', 'elkachani.sara@example.com', '2003-08-08', 36, '/resources/images/profiles/default.png'),
(824, 'R11777880', 'Mouftakir', 'Hassan', 'M', 'hassanmouftakir@gmail.com', '0623451004', '0634561004', 'mouftakir.hassan@example.com', '2002-09-09', 36, '/resources/images/profiles/default.png'),
(825, 'R11777881', 'Benazzi', 'Fatiha', 'F', 'fatihabenazzi@gmail.com', '0645671004', '0656781004', 'benazzi.fatiha@example.com', '2001-10-10', 36, '/resources/images/profiles/default.png'),
(826, 'R11777882', 'El Khamlichi', 'Mohamed', 'M', 'mohamedelkhamlichi@gmail.com', '0690121003', '0678901005', 'elkhamlichi.mohamed@example.com', '2000-11-11', 36, '/resources/images/profiles/default.png'),
(827, 'R11777883', 'Zouak', 'Amina', 'F', 'amina.zouak@gmail.com', '0612341003', '0623451003', 'zouak.amina@example.com', '1999-12-12', 36, '/resources/images/profiles/default.png'),
(828, 'R11777884', 'Bennis', 'Omar', 'M', 'omar.bennis@gmail.com', '0654321237', '0643214324', 'bennis.omar@example.com', '1998-11-11', 36, '/resources/images/profiles/default.png'),
(829, 'R11777885', 'El Mernissi', 'Laila', 'F', 'laila.elmernissi@gmail.com', '0667894324', '0678905435', 'elmernissi.laila@example.com', '1997-10-10', 36, '/resources/images/profiles/default.png'),
(830, 'R11777886', 'Bennani', 'Youssef', 'M', 'youssef.bennani@gmail.com', '0645671238', '0656782348', 'bennani.youssef@example.com', '1996-09-09', 36, '/resources/images/profiles/default.png');

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
(20, 2, 'Salle H1', 45),
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
(16, 7, 'VENDREDI', '08:00:00', '23:00:00', 21, 20),
(17, 7, 'MERCREDI', '10:00:00', '12:00:00', 22, 20);

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
(9, 'Sports'),
(10, 'testType');

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `telephone` (`telephone`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `administrateur`
--
ALTER TABLE `administrateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=831;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `typeCours`
--
ALTER TABLE `typeCours`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `absence_ibfk_1` FOREIGN KEY (`idSeance`) REFERENCES `seance` (`id`) ON DELETE CASCADE,
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
