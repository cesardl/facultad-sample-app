-- phpMyAdmin SQL Dump
-- version 3.2.2.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 04-04-2010 a las 14:44:04
-- Versión del servidor: 5.1.37
-- Versión de PHP: 5.2.10-2ubuntu6.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `facultad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE IF NOT EXISTS `alumno` (
  `id_alum` int(11) NOT NULL AUTO_INCREMENT,
  `cod_alum` varchar(8) NOT NULL,
  `nom_alum` varchar(255) NOT NULL,
  `edad_alum` int(11) NOT NULL,
  `sexo_alum` char(1) NOT NULL,
  `direc_alum` varchar(255) NOT NULL,
  `telef_alum` varchar(9) DEFAULT NULL,
  `profesor_id_prof` int(11) NOT NULL,
  PRIMARY KEY (`id_alum`),
  KEY `fk_alumno_profesor` (`profesor_id_prof`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=200853 ;

--
-- Volcar la base de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`id_alum`, `cod_alum`, `nom_alum`, `edad_alum`, `sexo_alum`, `direc_alum`, `telef_alum`, `profesor_id_prof`) VALUES
(1, '200004', 'Carlos Perez', 21, 'M', 'Av Javier Prado', '8563-1456', 1),
(2, '200135', 'Eliana Zapata', 22, 'F', 'Av Mirones', NULL, 1),
(3, '200147', 'Richard Lozano', 21, 'M', 'Av Castilla', '9632-1547', 1),
(4, '200152', 'Lizbeth Mamani', 19, 'F', 'Av Habich', '5684-9963', 2),
(5, '200159', 'Silvia Penha', 21, 'F', 'Av Leitod', '8541-2878', 2),
(6, '200170', 'Cesar Diaz', 21, 'M', 'Av Confraternidad', '2569-8845', 2),
(7, '200369', 'Alexsandra Herrera', 20, 'F', 'Av Rios', '9632-1478', 2),
(8, '200456', 'Gustavo Avila', 23, 'M', 'Av Universitaria', NULL, 1),
(9, '200584', 'Etmee Zarate', 23, 'F', 'Av Sauces', '1235-4785', 1),
(10, '200852', 'Jose Lopez', 19, 'M', 'Av Aviacion', NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `id_prof` int(11) NOT NULL AUTO_INCREMENT,
  `cod_prof` varchar(8) NOT NULL,
  `nom_prof` varchar(255) NOT NULL,
  `edad_prof` int(11) NOT NULL,
  `email_prof` varchar(45) NOT NULL,
  PRIMARY KEY (`id_prof`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=212589 ;

--
-- Volcar la base de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id_prof`, `cod_prof`, `nom_prof`, `edad_prof`, `email_prof`) VALUES
(1, '212399', 'Carlos Melo', 51, 'cmelo@univ.edu'),
(2, '212588', 'Giovanna Melva', 43, 'gmelva@univ.edu');

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `fk_alumno_profesor` FOREIGN KEY (`profesor_id_prof`) REFERENCES `profesor` (`id_prof`) ON DELETE NO ACTION ON UPDATE NO ACTION;
