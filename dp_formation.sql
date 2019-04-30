-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 29 avr. 2019 à 17:52
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dp_formation`
--
CREATE DATABASE IF NOT EXISTS `dp_formation` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dp_formation`;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `ID_Personne` int(11) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Poids` float NOT NULL,
  `Taille` float NOT NULL,
  `Sexe` varchar(12) NOT NULL,
  `ID_Societe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`ID_Personne`, `Nom`, `Prenom`, `Poids`, `Taille`, `Sexe`, `ID_Societe`) VALUES
(1, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(2, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(3, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(4, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(5, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(6, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(7, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1),
(8, 'Abelard', 'Simon', 54.7, 1.75, 'MASCULIN', 1);

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

CREATE TABLE `societe` (
  `ID_Societe` int(11) NOT NULL,
  `Nom` varchar(60) NOT NULL,
  `ChiffreDAffaire` float NOT NULL,
  `Activite` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `societe`
--

INSERT INTO `societe` (`ID_Societe`, `Nom`, `ChiffreDAffaire`, `Activite`) VALUES
(1, 'BNP', 10000000, 'Banque'),
(2, 'M2I Formation', 540001, 'Organisme de formation'),
(3, 'M2I Formation', 540001, 'Organisme de formation'),
(4, 'M2I Formation', 540001, 'Organisme de formation');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`ID_Personne`);

--
-- Index pour la table `societe`
--
ALTER TABLE `societe`
  ADD PRIMARY KEY (`ID_Societe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
