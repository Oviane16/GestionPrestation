-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 28 Septembre 2017 à 15:40
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `prestation`
--

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE IF NOT EXISTS `medecin` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `taux_journalier` int(10) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `medecin`
--

INSERT INTO `medecin` (`id`, `nom`, `taux_journalier`, `numero`) VALUES
(1, 'Sanda', 4000, 'MED001'),
(2, 'Aly', 200, 'MED002'),
(3, 'andrana', 20000, 'MED003'),
(4, 'testaDoc', 10000, 'MED004');

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

CREATE TABLE IF NOT EXISTS `medicament` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `design` varchar(255) DEFAULT NULL,
  `pu` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `medicament`
--

INSERT INTO `medicament` (`id`, `numero`, `design`, `pu`) VALUES
(1, 'MDIC001', 'para', 50);

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

CREATE TABLE IF NOT EXISTS `ordonnance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `nbJour` int(10) DEFAULT NULL,
  `dateCons` date DEFAULT NULL,
  `patientid` int(10) DEFAULT NULL,
  `medicamentid` int(10) DEFAULT NULL,
  `medecinid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `donner` (`medecinid`),
  KEY `avoir` (`patientid`),
  KEY `FKordonnance622639` (`medicamentid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `ordonnance`
--

INSERT INTO `ordonnance` (`id`, `numero`, `nbJour`, `dateCons`, `patientid`, `medicamentid`, `medecinid`) VALUES
(1, 'ORDO001', 2, NULL, 1, 1, 1),
(2, 'ORDO002', 4, '2017-09-24', 1, 1, 1),
(3, 'ORDO003', 8, '2017-09-24', 1, 1, 1),
(4, 'ORDO004', 6, '2017-09-24', 3, 1, 1),
(5, 'ORDO005', 5, '2017-09-24', 4, 1, 1),
(6, 'ORDO006', 3, '2017-09-26', 1, 1, 2),
(7, 'ORDO007', 12, '2017-09-26', 1, 1, 2),
(8, 'ORDO008', 6, '2017-09-26', 6, 1, 3),
(9, 'ORDO009', 7, '2017-09-26', 6, 1, 3),
(10, 'ORDO010', 6, '2017-09-26', 7, 1, 4),
(11, 'ORDO011', 8, '2017-09-26', 7, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`id`, `numero`, `nom`, `adresse`) VALUES
(1, 'PAT001', 'Mialy cherie', 'Mokana'),
(3, 'PAT002', 'testa', '2000'),
(4, 'PAT003', 'Mialy tiko', 'Ambomaso'),
(5, 'PAT004', 'Andry', 'Andrainjato'),
(6, 'PAT005', 'andranaPatient', 'tana'),
(7, 'PAT006', 'testaPatient', 'fianara');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ordonnance`
--
ALTER TABLE `ordonnance`
  ADD CONSTRAINT `avoir` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `donner` FOREIGN KEY (`medecinid`) REFERENCES `medecin` (`id`),
  ADD CONSTRAINT `FKordonnance622639` FOREIGN KEY (`medicamentid`) REFERENCES `medicament` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
