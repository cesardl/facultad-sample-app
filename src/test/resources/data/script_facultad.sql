/*
Navicat MySQL Data Transfer

Source Server         : Local server
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : facultad

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2014-10-18 17:05:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `alumno`
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno` (
  `id_alum` int(11) NOT NULL auto_increment,
  `cod_alum` varchar(8) NOT NULL,
  `nom_alum` varchar(255) NOT NULL,
  `nacimiento_alum` date NOT NULL,
  `sexo_alum` char(1) NOT NULL,
  `direc_alum` varchar(255) NOT NULL,
  `telef_alum` varchar(9) default NULL,
  `profesor_id_prof` int(11) NOT NULL,
  PRIMARY KEY  (`id_alum`),
  KEY `fk_alumno_profesor` (`profesor_id_prof`),
  CONSTRAINT `fk_alumno_profesor` FOREIGN KEY (`profesor_id_prof`) REFERENCES `profesor` (`id_prof`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alumno
-- ----------------------------
INSERT INTO `alumno` VALUES ('1', '200004', 'Carlos Perez', '1988-01-01', 'M', 'Av Javier Prado', '8563-1456', '1');
INSERT INTO `alumno` VALUES ('2', '200135', 'Eliana Zapata', '1988-01-01', 'F', 'Av Mirones', null, '1');
INSERT INTO `alumno` VALUES ('3', '200147', 'Richard Lozano', '1988-01-01', 'M', 'Av Castilla', '9632-1547', '1');
INSERT INTO `alumno` VALUES ('4', '200152', 'Lizbeth Mamani', '1988-01-01', 'F', 'Av Habich', '5684-9963', '2');
INSERT INTO `alumno` VALUES ('5', '200159', 'Silvia Penha', '1988-01-01', 'F', 'Av Leitod', '8541-2878', '2');
INSERT INTO `alumno` VALUES ('6', '200170', 'Cesar Diaz', '1988-01-01', 'M', 'Av Confraternidad', '2569-8845', '2');
INSERT INTO `alumno` VALUES ('7', '200369', 'Alexsandra Herrera', '1988-01-01', 'F', 'Av Rios', '9632-1478', '2');
INSERT INTO `alumno` VALUES ('8', '200456', 'Gustavo Avila', '1988-01-01', 'M', 'Av Universitaria', null, '1');
INSERT INTO `alumno` VALUES ('9', '200584', 'Etmee Zarate', '1988-01-01', 'F', 'Av Sauces', '1235-4785', '1');
INSERT INTO `alumno` VALUES ('10', '200852', 'Jose Lopez', '1988-01-01', 'M', 'Av Aviacion', null, '2');

-- ----------------------------
-- Table structure for `profesor`
-- ----------------------------
DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor` (
  `id_prof` int(11) NOT NULL auto_increment,
  `cod_prof` varchar(8) NOT NULL,
  `nom_prof` varchar(255) NOT NULL,
  `nacimiento_prof` date NOT NULL,
  `email_prof` varchar(45) NOT NULL,
  PRIMARY KEY  (`id_prof`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profesor
-- ----------------------------
INSERT INTO `profesor` VALUES ('1', '212399', 'Carlos Melo', '1960-01-01', 'cmelo@univ.edu');
INSERT INTO `profesor` VALUES ('2', '212588', 'Giovanna Melva', '1960-01-01', 'gmelva@univ.edu');
